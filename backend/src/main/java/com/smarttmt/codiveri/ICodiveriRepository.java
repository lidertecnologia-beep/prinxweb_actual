package com.smarttmt.codiveri;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICodiveriRepository extends JpaRepository<Codiveri, Long> {


    @Query(value = "select pkgCodiVeri.fun_Validar_CodiVeri(:sbCodigo, :sbCoveTerc,:sbCoveUssi,:sbCoveMail) from dual", nativeQuery = true)
    String validarCodiveri(@Param("sbCodigo") String codigo, @Param("sbCoveTerc") String tercero,
                           @Param("sbCoveUssi") String us, @Param("sbCoveMail") String mail);

}
