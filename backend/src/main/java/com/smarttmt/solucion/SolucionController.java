package com.smarttmt.solucion;

import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.personal.PersonalRecord;
import com.smarttmt.requerim.EntityVwRequerim;
import com.smarttmt.requerim.EntityVwRequerimSinRelaciones;
import com.smarttmt.requerim.IRepositoryRequerimSinRelaciones;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.ResponseBindigResult;
import com.smarttmt.utilities.TokenAutenticacion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/solucion")
@RestController
public class SolucionController {

    private final ParaoperIService iServiceGenericParaoper;
    private final SolucionIService solucionIService;


    @Autowired
    public SolucionController(
            ParaoperIService iServiceGenericParaoper,
            SolucionIService solucionIService,
            IRepositoryRequerimSinRelaciones iRepositoryRequerimSinRelaciones) {
        this.iServiceGenericParaoper = iServiceGenericParaoper;
        this.solucionIService = solucionIService;
    }

    @GetMapping
    public ResponseEntity<?> getSolucion(
            @RequestHeader("token") @NonNull String token,
            @NotNull(message = "El campo cliente no debe ser nulo")
            @NotBlank(message = "El campo cliente no debe estar vacío")
            @RequestParam(required = true, name = "cliente") String cliente,
            @NotNull(message = "El campo solucodi no debe ser nulo")
            @NotBlank(message = "El campo solucodi no debe estar vacío")
            @RequestParam(required = true, name = "solucodi") String numeroSolicitud) {

        log.info("init getSolucion cliente:{} numeroSolicitud:{}", cliente, numeroSolicitud);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solucionIService.getSolucionRecordDTO(numeroSolicitud));

    }

    @PutMapping
    public ResponseEntity<?> updateSolucion(@RequestHeader("token") @NonNull String token,
                                            @NotNull(message = "El campo cliente no debe ser nulo")
                                            @NotBlank(message = "El campo cliente no debe estar vacío")
                                            @RequestParam(required = true, name = "cliente") String cliente,
                                            @Valid @RequestBody EntityVwRequerimSinRelaciones requerim, BindingResult bindingResult) {

        log.info("init updateSolucion cliente:{} ", cliente);

        if (bindingResult.hasErrors()) {
            return ResponseBindigResult.getResponseMapErrors(bindingResult);
        }

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.notFound().build();
        }

        solucionIService.update(requerim);

        return ResponseEntity.ok(Constantes.MENSAJE_RESPUESTA_OK);

    }

}
