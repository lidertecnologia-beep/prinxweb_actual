package com.smarttmt.anexos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IAnexsoliGenericRepository<T, Integer> extends JpaRepository<T, Integer> {

    @Query("select a from Anexsoli2 a where a.ansosolu = ?1 and a.ansoarch = ?2")
    Optional<T> getAnexsoliByAnsosoluAnAndAnsoarch(Integer ansosolu, String ansoarch);

}
