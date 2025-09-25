package com.smarttmt.tercero;

import java.sql.Types;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceTercero implements IServiceTercero {

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceTercero(
            JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getItemsTercero(String tercero) {
        
        log.info("init getItemsTercero tercero:{}", tercero);
        
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgtercero")
                .withFunctionName("fun_Tercero_MIG")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter("return", Types.VARCHAR),
                        new SqlParameter("sbTipoBusc", Types.VARCHAR),
                        new SqlParameter("sbTercCodi", Types.VARCHAR),
                        new SqlOutParameter("sbValoRegr", Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sbTipoBusc", "D")
                .addValue("sbTercCodi", tercero)
                .addValue("sbValoRegr", null);

        Map<String, Object> mapRetorno = call.execute(in);
        String retorno = (String) mapRetorno.get("return");
        if (retorno != null && !retorno.isEmpty()) {
            return (String) mapRetorno.get("sbValoRegr");
        }

        return null;
    }

}
