package com.smarttmt.objeprod;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjeprodRepository extends CrudRepository<ObjeprodEntity,ObjeprodPK> {

    @Query("SELECT o.objeprodPK.obprobje as codigo, o.obprdesc as descripcion,o.objeprodPK.obprprod " +
            "FROM ObjeprodEntity o " +
            " WHERE o.objeprodPK.obprprod = :producto AND o.obprclas = :claseObjeto order by  o.obprclas, o.objeprodPK.obprobje")
    List<Object[]> findObjectoProduco(String producto,String claseObjeto);

}
