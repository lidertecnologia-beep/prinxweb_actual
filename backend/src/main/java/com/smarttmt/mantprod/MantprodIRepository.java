package com.smarttmt.mantprod;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MantprodIRepository extends CrudRepository<MantprodEntity,Integer> {

    @Query("select m from MantprodEntity m where m.maprrequ = ?2 and exists (select 1 from EntityRequerim r where r.requclie = ?1 and r.requcodi = m.maprrequ)")
    List<MantprodEntity> getListMantprod(String cliente, Integer numeroRequerim);

}
