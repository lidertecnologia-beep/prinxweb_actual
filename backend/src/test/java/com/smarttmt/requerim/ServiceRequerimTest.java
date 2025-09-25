package com.smarttmt.requerim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceRequerimTest {

    @Autowired
    ServiceRequerim serviceRequerim;

    @DisplayName("Test a clase ServiceRequerim metodo requerimPendiente que valida estados pendientes")
    @Test
    void requerimPendiente() {
        Assertions.assertEquals("S",serviceRequerim.requerimPendiente("ac"));
        Assertions.assertEquals("N",serviceRequerim.requerimPendiente(null));
        Assertions.assertEquals("N",serviceRequerim.requerimPendiente("xxx"));
    }

}