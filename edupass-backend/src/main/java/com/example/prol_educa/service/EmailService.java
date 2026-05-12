package com.example.prol_educa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.prol_educa.models.RequestSupportDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void enviarCodigoRecuperacao(String para, String codigo) {
    SimpleMailMessage mensagem = new SimpleMailMessage();
    mensagem.setTo(para);
    mensagem.setFrom("sistema@proleduca.com.br");
    mensagem.setSubject("Código de Recuperação de Senha");
    mensagem.setText("Seu código de recuperação é: " + codigo);

    mailSender.send(mensagem);
  }

  public void sendEmail(String to, String subject, String body) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom("sistema@proleduca.com.br");
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(body, true);

      mailSender.send(message);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
    }
  }

  public void sendSupportRequest(RequestSupportDto request) {
    String to = "sistema@proleduca.com.br";
    String subject = "📩 Nova Solicitação de Suporte";

    String body = """
            <div style="font-family: Arial, sans-serif; font-size: 14px; color: #333;">
              <h2 style="color: #30ADE7;">📌 Nova Solicitação de Suporte</h2>

              <p><strong>👤 Nome:</strong> %s</p>
              <p><strong>📧 E-mail:</strong> %s</p>
              <p><strong>📂 Tipo da Solicitação:</strong> %s</p>
              <p><strong>📝 Assunto:</strong> %s</p>

              <p><strong>🧾 Descrição:</strong></p>
              <p style="white-space: pre-line;">%s</p>

              <hr>
              <p style="font-size: 12px; color: #888;">
                  Este e-mail foi gerado automaticamente pelo sistema de suporte.
              </p>
            </div>
        """.formatted(
          request.getFullName(),
          request.getEmail(),
          request.getRequestType(),
          request.getSubject(),
          request.getDescription()
        );

    sendEmail(to, subject, body);
  }

}