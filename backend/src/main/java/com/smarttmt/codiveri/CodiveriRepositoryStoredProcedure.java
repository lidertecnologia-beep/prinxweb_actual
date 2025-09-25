package com.smarttmt.codiveri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
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
import java.sql.Types;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodiveriRepositoryStoredProcedure implements ICodiveriRepositoryStoredProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String crearCodiveri(String param) {
        StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("crearCodiveri");
        storedProcedure.setParameter("sbValoRegr", param);
        storedProcedure.execute();
        return (String) storedProcedure.getOutputParameterValue("sbRetorno");
    }

    @Override
    public String validarCodiveri(String codigo, String tercero, String us, String email) {

        log.info("init validarCodiveri valida tag codigo:{} tercero:{} us:{} email:{}",codigo,tercero,us,email);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgCodiVeri")
                .withFunctionName("fun_Validar_CodiVeri")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter("sbRetorno", Types.VARCHAR),
                        new SqlParameter("sbCodigo", Types.VARCHAR),
                        new SqlParameter("sbCoveTerc", Types.VARCHAR),
                        new SqlParameter("sbCoveUssi", Types.VARCHAR),
                        new SqlParameter("sbCoveMail", Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sbCodigo", codigo)
                .addValue("sbCoveTerc", tercero)
                .addValue("sbCoveUssi", us)
                .addValue("sbCoveMail", email);

        Map<String, Object> result = call.execute(in);
        log.info("sbRetorno :{}", (String) result.get("sbRetorno"));
        return (String) result.get("sbRetorno");
    }

}

