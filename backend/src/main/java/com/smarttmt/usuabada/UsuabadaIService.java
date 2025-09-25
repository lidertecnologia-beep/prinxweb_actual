package com.smarttmt.usuabada;

import com.fasterxml.jackson.databind.JsonNode;

public interface UsuabadaIService {
    JsonNode validarAuth(String authString) throws Exception;

}
