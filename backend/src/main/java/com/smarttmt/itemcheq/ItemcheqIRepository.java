package com.smarttmt.itemcheq;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemcheqIRepository extends CrudRepository<ItemcheqEntity,ItemcheqPK> {

    @Query("select i from ItemcheqEntity i where i.itemcheqPK.itchestr = :codigo")
    List<ItemcheqEntity> getListItemcheBYCodigo(String codigo);

}
