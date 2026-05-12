package com.example.prol_educa.entities;

import java.time.OffsetDateTime;

import com.example.prol_educa.utils.enuns.EPaymentMethods;
import com.example.prol_educa.utils.enuns.EStatusTransactions;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transacoes")
public class Transactions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "empresa_id", nullable = false)
  private Companies empresaId;

  @Column(name = "cobranca_id", nullable = false)
  private Integer cobrancaId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private EPaymentMethods metodoPagamento;

  @Column
  private Float valor;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "data_cobranca")
  private OffsetDateTime dataCobranca;

  @NotNull
  @Enumerated(EnumType.STRING)
  private EStatusTransactions status;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "data_notificacao")
  private OffsetDateTime dataNotificacao;

  public Transactions() {
  }

  public Transactions(Companies empresaId, Integer cobrancaId, EPaymentMethods metodoPagamento, Float valor,
      OffsetDateTime dataCobranca, EStatusTransactions status, OffsetDateTime dataNotificacao) {
    this.empresaId = empresaId;
    this.cobrancaId = cobrancaId;
    this.metodoPagamento = metodoPagamento;
    this.valor = valor;
    this.dataCobranca = dataCobranca;
    this.status = status;
    this.dataNotificacao = dataNotificacao;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Companies getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(Companies empresaId) {
    this.empresaId = empresaId;
  }

  public Integer getCobrancaId() {
    return cobrancaId;
  }

  public void setCobrancaId(Integer cobrancaId) {
    this.cobrancaId = cobrancaId;
  }

  public EPaymentMethods getMetodoPagamento() {
    return metodoPagamento;
  }

  public void setMetodoPagamento(EPaymentMethods metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }

  public Float getValor() {
    return valor;
  }

  public void setValor(Float valor) {
    this.valor = valor;
  }

  public OffsetDateTime getDataCobranca() {
    return dataCobranca;
  }

  public void setDataCobranca(OffsetDateTime dataCobranca) {
    this.dataCobranca = dataCobranca;
  }

  public EStatusTransactions getStatus() {
    return status;
  }

  public void setStatus(EStatusTransactions status) {
    this.status = status;
  }

  public OffsetDateTime getDataNotificacao() {
    return dataNotificacao;
  }

  public void setDataNotificacao(OffsetDateTime dataNotificacao) {
    this.dataNotificacao = dataNotificacao;
  }

}
