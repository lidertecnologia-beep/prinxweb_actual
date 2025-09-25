package com.smarttmt.personal;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonalIRespository extends CrudRepository<PersonalEntity, String> {

    Optional<PersonalEntity> getPersonalEntityByPersusua(String us);

}
