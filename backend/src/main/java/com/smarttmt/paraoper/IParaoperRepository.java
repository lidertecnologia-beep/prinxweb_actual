package com.smarttmt.paraoper;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IParaoperRepository extends JpaRepository<Paraoper, String> {

    Paraoper getReferenceById(String cliente);

}
