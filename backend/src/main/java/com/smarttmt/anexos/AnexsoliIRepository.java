package com.smarttmt.anexos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexsoliIRepository extends CrudRepository<Anexsoli, AnexsoliEntityPK> {

    @Query("select m from Anexsoli m where m.id.ansosolu = ?1")
    List<Anexsoli> getListAnexsoli(java.lang.Integer numeroRequerim);

}