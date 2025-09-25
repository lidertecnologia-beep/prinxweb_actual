package com.smarttmt.tiporequ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TiporequIRepository extends JpaRepository<TiporequEntity, String> {

    @Query("select t from TiporequEntity t where t.tiretire = ?1 and t.tirecodi = ?2")
    Optional<TiporequEntity> getClasificaionTipoRequirimeinto(String tiretire, String tirecodi);

}
