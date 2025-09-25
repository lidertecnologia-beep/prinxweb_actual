package com.smarttmt.solucion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "SOLUCION")

public class SolucionEntity {

    @Id
    Integer solucodi;
    String solutipo;
    Integer solurequ;
    String soluprob;
    String solusolu;
    String soluobse;
    String solupeso;
    Date solufeso;
    Date solufesi;
    String soluterm;
    String soluesta;
    Integer solucoay;
    String soluclie;
    String soluprod;
    String solupere;
    String soluconf;
    String solumeco;
    String soluclas;
    String soluarch;
    Integer soluprio;
    String soluobje;
    String solusoli;
    String solucicl;
    Date solufecc;
    String soludigi;
    String solupers;
    String soludevo;
    Date solufede;
    String solucade;
    String soludede;
    Integer solucali;
    Integer soluporc;
    Integer soluvweb;
    String solutire;
    String soluproc;
    String solumodu;
    String solusupr;
    String solutise;
    Integer solunuca;
    String solunouf;
    String soluarea;
    Integer soluexts;
    Integer soluproy;
    Integer solusecu;
    @Transient
    String soluprioridaAtencion;

}
