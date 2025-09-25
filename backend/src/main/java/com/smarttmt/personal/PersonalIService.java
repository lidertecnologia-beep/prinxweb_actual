
package com.smarttmt.personal;


public interface PersonalIService {
    
    String actualizar(PersonalRecord personalRecord);

    PersonalEntity getPersonalPorUsuario(String us) throws Exception;

    PersonalEntity getPersonal(String idPersonal);
}
