package com.smarttmt.estado;

import com.smarttmt.paraoper.ParaoperIService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class EstadoService implements EstadoIService {

    private final EstadoIRepositoryStoredProcedure iEstadoRepositoryStoredProcedure;

    public List<EstadoModificadosRecord> getListEstadoModificado() {
        log.info("init getListEstadoModificado");
        List<EstadoModificadosRecord> listEstados = new ArrayList<>();
        List<EstadoDetalle> listEstadoDetalle = iEstadoRepositoryStoredProcedure.getListEstadosDetalle("");
        listEstadoDetalle.forEach(estado -> listEstados.add(new EstadoModificadosRecord(estado.getCodigo(), estado.getDescripcion())));
        return listEstados;
    }

    public List getListEstadosDetalle(String cliente) {
        log.info("init getListEstadosDetall cliente:{}", cliente);
        return cliente.isEmpty() ?  Collections.emptyList() : iEstadoRepositoryStoredProcedure.getListEstadosDetalle(cliente);
    }

}
