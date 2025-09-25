package com.smarttmt.seguridad;

import com.smarttmt.codiveri.ICodiveriService;
import com.smarttmt.codiveri.IModelGroupValidaCodiveri;
import com.smarttmt.codiveri.ModelCodiveri;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.LeeCadena;
import com.smarttmt.utilities.ResponseBindigResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/restablecepw")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestablecePasswordController {

    private final ICodiveriService iCodiveriService;
    private final ISeguridad iSeguridad;

    @PostMapping("/crearcodiveri")
    public ResponseEntity<?> crearCodiveri(@Valid @RequestBody ModelCodiveri modelCodiveri, BindingResult bindingResult) {
        log.info("init crearCodiveri modelCodiveri:{}", modelCodiveri);

        if (bindingResult.hasErrors()) {
            return ResponseBindigResult.getResponseMapErrors(bindingResult);
        }

        if (StringUtils.isEmpty(modelCodiveri.getUs()) && StringUtils.isEmpty(modelCodiveri.getEmail())) {
            return ResponseEntity.notFound().build();
        }

        String retorno = iCodiveriService.crearCodiveri(modelCodiveri.getUs(), modelCodiveri.getEmail());
        if (Constantes.VALOR_RETORNO_FALSE.equalsIgnoreCase(retorno)) {
            return ResponseEntity.ok(retorno);
        }

        ModelCodiveri codiveri = new ModelCodiveri();
        codiveri.setCodiveri(LeeCadena.funLeerItems(retorno, Constantes.COLUMNA_CODIGO_VERIFICACION_CODIGO_ALTERNO));
        codiveri.setTercero(LeeCadena.funLeerItems(retorno, Constantes.COLUMNA_CODIGO_VERIFICACION_TERCERO));
        return ResponseEntity.ok(codiveri);

    }

    @PostMapping("/validarcodiveri")
    public ResponseEntity<?> validarCodiveri(@Validated(IModelGroupValidaCodiveri.class) @RequestBody ModelCodiveri modelCodiveri, BindingResult bindingResult) {
        log.info("init validarCodiveri modelCodiveri:{}", modelCodiveri);
        return bindingResult.hasErrors()
                ? ResponseBindigResult.getResponseMapErrors(bindingResult)
                : ResponseEntity.ok(iCodiveriService.validarCodiveri(modelCodiveri.getCodiveri(), modelCodiveri.getTercero(), modelCodiveri.getUs(), modelCodiveri.getEmail()));
    }

    @PostMapping("/cambiopw")
    public ResponseEntity<?> cambiarPw(@Valid @RequestBody ModelCambioClave modelCambioClave, BindingResult bindingResult) {
        log.info("init crearCodiveri");
        return bindingResult.hasErrors()
                ? ResponseBindigResult.getResponseMapErrors(bindingResult)
                : ResponseEntity.ok(iSeguridad.cambiarClave(modelCambioClave.getUs(), modelCambioClave.getEmail(), modelCambioClave.getPw()));
    }
}
