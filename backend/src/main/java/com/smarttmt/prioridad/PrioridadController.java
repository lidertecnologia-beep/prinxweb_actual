package com.smarttmt.prioridad;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Slf4j
@RequestMapping("/prioridad")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PrioridadController {

    private final PrioridadService prioridadService;
    private final ParaoperIService paraoperIService;

    @GetMapping("/listVal")
    public ResponseEntity<?> getListaValores(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente) {

        log.info("init getRequerim token:{} cliente:{}", token, cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, paraoperIService.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(prioridadService.getListaValores());
    }


}
