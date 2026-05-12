package com.example.prol_educa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtils {

  /**
   * Converte um valor monetário em reais (double) para centavos (long).
   *
   * Ex: 1.00 -> 100, 189.87 -> 18987
   *
   * @param real o valor em reais
   * @return o valor em centavos
   */
  public static Integer convertToCents(Number real) {
    BigDecimal value = new BigDecimal(real.toString());
    BigDecimal cents = value.multiply(BigDecimal.valueOf(100));

    return cents.setScale(0, RoundingMode.HALF_EVEN).intValueExact();
  }
}
