package com.example.prol_educa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.service.BilletPaymentService;
import com.example.prol_educa.service.CardPaymentService;
import com.example.prol_educa.service.PixPaymentService;
import com.example.prol_educa.service.ChargeService;
import com.example.prol_educa.service.ConfigWebhookPix;

@RestController
@RequestMapping("/payment")
public class PaymentsController {

  @Autowired
  private CardPaymentService cardPaymentService;

  @Autowired
  private BilletPaymentService billetPaymentService;

  @Autowired
  private PixPaymentService pixPaymentService;

  @Autowired
  private ChargeService chargeService;

  @Autowired
  private ConfigWebhookPix configWebhookPix;

  @PostMapping("/card")
  public ResponseEntity<?> cardPayment(@RequestBody Map<String, Object> requestBody) {
    try {
      Map<String, Object> response = cardPaymentService.createCardCharge(requestBody);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
  }

  @PostMapping("/billet")
  public ResponseEntity<?> billetPayment(@RequestBody Map<String, Object> body) {
    try {
      Map<String, Object> response = billetPaymentService.createBilletCharge(body);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
  }

  @PostMapping("/pix")
  public ResponseEntity<?> pixPayment(@RequestBody Map<String, Object> body) {
    try {
      Map<String, Object> response = pixPaymentService.createPixCharge(body);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
  }

  @PostMapping("/process")
  public ResponseEntity<?> processResponse(@RequestParam Map<String, String> formParams) {
    try {
      // Map<String, Object> response = pixPaymentService.createPixCharge(body);
      Map<String, Object> response = chargeService.process(formParams);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
  }

  @GetMapping("/config/webhook/pix")
  public ResponseEntity<?> configWebhookPix() {
    try {
      Map<String, Object> response = configWebhookPix.config();
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("erro", e.getMessage()));
    }
  }
}