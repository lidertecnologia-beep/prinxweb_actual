package com.smarttmt.clasrequ;

import com.smarttmt.tiporequ.TiporequEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigInteger;

@Data
@Table(name = "CLASREQU")
@Entity
public class ClasrequEntity {

    @OneToOne(mappedBy = "clasrequ")
    private TiporequEntity tiporequ;

    @Id
    private String clrecodi;
    private String clredesc;
    private String clretire;
    private BigInteger clrevweb;

}
