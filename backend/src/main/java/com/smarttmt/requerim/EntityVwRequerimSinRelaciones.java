package com.smarttmt.requerim;

import com.smarttmt.itemcheq.Itemcheq;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Usuario
 */
@Data
@Entity
@Table(name = "VW_REQUERIM")
@NamedStoredProcedureQuery(name = "getVwRequerimReporte",
        procedureName = "pkgrequerim.pro_requerim_fechas_refcursor",
        resultClasses = EntityVwRequerimSinRelaciones.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbFechInic", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbFechFina", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "rgRegistro", type = Void.class)
        })

@NamedStoredProcedureQuery(name = "getEstadisticasDetalleReporte",
        procedureName = "pkgrequerim.pro_estados_paginas_refcursor",
        resultClasses = EntityVwRequerimSinRelaciones.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inRequCodi", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFech", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFeco", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbSistema", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inPagina", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inRows", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "rgRegistro", type = Void.class)
        })

@NamedStoredProcedureQuery(name = "getTotalEstadisticasDetalleReporte",
        procedureName = "pkgrequerim.pro_estados_paginas_totalregi",
        resultClasses = EntityVwRequerimSinRelaciones.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inRequCodi", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFech", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFeco", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbSistema", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "inTotalRegi", type = Integer.class)
        })

public class EntityVwRequerimSinRelaciones implements Serializable {

    @Id
    @NotNull(message = "Campo requcodi requerido")
    Integer requcodi;
    String requclie;
    String requprod;
    @NotEmpty(message = "Campo requfech no puede estar vacio")
    @NotNull(message = "Campo requfech requerido")
    @Pattern(message = "Formato de fecha invalido", regexp = "\\d{2}-\\d{2}-\\d{4}")
    String requfech;
    String requclas;
    String requtipo;
    @NotEmpty(message = "Campo requdeta no puede estar vacio")
    @NotNull(message = "Campo requdeta requerido")
    String requdeta;
    String requdiem;
    String requdivi;
    String requnuex;
    Integer requprio;
    String requpere;
    String requpeso;
    String requsoli;
    String requmeco;
    Date requfesi;
    String requterm;
    Date requfeco;
    Date requfecp;
    Date requfeso;
    String requesta;
    String requperi;
    String requresp;
    Date requfeer;
    String requobpr;
    String requrepr;
    Date requfeip;
    String requobse;
    String requacti;
    Integer requproy;
    Integer requsecu;
    String requprog;
    String requvibu;
    Date requfevb;
    String requrech;
    Date requfere;
    String requcaus;
    String requprue;
    Integer requcaso;
    String requcrit;
    String requorig;
    Integer requmapr;
    Integer requmaps;
    Integer requmase;
    String requcont;
    String requtere;
    String requobco;
    String requfact;
    Integer requsoco;
    String requrele;
    Date requfelc;
    Date requfelp;
    String requdest;
    String requcomp;
    Integer requhoes;
    Integer requhore;
    String requarch;
    String requcicl;
    Date requfecc;
    String requdigi;
    String requpers;
    String requdevo;
    Date requfede;
    String requcade;
    String requdede;
    Integer requacpr;
    Integer requacnu;
    Integer requacse;
    Integer requporc;
    String requcali;
    Integer requpres;
    Integer requpese;
    String requlaps;
    String requclso;
    Integer requvweb;
    String requpaco;
    String requanul;
    Integer requnupr;
    String requrese;
    String requproc;
    String requsupr;
    String requobca;
    String requcaan;
    Date requfean;
    String requcoco;
    String requclac;
    Integer requties;
    String requmeso;
    Integer requfuen;
    String requmodu;
    String requexte;
    String requnore;
    String requtire;
    String requtise;
    String fechrequ;
    String requferr;
    String fechlipr;
    String fechcomp;
    String fechcopr;
    String fechsolu;
    String fechanul;
    String proddesc;
    String tercdesc;
    String tiredesc;
    String clredesc;
    String nombresp;
    String nombprog;
    String nombserv;
    String prprdepr;
    String prprdesp;
    String estadesc;
    String modudesc;
    String numesema;
    String nombmes;
    @Transient
    List<Itemcheq> listItemCheq;

}
