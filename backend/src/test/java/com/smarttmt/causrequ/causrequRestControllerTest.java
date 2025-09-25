package com.smarttmt.causrequ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.paraoper.ParaoperIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
@WebMvcTest(CausrequController.class)
public class causrequRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParaoperIService iServiceGenericParaoper;

    @MockBean
    CausrequIService causrequIService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Disabled
    @Test
    void getCausasDevolucionTest() throws Exception {

        //given
        ListaValoresModel listValo = new ListaValoresModel("sdsdsd","causa");
        List<ListaValoresModel> listCausrequ = new ArrayList<>();
        listCausrequ.add(listValo);
        String token = "##E$%%3";
        String cliente = "123";
        String idToken = "1234";

        when(iServiceGenericParaoper.getIdTokeWs()).thenReturn(idToken);
        when(causrequIService.getCausasDevolucion()).thenReturn(listCausrequ);

        //when
        mockMvc.perform(
                        get("/causasDevolucion/listVal?cliente=" + cliente)
                                .header("token", token)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCausrequ)))
                .andExpect(jsonPath("$[0].carecodi").value("sdsdsd"));

    }

}
