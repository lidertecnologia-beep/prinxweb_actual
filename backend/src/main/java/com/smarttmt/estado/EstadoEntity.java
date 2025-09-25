package com.smarttmt.estado;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigInteger;

@Table(name = "ESTADO")
@Data
@Entity
public class EstadoEntity {
    @Id
    private String estacodi;
    private String estadesc;
    private BigInteger estavweb;

}
