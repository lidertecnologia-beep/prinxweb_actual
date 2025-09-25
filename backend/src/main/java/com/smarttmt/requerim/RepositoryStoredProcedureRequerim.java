package com.smarttmt.requerim;

import com.smarttmt.utilities.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Repository
public class RepositoryStoredProcedureRequerim {

    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public List getResultListEstadisticasDetalle(String cliente, String estado) {
        StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("getEstadisticasDetalle");
        storedProcedure.setParameter(Constantes.PARAMETRO_SBCLIENTE, cliente);
        storedProcedure.setParameter(Constantes.PARAMETRO_SBESTADO, estado);
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }

    public Integer getTotalRegistroEstadisticasDetalle(String cliente, String estado, Integer requcodi, String requfech, String requfeco) {
        StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("getTotalEstadisticasDetalle");
        storedProcedure.setParameter(Constantes.PARAMETRO_SBCLIENTE, cliente);
        storedProcedure.setParameter(Constantes.PARAMETRO_SBESTADO, estado);
        storedProcedure.setParameter("inRequCodi", requcodi);
        storedProcedure.setParameter("sbRequFech", requfech);
        storedProcedure.setParameter("sbRequFeco", requfeco);
        storedProcedure.setParameter("sbSistema", "MCRE");
        storedProcedure.execute();
        return (Integer) storedProcedure.getOutputParameterValue("inTotalRegi");
    }

    public List getResultListEstadisticasDetalleReporte(String cliente, String estado, Integer requcodi, String requfech, String requfeco, Integer page, Integer size) {
        StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("getEstadisticasDetalleReporte");
        storedProcedure.setParameter(Constantes.PARAMETRO_SBCLIENTE, cliente);
        storedProcedure.setParameter(Constantes.PARAMETRO_SBESTADO, estado);
        storedProcedure.setParameter("inRequCodi", requcodi);
        storedProcedure.setParameter("sbRequFech", requfech);
        storedProcedure.setParameter("sbRequFeco", requfeco);
        storedProcedure.setParameter("sbSistema", "MCRE");
        storedProcedure.setParameter("inPagina", page);
        storedProcedure.setParameter("inRows", size);
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }

    @Transactional(readOnly = true)
    public String getCantidadEstadosCliente(String cliente) {

        if (cliente == null || cliente.isEmpty()) {
            return null;
        }

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgrequerim")
                .withFunctionName("fun_cantiestad_modulo_clientes")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter(Constantes.PARAMETRO_RETURN, Types.VARCHAR),
                        new SqlParameter("sbCliente", Types.VARCHAR));

        SqlParameterSource sqlParamters = new MapSqlParameterSource()
                .addValue("sbCliente", cliente);

        Map<String, Object> mapRetorno = call.execute(sqlParamters);
        String retorno = (String) mapRetorno.get("return");

        return StringUtils.isEmpty(retorno) ? null : retorno;

    }

    @Transactional(readOnly = true)
    public String crearRequerim(String itemsSolucion, String itemsPrioridaAtencion) {

        log.info("init crearRequerim itemsSolucion:{} itemsPrioridaAtencion:{}", itemsSolucion, itemsPrioridaAtencion);

        if (StringUtils.isEmpty(itemsSolucion) || StringUtils.isEmpty(itemsPrioridaAtencion)) {
            throw new IllegalArgumentException("Es requerido los parametros itemsSolucion y itemsPrioridaAtencion");
        }

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("Pkgsolucion")
                .withFunctionName("fun_crear_modulo_cliente")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter(Constantes.PARAMETRO_RETURN, Types.VARCHAR),
                        new SqlParameter("sbValoRegi", Types.VARCHAR),
                        new SqlParameter("sbPrioAtan", Types.VARCHAR));

        SqlParameterSource sqlParamters = new MapSqlParameterSource()
                .addValue("sbValoRegi", itemsSolucion)
                .addValue("sbPrioAtan", itemsPrioridaAtencion);

        Map<String, Object> mapRetorno = call.execute(sqlParamters);
        String retorno = (String) mapRetorno.get(Constantes.PARAMETRO_RETURN);
        return (StringUtils.isEmpty(retorno)) ? null : retorno;

    }


    @Transactional
    public String eliminarRequerim(Integer idSolicitud) {
        log.info("init deleteRequerim idSolicitud:{} ", idSolicitud);

        idSolicitud = Optional.ofNullable(idSolicitud).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro requcodi"));

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("Pkgsolucion")
                .withFunctionName("fun_Delete_Solucion")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(new SqlOutParameter(Constantes.PARAMETRO_RETURN, Types.VARCHAR), new SqlParameter("sbSoluCodi", Types.VARCHAR));

        SqlParameterSource sqlParamters = new MapSqlParameterSource().addValue("sbSoluCodi", idSolicitud);

        Map<String, Object> mapRetorno = call.execute(sqlParamters);
        String retorno = (String) mapRetorno.get(Constantes.PARAMETRO_RETURN);
        return (StringUtils.isEmpty(retorno)) ? null : retorno;

    }

}
