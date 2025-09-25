package com.smarttmt.usuabada;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuabadaIRepository extends CrudRepository<UsuabadaEntity,String> {
}
