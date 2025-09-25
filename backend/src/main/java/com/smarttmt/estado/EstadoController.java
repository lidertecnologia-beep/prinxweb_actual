package com.smarttmt.estado;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/estado")
@RestController
public class EstadoController {

    private final ParaoperIService iServiceGenericParaoper;
    private final EstadoIService estadoIService;


    /* se le da la conotacion de modificado debido a los pendites donde existe el estado endientes pero en la logica de negocio hay
     * otros estado que se consideran como pendientes
     * */
    @GetMapping("/listVal")
    public ResponseEntity<?> getListValEstadosModificado(
            @RequestHeader(name = "token") @NonNull String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente){

        log.info("init getListValEstadosModificado");

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return  ResponseEntity.ok(estadoIService.getListEstadoModificado());

    }

    @GetMapping("/estadosDetalle")
    public ResponseEntity<?> getListEstadosDetalle(
            @RequestHeader(name = "token") @NonNull String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente) throws IOException {

        log.info("init getListValEstadosModificado");

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return  ResponseEntity.ok(estadoIService.getListEstadosDetalle(cliente));

    }


}
