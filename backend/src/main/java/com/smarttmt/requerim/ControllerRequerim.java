package com.smarttmt.requerim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.modelos.RequerimCierreModel;
import com.smarttmt.modelos.RequerimCreateModel;
import com.smarttmt.modelos.RequerimDevolucionModel;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Validated
@RequestMapping("/requerimientos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ControllerRequerim {

    protected IServiceRequerim<EntityVwRequerim> iServiceGeneric;
    protected IServiceRequerimSinRelacion<EntityVwRequerimSinRelaciones> iServiceRequerimSinRelacion;
    protected ParaoperIService iServiceGenericParaoper;
    protected IJPAFilterVwRequerim<EntityVwRequerim> iJPASpecificationVwRequerim;
    protected IJPAFilterVwRequerim<EntityVwRequerimSinRelaciones> iJPASpecificationVwRequerimReporte;

    @Autowired
    public ControllerRequerim(
            IServiceRequerim iServiceGeneric,
            IServiceRequerimSinRelacion<EntityVwRequerimSinRelaciones> iServiceRequerimSinRelacion,
            ParaoperIService iServiceGenericParaoper,
            IJPAFilterVwRequerim<EntityVwRequerim> iJPASpecificationVwRequerim,
            IJPAFilterVwRequerim<EntityVwRequerimSinRelaciones> iJPASpecificationVwRequerimReporte) {
        this.iServiceGeneric = iServiceGeneric;
        this.iServiceRequerimSinRelacion = iServiceRequerimSinRelacion;
        this.iServiceGenericParaoper = iServiceGenericParaoper;
        this.iJPASpecificationVwRequerim = iJPASpecificationVwRequerim;
        this.iJPASpecificationVwRequerimReporte = iJPASpecificationVwRequerimReporte;
    }

    @GetMapping
    public ResponseEntity<?> getRequerim(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestParam("datareporte") String dataReporte) {

        log.info("init getRequerim token:{} cliente:{} numeroRequerim:{}", token, cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(iServiceGeneric.getAlltByCliente(cliente));

    }

    @GetMapping("estadisticasDetalle")
    public ResponseEntity<?> findRequerimientosCantidadEstados(
            @RequestHeader(name = "token") @NonNull String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") @NonNull String cliente,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_ESTADO_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_ESTADO_NO_VACIO)
            @RequestParam("estado") String estado,
            @RequestParam("datareporte") String dataReporte) {

        log.info("init findRequerimientosCantidadEstados token:{} cliente :{} estado:{} ", token, cliente, estado, dataReporte);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(iServiceGeneric.getEstadisticasDetalle(cliente, estado));
    }

    @GetMapping("rangofechas")
    public ResponseEntity<?> getRequerimRangoFechas(
            @RequestHeader(name = "token") @NonNull String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_FECHA_INICIAL_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_FECHA_INICIAL_NO_VACIO)
            @RequestParam("fechaInicial") String fechaInicial,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_FECHA_FINAL_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_FECHA_FINAL_NO_VACIO)
            @RequestParam("fechaFinal") String fechaFinal,
            @RequestParam("datareporte") String dataReporte) {

        log.info("init getRequerimRangoFechasPageable pageable:{} token:{} cliente:{} fechaInicial:{} fechaFinal:{}", token, cliente, fechaInicial, fechaFinal);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        final String fechCambioFormaInic = Utilidades.getFormatStr(fechaInicial, Constantes.FORMATO_ESTANDAR_FECHA,  Constantes.FORMATO_FECHA_YYYYMMDD);
        final String fechCambioFormaFina = Utilidades.getFormatStr(fechaFinal, Constantes.FORMATO_ESTANDAR_FECHA,  Constantes.FORMATO_FECHA_YYYYMMDD);
        final Date fechInic = Utilidades.getFechaDesdeCadena(fechCambioFormaInic, Constantes.FORMATO_FECHA_YYYYMMDD);
        final Date fechFina = Utilidades.getFechaDesdeCadena(fechCambioFormaFina,  Constantes.FORMATO_FECHA_YYYYMMDD);

        return ResponseEntity.ok(iServiceGeneric.getAllRequerimRangoFechas(cliente, fechInic, fechFina));

    }

    @GetMapping("columnasexcelrequerim")
    public ResponseEntity<?> getColumnsExcelVwRequerim(@RequestHeader(name = "token") @NonNull String token,
                                                       @RequestParam("cliente") @NonNull String cliente) {
        log.info("init getColumnsExcelVwRequerim token:{} cliente:{}", token, cliente);
        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }
        return ResponseEntity.ok(iServiceGeneric.getColumVwRequerimMap());
    }

    @GetMapping("detalle")
    public ResponseEntity<?> getDetalleRequerim(
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

        return ResponseEntity.ok(iServiceGeneric.getDetalle(cliente, Integer.parseInt(numeroRequerim)));

    }

    @GetMapping("/cantidadEstados")
    public ResponseEntity<?> cantidadRequerimEstado(
            @RequestHeader(name = "token") @NonNull String token,
            @NotNull(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO)
            @NotBlank(message = Constantes.MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO)
            @RequestParam("cliente") String cliente) {

        log.info("init cantidadRequerimEstado cliente:{}", cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return ResponseEntity.ok(iServiceGeneric.getCantidaEstadosPorCliente(cliente));
    }

    @PostMapping
    public ResponseEntity<?> crearRequerimiento(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @Valid  @RequestBody RequerimCreateModel requerim, BindingResult bindingResult){

        log.info("init crearRequerimiento cliente:{}", cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        if (bindingResult.hasErrors()) {
            return ResponseBindigResult.getResponseMapErrors(bindingResult);
        }

        String personal = TokenAutenticacion.getUsuarioToken(token);
        String retorno = iServiceGeneric.crear(requerim, cliente, personal);
        ObjectNode json = null;
        if (retorno.contains(Constantes.CAMPO_REQUCODI)) {
            json = new ObjectMapper().createObjectNode();
            json.put(Constantes.CAMPO_REQUCODI.toLowerCase(), LeeCadena.funLeerItems(retorno, Constantes.CAMPO_REQUCODI));
        }

        return ResponseEntity.ok(Optional.ofNullable(json).isPresent() ? json : retorno);
    }


    @PutMapping("/cierre")
    public ResponseEntity<?> cerrarRequerimiento(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestBody RequerimCierreModel requerim){

        log.info("inti cerrarRequerimiento cliente:{}", cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return iServiceGeneric.cerrar(requerim) ? ResponseEntity.ok("ok") :  ResponseEntity.badRequest().build();

    }



    @PutMapping("/devolucion")
    public ResponseEntity<?> devolverRequerimiento(
            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestBody RequerimDevolucionModel requerim) {

        log.info("inti devolverRequerimiento cliente:{}", cliente);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        return iServiceGeneric.devolucion(requerim) ? ResponseEntity.ok("ok") :  ResponseEntity.badRequest().build();

    }


    @DeleteMapping
    public ResponseEntity<?> eliminarRequerimiento(

            @RequestHeader(name = "token") @NonNull String token,
            @RequestParam("cliente") @NonNull String cliente,
            @RequestParam("id") @NonNull String requerimiento) {

        log.info("inti eliminarRequerimiento cliente:{} requerimiento:{}", cliente, requerimiento);

        if (!TokenAutenticacion.validarToken(token, cliente, iServiceGenericParaoper.getIdTokeWs())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constantes.TOKEN_INVALIDO);
        }

        String retorno = iServiceGeneric.eliminar(requerimiento);
        return Constantes.RETORNO_STRING_TRUE.equals(retorno) ? ResponseEntity.ok("ok") :  ResponseEntity.accepted().body(retorno);

    }

}
