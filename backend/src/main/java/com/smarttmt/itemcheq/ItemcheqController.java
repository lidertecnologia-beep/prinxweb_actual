package com.smarttmt.itemcheq;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@Slf4j
@RequestMapping("/itemschequeo")
@RestController
public class ItemcheqController {

    private final ItemcheqService itemcheqService;
    private final ParaoperIService paraoperIService;

    @GetMapping("/listaIncidentes")
    public ResponseEntity<?> listIncidente(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente) {

        log.info("init listIncidente token:{} cliente:{}", token, cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, paraoperIService.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(itemcheqService.getListaIncidente());

    }

}
