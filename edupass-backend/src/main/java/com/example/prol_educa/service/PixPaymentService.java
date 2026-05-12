package com.example.prol_educa.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prol_educa.config.EfiConfig;
import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Transactions;
import com.example.prol_educa.utils.StringCleaner;
import com.example.prol_educa.utils.enuns.EPaymentMethods;
import com.example.prol_educa.utils.enuns.EStatusTransactions;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

@Service
public class PixPaymentService {

  @Autowired
  CompaniesService companiesService;

  @Autowired
  TransactionsService transactionsService;

  public Map<String, Object> createPixCharge(Map<String, Object> requestBody) throws Exception {
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

      Integer companyId = (Integer) companyDataMap.get("companyId");
      Companies company = companiesService.findById(companyId);
      if (company == null) {
        response.put("status", false);
        response.put("message", "Empresa não encontrada.");
        return response;
      }

      Map<String, Object> calendar = new HashMap<>();
      calendar.put("expiracao", 3600);

      Map<String, Object> payer = new HashMap<>();
      payer.put("cnpj", StringCleaner.cleanCNPJ(company.getCnpj()));
      payer.put("nome", StringCleaner.cleanName(company.getFantasyName()));

      Object rawValue = billingDataMap.get("value");
      double valor = ((Number) rawValue).doubleValue();

      Map<String, Object> value = new HashMap<>();
      value.put("original", String.format(Locale.US, "%.2f", valor));

      Map<String, Object> chargeBody = new HashMap<>();
      chargeBody.put("calendario", calendar);
      chargeBody.put("devedor", payer);
      chargeBody.put("valor", value);
      chargeBody.put("chave", "7395824e-5f52-4157-aa99-66e8c38100b3"); // sua chave Pix

      chargeBody.put("solicitacaoPagador", "Pagamento referente à mensalidade do EduPass.");

      // Criação da cobrança imediata
      EfiPay efipay = new EfiPay(EfiConfig.getOptions());
      Map<String, Object> pixChargeResponse = efipay.call("pixCreateImmediateCharge", new HashMap<>(), chargeBody);

      @SuppressWarnings("unchecked")
      Map<String, Object> loc = (Map<String, Object>) pixChargeResponse.get("loc");

      Integer txId = (Integer) loc.get("id");

      // Gera o QR Code
      Map<String, String> paramsQRCode = new HashMap<>();
      paramsQRCode.put("id", txId.toString());

      Map<String, Object> qrCodeResponse = efipay.call("pixGenerateQRCode", paramsQRCode, new HashMap<>());

      // Salvar transação no banco
      Transactions transaction = new Transactions();
      transaction.setEmpresaId(company);
      transaction.setCobrancaId(txId);
      Float valorFloat = ((Number) billingDataMap.get("value")).floatValue();
      transaction.setValor(valorFloat);
      transaction.setMetodoPagamento(EPaymentMethods.PIX);
      transaction.setStatus(EStatusTransactions.AGUARDANDO);
      
      ZoneId BrTimezone = ZoneId.of("America/Sao_Paulo");
      OffsetDateTime billingDate = OffsetDateTime.now(BrTimezone);
      transaction.setDataCobranca(billingDate);

      transactionsService.create(transaction);

      response.put("status", true);
      response.put("message", "Cobrança PIX gerada com sucesso.");
      // response.put("txid", txId);
      // response.put("locationId", loc.get("id"));
      response.put("qrcode", qrCodeResponse.get("qrcode"));
      response.put("imagemQrcode", qrCodeResponse.get("imagemQrcode"));

      return response;

    } catch (EfiPayException e) {
      response.put("status", false);
      response.put("message",
          String.format("Erro ao processar pagamento com PIX: %s!, %s!", e.getMessage(), e.getErrorDescription()));
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      response.put("status", false);
      response.put("message", String.format("Erro interno ao criar cobrança com PIX: %s", e.getMessage()));
      return response;
    }
  }
}