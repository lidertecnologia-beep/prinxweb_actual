package com.smarttmt.codiveri;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelCodiveri {

    @NotEmpty(groups = IModelGroupValidaCodiveri.class, message = "Campo usuario no puede estar vacio")
    @NotNull(groups = IModelGroupValidaCodiveri.class, message = "Campo usuario requerido")
    @NotEmpty(message = "campo usuario no puede estar vacio")
    @NotNull(message = "Campo usuario requerido")
    private String us;

    @NotEmpty(groups = IModelGroupValidaCodiveri.class, message = "Campo email no puede estar vacio")
    @NotNull(groups = IModelGroupValidaCodiveri.class, message = "Campo email requerido")
    @NotEmpty(message = "campo email no puede estar vacio")
    @NotNull(message = "Campo email requerido")
    private String email;

    @NotEmpty(groups = IModelGroupValidaCodiveri.class, message = "Campo codiveri no puede estar vacio")
    @NotNull(groups = IModelGroupValidaCodiveri.class, message = "Campo codiveri requerido")
    private String codiveri;
    private String tercero;

}
