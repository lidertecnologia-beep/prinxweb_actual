package com.smarttmt.anexos;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.requerim.IServiceRequerim;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;

import java.io.IOException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/anexos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AnexsoliController {

    protected ParaoperIService iServiceParaoper;
    protected AnexsoliIService iServiceAnexsoli;
    protected IServiceRequerim iServiceRequerim;

    @Autowired
    public AnexsoliController(ParaoperIService iServiceParaoper,
                              AnexsoliIService<Anexsoli2> iServiceAnexsoli,
                              IServiceRequerim iServiceRequerim) {
        this.iServiceParaoper = iServiceParaoper;
        this.iServiceAnexsoli = iServiceAnexsoli;
        this.iServiceRequerim = iServiceRequerim;
    }

    @GetMapping
    public ResponseEntity<?> getAnexos(
            @RequestHeader(name = "token") String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_VACIO)
            @RequestParam("requcodi") String numeroRequerim) {

        log.info("init getMantprod2 cliente:{} numeroRequerim:{}", cliente, numeroRequerim);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(iServiceAnexsoli.getAnexsoli(Integer.parseInt(numeroRequerim)));
    }

    @GetMapping("resourcefile")
    public ResponseEntity<Resource> getAnexo(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam(name = "cliente") @NonNull String cliente,
            @RequestParam("requcodi") @NonNull String numeroRequerim,
            @RequestParam("arsoarch") @NonNull String nombreArchivo) {

        log.info("init getAnexo token:{}  cliente :{} requcodi:{} arsoarch:{}", token, cliente, numeroRequerim, nombreArchivo);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Resource resourceFile = iServiceAnexsoli.getResourceFile(Integer.valueOf(numeroRequerim), nombreArchivo);
        try {
            return ResponseEntity.ok()
                    .contentLength(resourceFile.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resourceFile);
        } catch (IOException io) {
            log.error("getAnexo IOException:{}", io.toString());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("uploadFile")
    public ResponseEntity<?> subirArchivos(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestPart("cliente") @NonNull String cliente,
            @RequestPart("id") @NonNull String numeroRequerim,
            @RequestPart("file") @NonNull MultipartFile[] fileInputStream) {

        log.info("init subirArchivos token:{} cliente.{} id:{} file:{} ", token, cliente, numeroRequerim, fileInputStream);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        if (!iServiceRequerim.isExisteRequerim(numeroRequerim)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constantes.MENSAJE_ERROR_NO_EXISTE_REQUERIM);
        }

        iServiceAnexsoli.cargarAnexos(cliente,numeroRequerim,fileInputStream);

        return ResponseEntity.ok("ok");

    }

    @DeleteMapping("/allId")

    public ResponseEntity<?> removeTodosId(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestParam("id") @NonNull String numeroRequerim) {

        log.info("init removeTodosId cliente:{} id:{}", cliente, numeroRequerim );

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(iServiceAnexsoli.borrarAnexsoli(Integer.valueOf(numeroRequerim),null));

    }


}
