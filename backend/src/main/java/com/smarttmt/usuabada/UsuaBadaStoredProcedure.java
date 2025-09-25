package com.smarttmt.usuabada;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.Map;

@Slf4j
@Repository
public class UsuaBadaStoredProcedure implements UsuaBadaIStoredProcedure {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuaBadaStoredProcedure(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getAutenticacion(String us, String pw) {
        log.info("init getAutenticacion");

        if(us == null || pw == null || us.isEmpty() || pw.isEmpty()){
            return "false";
        }

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgUsuabada")
                .withFunctionName("fun_autenticacion")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter("return", Types.VARCHAR),
                        new SqlParameter("sbUsbdCodi", Types.VARCHAR),
                        new SqlParameter("sbUsbdPass", Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sbUsbdCodi", us)
                .addValue("sbUsbdPass", pw);

        Map<String, Object> mapRetorno = call.execute(in);
        return  (String) mapRetorno.get("return");
    }

}
