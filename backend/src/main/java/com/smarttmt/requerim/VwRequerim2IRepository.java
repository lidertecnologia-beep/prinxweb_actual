package com.smarttmt.requerim;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VwRequerim2IRepository extends JpaRepository<VwRequerim2Entity,Integer> {

    public List<VwRequerim2Entity> findByRequclie(String cliente);

    public List<VwRequerim2Entity> findByRequclieAndEstacodi(String cliente,String estacodi);

}
