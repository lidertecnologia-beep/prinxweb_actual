package com.smarttmt.estado;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EstadoServiceTest {

    @Autowired
    EstadoIService estadoIService;

    @Disabled
    @Test
    void testGetListEstadoModificado() {

        List<EstadoModificadosRecord> list = estadoIService.getListEstadoModificado();

        list.forEach(e -> System.out.println(e));

        assertTrue(!list.isEmpty());

    }

}