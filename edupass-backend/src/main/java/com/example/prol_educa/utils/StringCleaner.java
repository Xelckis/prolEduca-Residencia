package com.example.prol_educa.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCleaner {

  /**
   * Limpa uma string mantendo apenas palavras com letras (acentuadas inclusive),
   * com pelo menos duas palavras separadas por espaço.
   *
   * @param input A string original
   * @return A string limpa ou null se não for válida
   */
  public static String cleanName(String input) {
    if (input == null) {
      return null;
    }

    String trimmed = input.trim();

    Pattern pattern = Pattern.compile("^[ ]*([A-Za-zÀ-ÿ]+[ ]+)+[A-Za-zÀ-ÿ]+[ ]*$");
    Matcher matcher = pattern.matcher(trimmed);

    if (matcher.matches()) {
      return trimmed;
    } else {
      return null;
    }
  }

  /**
   * Remove pontos, traços, parênteses, barras e espaços de um CNPJ ou string
   * similar.
   *
   * Ex: "12.345.678/0001-90" → "12345678000190"
   *
   * @param cnpj A string com CNPJ formatado
   * @return A string apenas com números
   */
  public static String cleanCNPJ(String cnpj) {
    if (cnpj == null) {
      return null;
    }
    
    return cnpj.replaceAll("[.\\-()/\\s]", "");
  }
}