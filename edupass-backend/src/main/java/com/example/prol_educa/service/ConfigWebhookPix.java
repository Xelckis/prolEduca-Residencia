package com.example.prol_educa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.prol_educa.config.EfiConfig;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

@Service
public class ConfigWebhookPix {
  public Map<String, Object> config() throws Exception {
    Map<String, Object> response = new HashMap<>();

    try {
      // String notificationToken = responseBody.get("notification");

      HashMap<String, String> params = new HashMap<>();
      params.put("chave", "7395824e-5f52-4157-aa99-66e8c38100b3");

      Map<String, Object> body = new HashMap<>();
      body.put("webhookUrl", "https://edupass-backend-production.up.railway.app/payment/process?ignorar=");

      // Criação da cobrança na Efí
      EfiPay efipay = new EfiPay(EfiConfig.getOptions());
      Map<String, Object> configResponse = efipay.call("pixConfigWebhook", params, body);

      response.put("response", configResponse);

      // List<Map<String, Object>> dataList = (List<Map<String, Object>>) notificationResponse.get("data");
      // Map<String, Object> lastUpdate = (Map<String, Object>) dataList.get(dataList.size() - 1);

      // Map<String, Object> identifiers = (Map<String, Object>) lastUpdate.get("identifiers");
      // Map<String, Object> status = (Map<String, Object>) lastUpdate.get("status");

      // Number chargeId = (Number) identifiers.get("charge_id");
      // String currentStatus = ((String) status.get("current")).toUpperCase();

      // Transactions transactions = transactionsService.findByChargeId(chargeId);

      // // status possíveis para cartão e boleto
      // // new, waiting, approved, paid, unpaid

      // switch (currentStatus) {
      // case "NEW" -> transactions.setStatus(EStatusTransactions.AGUARDANDO);
      // case "WAITING" -> transactions.setStatus(EStatusTransactions.AGUARDANDO);
      // case "APPROVED" -> transactions.setStatus(EStatusTransactions.APROVADO);
      // case "PAID" -> transactions.setStatus(EStatusTransactions.PAGO);
      // case "UNPAID" -> transactions.setStatus(EStatusTransactions.RECUSADO);
      // default -> {
      //   transactions.setStatus(EStatusTransactions.AGUARDANDO);
      //   response.put("message", "Novo status da cobrança não identificado");
      //   throw new IllegalStateException("Novo status da cobrança não identificado: " + currentStatus);
      // }
      // }

      // ZoneId BrTimezone = ZoneId.of("America/Sao_Paulo");
      // OffsetDateTime notificationDate = OffsetDateTime.now(BrTimezone);
      // transactions.setDataNotificacao(notificationDate);

      // transactionsService.update(transactions.getId(), transactions);

      // response.put("status", true);
      // response.put("message", "Notificação processada com sucesso!");

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
