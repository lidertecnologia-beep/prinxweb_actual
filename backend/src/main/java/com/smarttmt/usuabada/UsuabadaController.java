package com.smarttmt.usuabada;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Validated
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
@RestController
public class UsuabadaController {

    private final UsuabadaIService usuabadaIService;
    private final String AUTH_STRING_NULL = "Basic Og==";

    @PostMapping
    public ResponseEntity<?> validarUsuabada(
            @RequestHeader("Authorization") String authString) throws Exception {

        log.info("init validarUsuabada ");


        if (authString == null || AUTH_STRING_NULL.equals(authString)) {
            return ResponseEntity.badRequest().build();
        }

        JsonNode jsonNode = usuabadaIService.validarAuth(authString);
        if (jsonNode != null) {
            return ResponseEntity.ok(ResponseEntity.ok(usuabadaIService.validarAuth(authString)));
        }

        return ResponseEntity.notFound().build();
    }

}
