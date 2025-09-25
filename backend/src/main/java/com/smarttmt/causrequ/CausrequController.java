package com.smarttmt.causrequ;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RequestMapping("/causasDevolucion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CausrequController {

    private final ParaoperIService iServiceGenericParaoper;
    private final CausrequIService causrequIService;

    @GetMapping("/listVal")
    public ResponseEntity<?> getCausasDevolucion(
            @RequestHeader(name = "token") String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente) {

        log.info("init getCausasDevolucion cliente:{}", cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(causrequIService.getCausasDevolucion());

    }

}
