package com.smarttmt.utilities;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ResponseBindigResult {

    public static ResponseEntity<Map<String, String>> getResponseMapErrors(BindingResult bindingResult) {
        Map<String, String> mapErrores = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> mapErrores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.accepted().body(mapErrores);
    }

}
