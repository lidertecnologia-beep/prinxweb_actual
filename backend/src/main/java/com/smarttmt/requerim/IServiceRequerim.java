package com.smarttmt.requerim;

import com.smarttmt.modelos.RequerimCierreModel;
import com.smarttmt.modelos.RequerimCreateModel;
import com.smarttmt.modelos.RequerimDevolucionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceRequerim<T> {

    List<T> getAlltByCliente(String cliente);

    Map<String, Object> getAlltByClienteMap(String cliente, Pageable page);

    Page<EntityVwRequerim> getAlltByRequcodi(String cliente, Integer numeroRequerim, Pageable page);

    Page<EntityVwRequerim> getAlltByRequfech(String cliente, Date fechRequerim, Pageable page);

    Page<EntityVwRequerim> getAlltByRequfeco(String cliente, Date fechCompromiso, Pageable page);

    Page<EntityVwRequerim> getAlltByRequesta(String cliente, String estado, Pageable page);

    List<VwRequerim2Entity> getEstadisticasDetalle(String cliente, String estado);

    List<EntityVwRequerimSinRelaciones> getEstadisticasDetalleReporte(String cliente, String estado, Integer requcodi, String requfech, String requfeco);

    List<EntityVwRequerim> getAllRequerimRangoFechas(String cliente, Date fechaInicial, Date fechaFinal);

    Page<EntityVwRequerim> getAlltWithFilter(Specification<EntityVwRequerim> specification, Pageable page);

    List<EntityVwRequerimSinRelaciones> getAlltWithFilterReporte(Specification<EntityVwRequerimSinRelaciones> specification);

    Map<String, Object> getColumVwRequerimMap();
    
    boolean isExisteRequerim(String requcodi);

    String requerimPendiente(String estado);

    RequerimDetalleDTO getDetalle(String cliente, Integer numeroRequerim);

    Map<String, String> getCantidaEstadosPorCliente(String cliente);

    String crear(RequerimCreateModel requerim,String clienteIn,String personal);

    boolean cerrar(RequerimCierreModel requerimIn);

    boolean devolucion(RequerimDevolucionModel requerimIn);

    String eliminar(String requcodiIn);

}
