package com.example.prol_educa.config;

import java.util.HashMap;
import java.util.Map;

public class EfiConfig {
  public static Map<String, Object> getOptions() {
    Map<String, Object> options = new HashMap<>();

    options.put("client_id", "Client_Id_5d32f5203990c8c6c42d38a4b781218e065cb37d");
    options.put("client_secret", "Client_Secret_88001aa2426a50c9621dbe1520ffd257c80143a3");
    options.put("certificate", "certs/homologacao-98758-prol-edupass.p12");
    options.put("sandbox", true); // true para homologação e false para produção

    return options;
  }
}