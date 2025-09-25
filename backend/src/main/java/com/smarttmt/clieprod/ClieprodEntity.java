package com.smarttmt.clieprod;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@Table(name = "CLIEPROD")
public class ClieprodEntity {

    @Id
    private String clprclie;
    @Id
    private String clprprod;
    private Date clprfeco;
    private Date clprfein;
    private Date clprfeen;
    private Date clprfeac;
    private String clprvers;
    private Date clprfeig;
    private Date clprfefg;
    private String clpresco;
    private String clprobse;
    private String clprgepr;
    private String clprnuco;
    private String clprespr;
    private Long clprvweb;
    private Long clprnumo;
    private Long clprnupr;
    private Long clprnusp;

}
