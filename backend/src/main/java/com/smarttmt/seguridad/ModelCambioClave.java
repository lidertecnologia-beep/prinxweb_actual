package com.smarttmt.seguridad;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ModelCambioClave {

    @NotEmpty(message = "campo usuario no puede estar vacio")
    @NotNull(message = "Campo usuario requerido")
    private String us;
    @NotEmpty(message = "campo email no puede estar vacio")
    @NotNull(message = "Campo email requerido")
    private String email;
    @NotEmpty(message = "campo clave no puede estar vacio")
    @NotNull(message = "Campo clave requerido")
    private String pw;

}
