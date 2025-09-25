package com.smarttmt.requerim;

import java.util.Date;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface IRepositoryRequerimSinRelaciones extends JpaRepository<EntityVwRequerimSinRelaciones, Integer>, JpaSpecificationExecutor<EntityVwRequerimSinRelaciones> {

    @Override
    List<EntityVwRequerimSinRelaciones> findAll(Specification<EntityVwRequerimSinRelaciones> sec);

    @Query("select r from EntityVwRequerimSinRelaciones r where r.requclie = ?1 and r.requesta IN (?2)")
    List<EntityVwRequerimSinRelaciones> findListVwRequerim(String cliente, List<String> listEstadoPendiente);

    @Query("select r from EntityVwRequerimSinRelaciones r where r.requclie = ?1 and r.requfech between ?2 and ?3 order by r.requfech desc")
    List<EntityVwRequerimSinRelaciones> findListVwRequerimRangoFechas(String cliente, Date fechaInicial, Date fechaFinal);

}
