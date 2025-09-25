package com.smarttmt.usuabada;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuabadaServiceTest {

    @Autowired
    UsuabadaIService usuabadaIService;

    @Test
    void testGetUsuabadaPorUsuario() throws Exception {

        JsonNode jsonNode = usuabadaIService.validarAuth("Q19BTENKQU0wMDE6Q19BTENKQU0wMDEk");

        assertNotNull(jsonNode);
        assertEquals(jsonNode.get("personalIniciales"), "A.");

    }


}