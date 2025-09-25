package com.smarttmt.anexos;

import com.smarttmt.excepciones.ExcepcionNoControlada;
import com.smarttmt.excepciones.TransaccionBaseDatosException;
import com.smarttmt.utilities.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class AnexsoliRepositoryStoredProcedure implements AnexsoliIRepositoryStoredProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public String crear(String sbValoRegr) {
        log.info("init crear sbValoRegr:{} ", sbValoRegr);
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("pkganexsoli.pro_crear");
            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedure.setParameter(1, sbValoRegr);
            storedProcedure.setParameter(2, "S");
            storedProcedure.execute();
            return "ok";
        } catch (Exception ex) {
            log.error("Error crear Exception:{} ", ex.toString());
            throw new TransaccionBaseDatosException("Error al crear registro");
        }

    }

    @Transactional
    @Override
    public String borrar(Integer inAnsoSolu, Integer inAnsoRequ) {
        log.info("init borra inAnsoSolu:{} inAnsoRequ:{}", inAnsoSolu, inAnsoRequ );
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("pkganexsoli.pro_delete_anexsolo_requerim");
            storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedure.setParameter(1, inAnsoSolu);
            storedProcedure.setParameter(2, inAnsoRequ);
            storedProcedure.setParameter(3, "S");
            storedProcedure.execute();
            return "ok";
        } catch (Exception ex) {
            log.error("Error borrar Exception:{} ", ex.toString());
            throw new TransaccionBaseDatosException("Error al borrar registro");
        }
    }

}
