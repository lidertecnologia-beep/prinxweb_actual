package com.smarttmt.mantprod;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/mantprod")
@RestController
public class MantprodController {

    private final ParaoperIService iServiceGenericParaoper;
    private final MantprodIService mantprodIService;

    @GetMapping
    public ResponseEntity<?> getMantprod2(
            @RequestHeader(name = "token") String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_VACIO)
            @RequestParam("requcodi") String numeroRequerim) {

        log.info("init getMantprod2 cliente:{} numeroRequerim:{}", cliente, numeroRequerim);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(mantprodIService.getListMantprod(cliente, Integer.parseInt(numeroRequerim)));

    }

}
