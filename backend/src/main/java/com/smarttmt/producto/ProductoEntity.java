package com.smarttmt.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTO")
public class ProductoEntity {

    @Id
    private String prodcodi;
    private String proddesc;
    private String proddeta;
    @Temporal(TemporalType.DATE)
    private Date prodfech;
    String prodvers;
    String prodsoft;
    String prodesta;
    String prodclas;
    Integer prodver1;
    Integer prodver2;
    Integer prodver3;
    Integer prodver4;
    Integer prodver5;
    String prodresp;
    Date prodfeve;
    Date prodfevd;
    String prodvede;
    String produfde;
    String produfpr;
    String produrde;
    String produrpr;
    String prodempr;
    Integer prodproy;
    Integer prodvweb;

}
