package com.smarttmt.codiveri;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@NamedStoredProcedureQuery(name = "crearCodiveri",
        procedureName = "pkgCodiVeri.pro_Crear_CodiVeri",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbValoRegr", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "sbRetorno", type = String.class)
        })

@NamedStoredProcedureQuery(name = "validarCodiveri",
        procedureName = "pkgCodiVeri.fun_Validar_CodiVeri",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCodigo", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCoveTerc", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCoveUssi", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCoveMail", type = String.class),
        })

@Getter
@Setter
@Entity
@Table(name = "CODIVERI")
public class Codiveri {
    @Id
    @Column(name = "COVECODI", nullable = false)
    private Long id;

    @Column(name = "COVETECL", length = 2)
    private String covetecl;

    @Column(name = "COVETERC", nullable = false, length = 26)
    private String coveterc;

    @Column(name = "COVECOAL", nullable = false, length = 32)
    private String covecoal;

    @Column(name = "COVEMAIL", nullable = false, length = 100)
    private String covemail;

    @Column(name = "COVEFECH", nullable = false)
    private LocalDate covefech;

    @Column(name = "COVEESTA", length = 2)
    private String coveesta;

    @Column(name = "COVEPERS", nullable = false, length = 6)
    private String covepers;

    @Column(name = "COVECELU", length = 12)
    private String covecelu;

    @Column(name = "COVEUSSI", length = 30)
    private String coveussi;

    @Column(name = "COVEFTIC")
    private LocalDate coveftic;

    @Column(name = "COVEFTFC")
    private LocalDate coveftfc;

}