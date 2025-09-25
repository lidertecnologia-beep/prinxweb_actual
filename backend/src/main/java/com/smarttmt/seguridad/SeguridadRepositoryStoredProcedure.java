package com.smarttmt.seguridad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeguridadRepositoryStoredProcedure implements ISeguridadRepositoryStoredProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String cambiarClave(String us, String email, String pw) {
        log.info("init cambiarClave us:{} email:{}", us, email);

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("pkgCodiVeri.pro_Cambio_Clave");
        storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
        storedProcedure.setParameter(1, us);
        storedProcedure.setParameter(2, email);
        storedProcedure.setParameter(3, pw);
        //
        storedProcedure.execute();
        String resultado = (String) storedProcedure.getOutputParameterValue(4);
        log.info("cambiarClave resultado:{}", resultado);
        return resultado;
    }
}
