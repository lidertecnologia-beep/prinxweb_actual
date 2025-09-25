package com.smarttmt.requerim;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Table(name = "VW_PRINX_MODULO_CLIENTES")
@Data
@Entity
public class VwRequerim2Entity {
    @Id
    Integer requcodi;
    String requclie;
    String fechsoli;
    String fechfeco;
    String estadesc;
    String estacodi;
    String requdeta;
    Date requfech;
    String requsoli;
    String clredesc;
    String proddesc;
    Integer requprio;
    String requprod;
    Date requfeso;
    String fechcier;
    String prioclie;//prioridad cliente
    String requpere;//codigo persona que recibio
    String peredesc;//descripcion persona que recibio
    String prioeval;//prioridad evaluacion
    String diagnost;//diagnostico
    String nivelate;//nivel de atencion
    String contcamb;//control de cambio
    String preestim;//preestimacion
    String bancsolu;//banco soluciones






}
