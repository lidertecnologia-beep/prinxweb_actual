package com.smarttmt.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ItemCheqPrioridadAtencionModel {
    private String itchcodi;
    private Integer itchsecu;
    private String itchdesc;
    private String itchcheq;
}
