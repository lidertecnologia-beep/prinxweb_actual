/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarttmt.modelos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;


@Builder
@AllArgsConstructor
@Data
public class RequerimCreateModel implements Serializable {

    private String requcodi;
    @NotNull
    @Pattern(message = "Formato de fecha invalido", regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String requfech;
    @NotEmpty(message = "Campo requdeta no puede ser nulo")
    //@Pattern(message = "Campo requdeta no puede ser nulo", regexp = "^[a-zA-Z0-9]+$")
    @Size(max = 4000, message = "Campo requdeta maximo de caracteres 4000")
    private String requdeta;
    @NotEmpty(message = "Campo requtire no puede ser nulo")
    @Size(max = 2, message = "Campo requtire maximo de caracteres 2")
    private String requtire;
    @NotEmpty(message = "Campo requprio no puede ser nulo")
    @Size(max = 1, message = "Campo requprio maximo de caracteres 1")
    private String requprio;
    @NotEmpty(message = "Campo requmeco no puede ser nulo")
    @Size(max = 2, message = "Campo requmeco maximo de caracteres 2")
    private String requmeco;
    @NotEmpty(message = "Campo requprod no puede ser nulo")
    @Size(max = 10, message = "Campo requprod maximo de caracteres 2")
    private String requprod;
    private String requobpr;
    private String requesta;
    private String requpere;
    private String requclie;
    private String requtipo;
    private String requsoli;
    private String requfesi;
    private String requpeso;
    private String requcade;
    private String requdede;
    private String requdevo;
    private String requcicl;
    private String requfecc;
    private Integer requcali;
    private String requfede;
    private String requclas;
    private List<ItemCheqPrioridadAtencionModel> listItemCheq;
}
