package com.example.prol_educa.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prol_educa.config.EfiConfig;
import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Transactions;
import com.example.prol_educa.utils.MoneyUtils;
import com.example.prol_educa.utils.StringCleaner;
import com.example.prol_educa.utils.enuns.EPaymentMethods;
import com.example.prol_educa.utils.enuns.EStatusTransactions;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

@Service
public class BilletPaymentService {

  @Autowired
  CompaniesService companiesService;

  @Autowired
  TransactionsService transactionsService;

  public Map<String, Object> createBilletCharge(Map<String, Object> requestBody) throws Exception {
    Map<String, Object> response = new HashMap<>();

    try {
      Object companyData = requestBody.get("company");
      Object billingData = requestBody.get("billing");

      if (!(companyData instanceof Map<?, ?> companyMap) || !(billingData instanceof Map<?, ?> billingMap)) {
        response.put("status", false);
        response.put("message", "Erro ao processar requisição no servidor.");
        return response;
      }

      boolean allKeyMatchCompany = companyMap.keySet().stream().allMatch(key -> key instanceof String);
      boolean allKeyMatchBilling = billingMap.keySet().stream().allMatch(key -> key instanceof String);

      if (!allKeyMatchCompany || !allKeyMatchBilling) {
        response.put("status", false);
        response.put("message", "Erro ao processar requisição no servidor.");
        return response;
      }

      @SuppressWarnings("unchecked")
      Map<String, Object> companyDataMap = (Map<String, Object>) companyMap;

      @SuppressWarnings("unchecked")
      Map<String, Object> billingDataMap = (Map<String, Object>) billingMap;

      Map<String, String> params = new HashMap<>();
      Map<String, Object> chargeBody = new HashMap<>();

      Integer companyId = (Integer) companyDataMap.get("companyId");
      Companies company = companiesService.findById(companyId);
      if (company == null) {
        response.put("status", false);
        response.put("message", "Empresa não encontrada.");
        return response;
      }

      Map<String, Object> companyAddress = (Map<String, Object>) companyDataMap.get("address");

      Number realValue = (Number) billingDataMap.get("value");
      Integer valueInCents = MoneyUtils.convertToCents(realValue);

      Map<String, Object> item = new HashMap<>();
      item.put("name", "Mensalidade EduPass");
      item.put("amount", 1);
      item.put("value", valueInCents);

      List<Map<String, Object>> items = new ArrayList<>();
      items.add(item);

      Map<String, Object> juridicalPerson = new HashMap<>();
      juridicalPerson.put("cnpj", (String) StringCleaner.cleanCNPJ(company.getCnpj()));
      juridicalPerson.put("corporate_name", (String) StringCleaner.cleanName(company.getFantasyName()));

      Map<String, Object> address = new HashMap<>();
      address.put("street", companyAddress.get("street"));
      address.put("number", companyAddress.get("number"));
      address.put("neighborhood", companyAddress.get("neighborhood"));
      address.put("zipcode", companyAddress.get("zipcode"));
      address.put("city", companyAddress.get("city"));
      address.put("complement", companyAddress.get("complement"));
      address.put("state", companyAddress.get("state"));

      Map<String, Object> customer = new HashMap<>();
      customer.put("email", (String) company.getEmail());
      customer.put("phone_number", (String) company.getPhoneNumber());
      customer.put("juridical_person", (Map<String, Object>) juridicalPerson);
      customer.put("address", (Map<String, Object>) address);

      Map<String, Object> configurations = new HashMap<>();
      configurations.put("fine", 200);
      configurations.put("interest", 33);

      Map<String, Object> bankingBillet = new HashMap<>();
      bankingBillet.put("customer", (Map<String, Object>) customer);
      bankingBillet.put("expire_at", LocalDate.now().plusDays(3)); // adicionar 3 dias na data de hoje

      bankingBillet.put("configurations", configurations);

      Map<String, Object> payment = new HashMap<>();
      payment.put("banking_billet", (Map<String, Object>) bankingBillet);

      Map<String, Object> metadata = new HashMap<>();
      metadata.put("notification_url", "https://edupass-backend-production.up.railway.app/payment/process");

      chargeBody.put("items", (List<Map<String, Object>>) items);
      chargeBody.put("payment", (Map<String, Object>) payment);
      chargeBody.put("metadata", (Map<String, Object>) metadata);

      // Criação da cobrança na Efí
      EfiPay efipay = new EfiPay(EfiConfig.getOptions());
      Map<String, Object> chargeResponse = efipay.call("createOneStepCharge", params, chargeBody);

      Object rawData = chargeResponse.get("data");
      if (!(rawData instanceof Map<?, ?> rawMap)) {
        response.put("status", false);
        response.put("message", "Falha na resposta do sistema de pagamentos.");
        return response;
      }

      boolean keysAreStrings = rawMap.keySet().stream().allMatch(key -> key instanceof String);
      if (!keysAreStrings) {
        response.put("status", false);
        response.put("message", "Resposta da Efí está no formato inesperado.");
        return response;
      }

      @SuppressWarnings("unchecked")
      Map<String, Object> data = (Map<String, Object>) rawMap;

      Integer chargeId = (Integer) data.get("charge_id");
      String chargeStatus = ((String) data.get("status")).toUpperCase();
      Double chargeTotalValue = ((Number) data.get("total")).doubleValue();
      String billetLink = (String) data.get("link");
      String billetPdfLink = (String) ((Map<String, Object>) data.get("pdf")).get("charge");

      Transactions transactions = new Transactions();
      transactions.setEmpresaId(company);
      transactions.setCobrancaId(chargeId);
      transactions.setValor(chargeTotalValue.floatValue());
      transactions.setMetodoPagamento(EPaymentMethods.BOLETO);

      ZoneId BrTimezone = ZoneId.of("America/Sao_Paulo");
      OffsetDateTime billingDate = OffsetDateTime.now(BrTimezone);
      transactions.setDataCobranca(billingDate);

      switch (chargeStatus) {
      case "WAITING" -> {
        transactions.setStatus(EStatusTransactions.AGUARDANDO);
        response.put("message", "Boleto gerado com sucesso e aguardando o pagamento.");
      }
      default -> {
        transactions.setStatus(EStatusTransactions.AGUARDANDO);
        response.put("message", "Status do boleto não identificado. Entre com contato com o suporte.");
        throw new IllegalStateException("Status da cobrança por boleto não identificado: " + chargeStatus);
      }
      }

      transactionsService.create(transactions);

      response.put("status", true);
      response.put("billetLink", billetLink);
      response.put("billetPdfLink", billetPdfLink);

      return response;

    } catch (EfiPayException e) {
      response.put("status", false);
      response.put("message",
          String.format("Erro ao processar pagamento com boleto: %s!, %s!", e.getMessage(), e.getErrorDescription()));
      return response;
    } catch (Exception e) {
      // Log completo para desenvolvedores e suporte
      e.printStackTrace(); // Em produção, prefira usar logger.error()
      response.put("status", false);
      response.put("message", String.format("Erro interno ao criar cobrança com boleto: %s", e.getMessage()));
      return response;
    }
  }
}
