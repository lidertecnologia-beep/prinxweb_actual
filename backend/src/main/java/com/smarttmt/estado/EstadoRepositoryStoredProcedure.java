package com.smarttmt.estado;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstadoRepositoryStoredProcedure implements EstadoIRepositoryStoredProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Transactional(readOnly = true)
    @Override
    public List getListEstadosDetalle(String cliente) {

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgestado")
                .withProcedureName("pro_detalle_estado_moducliente")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("sbCliente", Types.VARCHAR),
                        new SqlOutParameter("rgRegistro", Types.REF_CURSOR)
                );

        SqlParameterSource sqlParamters = new MapSqlParameterSource()
                .addValue("sbCliente", cliente);

        Map<String, Object> result = call.execute(sqlParamters);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) result.get("rgRegistro");

        List<EstadoDetalle> listEstadoDetalle = new ArrayList<>();

        for (Map<String, Object> row : resultList) {
            listEstadoDetalle.add(
                    EstadoDetalle
                            .builder()
                            .codigo((String) row.get("CODIGO"))
                            .descripcion((String) row.get("DESCRIPCION"))
                            .cantidad((String) row.get("CANTIDAD"))
                            .color((String) row.get("COLOR"))
                            .hover((String) row.get("HOVER"))
                            .item((String) row.get("ITEM"))
                            .labellink((String) row.get("LABELLINK"))
                            .build());
        }

        return listEstadoDetalle;


    }

}