package com.smarttmt.causrequ;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CAUSREQU")
public class CausrequEntity {

    @Id
    String 	carecodi;
    String 	caredesc;
    String 	careclre;
    Integer carevweb;
    String 	careesta;
    String 	careclas;

}
