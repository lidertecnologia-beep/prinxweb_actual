package com.smarttmt.itemcheq;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class ItemcheqPK implements Serializable {

    String itchcodi;
    String itchestr;

}
