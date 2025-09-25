package com.smarttmt.personal;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.utilities.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PersonalService implements PersonalIService {

    private final PersonalIRespository personalIRespository;

    @Autowired
    public PersonalService(PersonalIRespository personalIRespository) {
        this.personalIRespository = personalIRespository;
    }

    public PersonalEntity getPersonal(String idPersonal) {
        log.info("init personal idPersonal: {}", idPersonal);
        Optional<PersonalEntity> optionalPersonalEntity = personalIRespository.findById(idPersonal);
        return optionalPersonalEntity.orElseThrow(() -> new RecursoNoEncontradoException("No se encotro el personal con id:" + idPersonal));
    }

    @Override
    public String actualizar(PersonalRecord personalRecord) {
        log.info("init actualizar");
        String error = null;
        try {
            if (personalRecord != null) {
                Optional<PersonalEntity> optionalPersonalEntity = personalIRespository.findById(personalRecord.perscodi());
                if (optionalPersonalEntity.isPresent()) {
                    PersonalEntity personal = optionalPersonalEntity.get();
                    personal.setPersnomb(personalRecord.persnomb());
                    personal.setPersemai(personalRecord.persemai());
                    personal.setPerstele(personalRecord.perstele());
                    personal.setPersapel(personalRecord.persapel());
                    personalIRespository.save(personal);
                    return Constantes.MENSAJE_RESPUESTA_OK;
                }
            }
        } catch (Exception ex) {
            error = "Error actualizar Exception:{}" + ex.toString();
            log.error("Error actualizar Exception:{}", ex.toString());
        }
        return error;
    }

    @Override
    public PersonalEntity getPersonalPorUsuario(String us) throws Exception {
        log.info("init getPersonalPorUsuario us:{}",us);
        return personalIRespository.getPersonalEntityByPersusua(us).orElse(null);
    }


}
