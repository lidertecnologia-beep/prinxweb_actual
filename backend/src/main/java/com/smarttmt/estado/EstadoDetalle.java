package com.smarttmt.estado;

import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NamedStoredProcedureQuery(
        name = "getEstadosDetalle",
        procedureName = "pkgestado.pro_detalle_estado_moducliente",
        resultClasses = EstadoDetalle.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "rgRegistro", type = Void.class)
        })


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EstadoDetalle implements Serializable {

    private String codigo;
    private String descripcion;
    private String cantidad;
    private String item;
    private String labellink;
    private String color;
    private String hover;

}
