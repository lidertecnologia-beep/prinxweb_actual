package com.smarttmt.personal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PersonalRecord(
        @NotEmpty(message = "Campo perscodi no puede estar vacio")
        @NotNull(message = "Campo perscodi requerido")
        String perscodi,
        @NotEmpty(message = "Campo persterc no puede estar vacio")
        @NotNull(message = "Campo persterc requerido")
        String persterc,
        @NotEmpty(message = "Campo persnomb no puede estar vacio")
        @NotNull(message = "Campo persnomb requerido")
        String persnomb,
        @NotEmpty(message = "Campo persapel no puede estar vacio")
        @NotNull(message = "Campo persapel requerido")
        String persapel,
        @NotEmpty(message = "Campo perstele no puede estar vacio")
        @NotNull(message = "Campo perstele requerido")
        String perstele,
        @NotEmpty(message = "Campo persemai no puede estar vacio")
        @NotNull(message = "Campo persemai requerido")
        String persemai) {

}
