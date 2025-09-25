package com.smarttmt.mantprod;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MantprodServiceTest {

    @Autowired
    MantprodIService mantprodIService;

    @MockBean
    MantprodIRepository mantprodIRepository;

    @Test
    void testGetMantprod() {
        String cliente = "890399046";
        Integer numeroSolicitud = 20210561;
        List<MantprodEntity> listMantprodVacio = Collections.emptyList();
        MantprodEntity mantprodNotNull = new MantprodEntity();
        mantprodNotNull.setMaprrequ(numeroSolicitud);
        List<MantprodEntity> listMantprodNotNull = new ArrayList<>();
        listMantprodNotNull.add(mantprodNotNull);

        when(mantprodIRepository.getListMantprod(cliente,0)).thenReturn(listMantprodVacio);
        when(mantprodIRepository.getListMantprod(cliente,numeroSolicitud)).thenReturn(listMantprodNotNull);

        Exception exception = assertThrows(RecursoNoEncontradoException.class, () -> mantprodIService.getListMantprod(cliente,null));
        List<MantprodEntity> listValorRetornoVacio = mantprodIService.getListMantprod(cliente,0);
        List<MantprodEntity> listValorRetornoNotNull = mantprodIService.getListMantprod(cliente,numeroSolicitud);

        assertEquals(exception.getClass(), RecursoNoEncontradoException.class);
        assertTrue(listValorRetornoVacio.isEmpty());
        assertNotNull(listValorRetornoNotNull);
        listValorRetornoNotNull.forEach(mantprod -> assertEquals(mantprod.getMaprrequ(),numeroSolicitud));

        verify(mantprodIRepository).getListMantprod(cliente,0);
        verify(mantprodIRepository).getListMantprod(cliente,numeroSolicitud);


    }
}