package com.smarttmt.requerim;

import com.smarttmt.utilities.EnumEstadosPendientes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServiceRequerimSinRelaciones implements IServiceRequerimSinRelacion<EntityVwRequerimSinRelaciones> {

    Logger logger = LoggerFactory.getLogger(ServiceRequerimSinRelaciones.class);

    protected IRepositoryRequerimSinRelaciones iRequerimRepositorySinRelaciones;

    @Autowired
    public ServiceRequerimSinRelaciones(IRepositoryRequerimSinRelaciones iRequerimRepositorySinRelaciones) {
        this.iRequerimRepositorySinRelaciones = iRequerimRepositorySinRelaciones;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntityVwRequerimSinRelaciones> getAlltByClienteReporte(String cliente) {
        logger.info("init getAllRequerimByClientPege  cliente:{} page:{}", cliente);
        List<String> listEstadoPendientes = Arrays.asList(
                EnumEstadosPendientes.ACTIVO.getCodigo(),
                EnumEstadosPendientes.ASIGNADO.getCodigo(),
                EnumEstadosPendientes.CONSTRUCION_DESARROLLO.getCodigo(),
                EnumEstadosPendientes.ANALISIS.getCodigo(),
                EnumEstadosPendientes.PRUEBAS.getCodigo(),
                EnumEstadosPendientes.DISENO.getCodigo(),
                EnumEstadosPendientes.DOCUMENTACION.getCodigo(),
                EnumEstadosPendientes.PENDIENTE.getCodigo(),
                EnumEstadosPendientes.PROGRAMADO.getCodigo());
        logger.info("listEstadoPendientes:{}", listEstadoPendientes);
        return iRequerimRepositorySinRelaciones.findListVwRequerim(cliente, listEstadoPendientes);
    }

    @Override
    public List<EntityVwRequerimSinRelaciones> getAllRequerimRangoFechasReporte(String cliente, Date fechaInicial, Date fechaFinal) {
        logger.info("getAllRequerimRangoFechas cliente:{} fechaInicial:{} fechaFinal:{} page:{}", cliente, fechaInicial, fechaFinal);
        return iRequerimRepositorySinRelaciones.findListVwRequerimRangoFechas(cliente, fechaInicial, fechaFinal);
    }

}
