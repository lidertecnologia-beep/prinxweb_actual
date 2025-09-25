package com.smarttmt.producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoIRepository extends CrudRepository<ProductoEntity,String> {

    @Query("SELECT c.clprprod as codigo, p.proddesc as descripcion " +
            "FROM ProductoEntity p " +
            " JOIN ClieprodEntity c ON  p.prodcodi = c.clprprod  WHERE c.clprclie = :cliente AND c.clprespr = :estadoActivo AND c.clpresco =:estadoActivo ")
    List<Object[]> findProductoByCliente(String cliente,String estadoActivo);
}