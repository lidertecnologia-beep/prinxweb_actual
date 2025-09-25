package com.smarttmt.personal;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.ResponseBindigResult;
import com.smarttmt.utilities.TokenAutenticacion;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/personal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PersonalController {

    private final ParaoperIService iServiceGenericParaoper;
    private final PersonalIService personalIService;

    @Autowired
    public PersonalController(ParaoperIService iServiceGenericParaoper,
            PersonalIService personalIService) {
        this.iServiceGenericParaoper = iServiceGenericParaoper;
        this.personalIService = personalIService;
    }

    @GetMapping
    public ResponseEntity<?> getPersonal(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestParam("idPersonal") @NonNull String idPersonal) {

        log.info("init listIncidente token:{} cliente:{} idPersonal:{}", token, cliente, idPersonal);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            throw new RecursoNoEncontradoException(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(personalIService.getPersonal(idPersonal));

    }

    @PutMapping
    public ResponseEntity<?> actualizar(
            @RequestHeader(name = "token") @NonNull String token,
            @Valid @RequestBody PersonalRecord personalRecord, BindingResult bindingResult) {

        log.info("init actualizar");

        if (bindingResult.hasErrors()) {
            return ResponseBindigResult.getResponseMapErrors(bindingResult);
        }

        if (!TokenAutenticacion.validarToken(token, personalRecord.persterc(), iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personalIService.actualizar(personalRecord));

    }

}
