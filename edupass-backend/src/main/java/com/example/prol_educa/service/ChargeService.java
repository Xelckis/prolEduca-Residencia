package com.example.prol_educa.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prol_educa.config.EfiConfig;
import com.example.prol_educa.utils.enuns.EStatusTransactions;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

@Service
public class ChargeService {

  @Autowired
  TransactionsService transactionsService;

  public Map<String, Object> process(Map<String, String> responseBody) throws Exception {
    Map<String, Object> response = new HashMap<>();

    try {
      String notificationToken = responseBody.get("notification");

      HashMap<String, String> token = new HashMap<>();
      token.put("token", notificationToken);

      // Criação da cobrança na Efí
      EfiPay efipay = new EfiPay(EfiConfig.getOptions());
      Map<String, Object> notificationResponse = efipay.call("getNotification", token, new HashMap<>()); // {code:200, data:[{...},{...}]}

      List<Map<String, Object>> dataList = (List<Map<String, Object>>) notificationResponse.get("data");
      Map<String, Object> lastUpdate = (Map<String, Object>) dataList.get(dataList.size() - 1);

      Map<String, Object> identifiers = (Map<String, Object>) lastUpdate.get("identifiers");
      Map<String, Object> status = (Map<String, Object>) lastUpdate.get("status");

      Number chargeId = (Number) identifiers.get("charge_id");
      String currentStatus = ((String) status.get("current")).toUpperCase();

      var transactions = transactionsService.findByChargeId(chargeId);

      // status possíveis para cartão e boleto
      // new, waiting, approved, paid, unpaid

      switch (currentStatus) {
      case "NEW" -> transactions.setStatus(EStatusTransactions.AGUARDANDO);
      case "WAITING" -> transactions.setStatus(EStatusTransactions.AGUARDANDO);
      case "APPROVED" -> transactions.setStatus(EStatusTransactions.APROVADO);
      case "PAID" -> transactions.setStatus(EStatusTransactions.PAGO);
      case "UNPAID" -> transactions.setStatus(EStatusTransactions.RECUSADO);
      default -> {
        transactions.setStatus(EStatusTransactions.AGUARDANDO);
        response.put("message", "Novo status da cobrança não identificado");
        throw new IllegalStateException("Novo status da cobrança não identificado: " + currentStatus);
      }
      }

      ZoneId BrTimezone = ZoneId.of("America/Sao_Paulo");
      OffsetDateTime notificationDate = OffsetDateTime.now(BrTimezone);
      transactions.setDataNotificacao(notificationDate);

      transactionsService.update(transactions.getId(), transactions);

      response.put("status", true);
      response.put("message", "Notificação processada com sucesso!");

      return response;

    } catch (EfiPayException e) {
      response.put("status", false);
      response.put("message",
          String.format("Erro interno ao consultar notificação: %s!, %s!", e.getMessage(), e.getErrorDescription()));
      return response;
    } catch (Exception e) {
      // Log completo para desenvolvedores e suporte
      e.printStackTrace(); // Em produção, prefira usar logger.error()
      response.put("status", false);
      response.put("message", String.format("Erro interno ao processar resposta Efí: %s", e.getMessage()));
      return response;
    }
  }
}
