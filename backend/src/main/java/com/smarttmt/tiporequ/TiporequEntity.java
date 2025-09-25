package com.smarttmt.tiporequ;

import com.smarttmt.clasrequ.ClasrequEntity;
import com.smarttmt.mantprod.MantprodEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "TIPOREQU")
@Entity
public class TiporequEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tirecodi",referencedColumnName = "clrecodi",insertable = false, updatable = false)
    ClasrequEntity clasrequ;

    @Id
    String tirecodi;
    String tiredesc;
    Short tireplaz;
    Integer tireproy;
    String tirerepr;
    String tirecaus;
    String tireorig;
    String tiredest;
    String tiremeco;
    BigInteger tirevweb;
    String tiretire;
    Short tiretiat;
    String tireunti;
    String tireclre;
    String tireesta;

}
