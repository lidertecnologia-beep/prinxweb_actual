package com.smarttmt.tiporequ;

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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tipoRequerimiento")
@Slf4j
@RestController
public class TipoRequController {

    private final ParaoperIService paraoperIService;
    private final TiporequService tiporequService;
    
    @GetMapping("/listVal")
    public ResponseEntity<?> getListaValores(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente) {

        log.info("init getRequerim token:{} cliente:{}", token, cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, paraoperIService.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(tiporequService.getListaValores());

    }

}
