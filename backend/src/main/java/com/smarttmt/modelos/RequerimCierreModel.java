/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarttmt.modelos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Builder
@AllArgsConstructor
@Data
public class RequerimCierreModel implements Serializable {
    private String requcodi;
    private String requdeta;
    private String requprio;
    private String requprod;
    private String requfech;
    private String requtire;
    private String requmeco;
    private String requobpr;
    private Integer requcali;
    private List<ItemCheqPrioridadAtencionModel> listItemCheq;
}