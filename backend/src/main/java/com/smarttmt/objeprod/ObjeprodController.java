package com.smarttmt.objeprod;

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
@RequestMapping("/objetoproducto")
@RestController
public class ObjeprodController {

    private final ParaoperIService paraoperIService;
    private final ObjeprodService objeprodService;

    @GetMapping("/listVal")
    public ResponseEntity<?> listObjetoProducto(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestParam("producto") @NonNull String producto) {

        log.info("init listIncidente token:{} cliente:{} producto:{}", token, cliente,producto);

        if (!TokenAutenticacion.validarToken(token, cliente, paraoperIService.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(objeprodService.getListaObjetoProducto(producto));

    }

}
