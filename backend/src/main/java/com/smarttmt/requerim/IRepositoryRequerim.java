package com.smarttmt.requerim;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRepositoryRequerim extends JpaRepository<EntityVwRequerim, Integer>, JpaSpecificationExecutor<EntityVwRequerim> {

    @Query("select r from EntityVwRequerim r where r.requclie = ?1")
    List<RequerimITablaDTO> findListVwRequerim(String cliente);

    @Query("select r from EntityRequerim r where r.requclie = ?1 ")
    List<EntityRequerim> getAllRequerimByCliente(String cliente);

    @Query("select r from EntityRequerim r where r.requclie = ?1 and r.requcodi = ?2")
    Optional<EntityRequerim> getAllRequerimByClienteAndId(String cliente, Integer id);

    @Procedure(name = "getVwRequerim")
    Page<EntityVwRequerim> getAllRequerimByClientePagable(@Param("sbCliente") String sbCliente, @Param("sbFechInic") String sbFechInic, @Param("sbFechFina") String sbFechFina, @Param("sbEstado") String sbEstado, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requclie = ?1 and r.requcodi = ?2 order by r.requfech desc")
    Page<EntityVwRequerim> findListVwRequerimFiltered(String cliente, Integer numeroRequerim, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requclie = ?1 and r.requfech  =  ?2  order by r.requfech desc")
    Page<EntityVwRequerim> findListVwRequerimRequfechFiltered(String cliente, Date fechRequerim, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requclie = ?1 and r.requfeco = ?2  order by r.requfech desc")
    Page<EntityVwRequerim> findListVwRequerimRequfecoFiltered(String cliente, Date fechCompromiso, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requclie = ?1 and r.requesta  = ?2 order by r.requfech desc")
    Page<EntityVwRequerim> findListVwRequerimRequestaFiltered(String cliente, String estado, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requclie = ?1 and r.requfech between ?2 and ?3 order by r.requfech desc")
    List<EntityVwRequerim> findListVwRequerimRangoFechas(String cliente, Date fechaInicial, Date fechaFinal);

    @Override
    Page<EntityVwRequerim> findAll(Specification<EntityVwRequerim> sec, Pageable pageable);

    @Query("select r from EntityVwRequerim r where r.requcodi = ?1")
    EntityVwRequerim findByRequcodi(Integer numeroRequerim);

}
