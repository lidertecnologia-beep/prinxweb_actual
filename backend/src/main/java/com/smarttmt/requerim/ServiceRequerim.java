package com.smarttmt.requerim;

import com.smarttmt.anexos.Anexsoli;
import com.smarttmt.anexos.AnexsoliIService;
import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.excepciones.TransaccionBaseDatosException;
import com.smarttmt.mantprod.MantprodEntity;
import com.smarttmt.mantprod.MantprodIService;
import com.smarttmt.modelos.RequerimCierreModel;
import com.smarttmt.modelos.RequerimCreateModel;
import com.smarttmt.modelos.RequerimDevolucionModel;
import com.smarttmt.personal.PersonalIService;
import com.smarttmt.solucion.SolucionEntity;
import com.smarttmt.solucion.SolucionIRepository;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.EnumEstadosPendientes;
import com.smarttmt.utilities.LeeCadena;
import com.smarttmt.utilities.Sysdate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.*;

@Slf4j
@Service
public class ServiceRequerim implements IServiceRequerim<VwRequerim2Entity> {

    protected IRepositoryRequerim iRequerimRepository;
    protected IRepositoryRequerimSinRelaciones iRequerimRepositorySinRelaciones;
    protected JdbcTemplate jdbcTemplate;
    protected RepositoryStoredProcedureRequerim requerimRepositoryStoredProcedure;
    private final MantprodIService mantprodIService;
    private final AnexsoliIService anexsoliIService;
    private final VwRequerim2IRepository vwRequerim2IRepository;
    private final RequerimSolucionItemsMapper requerimSolucionItemsMapper;
    private final PersonalIService personalIService;
    private final SolucionIRepository solucionIRepository;

    @Autowired
    public ServiceRequerim(
            IRepositoryRequerim iRequerimRepository,
            JdbcTemplate jdbcTemplate,
            RepositoryStoredProcedureRequerim requerimRepositoryStoredProcedure,
            IRepositoryRequerimSinRelaciones iRequerimRepositorySinRelaciones,
            MantprodIService mantprodIService,
            AnexsoliIService anexsoliIService,
            VwRequerim2IRepository vwRequerim2IRepository,
            RequerimSolucionItemsMapper requerimSolucionItemsMapper,
            PersonalIService personalIService,
            SolucionIRepository solucionIRepository) {

        this.iRequerimRepository = iRequerimRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.requerimRepositoryStoredProcedure = requerimRepositoryStoredProcedure;
        this.iRequerimRepositorySinRelaciones = iRequerimRepositorySinRelaciones;
        this.mantprodIService = mantprodIService;
        this.anexsoliIService = anexsoliIService;
        this.vwRequerim2IRepository = vwRequerim2IRepository;
        this.requerimSolucionItemsMapper = requerimSolucionItemsMapper;
        this.personalIService = personalIService;
        this.solucionIRepository = solucionIRepository;
    }

    /* implemetacion llamando un package function base de datos sysrefcursor retornando un map*/
    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> getAlltByClienteMap(String cliente, Pageable page) {

        log.info("init getAllSeviceMap  cliente:{} page:{}", cliente, page);

        jdbcTemplate.setResultsMapCaseInsensitive(true);

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgrequerim")
                .withFunctionName("fun_requerim_fechas_refcursor")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(
                        new SqlOutParameter("return", Types.REF_CURSOR),
                        new SqlParameter("sbCliente", Types.VARCHAR),
                        new SqlParameter("sbFechInic", Types.VARCHAR),
                        new SqlParameter("sbFechFina", Types.VARCHAR),
                        new SqlParameter("sbEstado", Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sbCliente", cliente)
                .addValue("sbFechInic", "01-12-2021")
                .addValue("sbFechFina", null)
                .addValue("sbEstado", "%");

        Map<String, Object> result = call.execute(in);
        log.info("result :{}", result);

        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VwRequerim2Entity> getAlltByCliente(String cliente) {
        log.info("init getAllRequerimByClientPege  cliente:{}", cliente);
        return vwRequerim2IRepository.findByRequclie(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EntityVwRequerim> getAlltByRequcodi(String cliente, Integer numeroRequerim, Pageable page) {
        log.info("init getAlltByRequcodi  cliente :{} numeroRequerim:{} page:{}", cliente, numeroRequerim, page);
        return iRequerimRepository.findListVwRequerimFiltered(cliente, numeroRequerim, page);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EntityVwRequerim> getAlltByRequfech(String cliente, Date fechRequerim, Pageable page) {
        log.info("init getAlltByRequfech  cliente :{} fechRequerim:{} page:{}", cliente, fechRequerim, page);
        return iRequerimRepository.findListVwRequerimRequfechFiltered(cliente, fechRequerim, page);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EntityVwRequerim> getAlltByRequfeco(String cliente, Date fechCompromiso, Pageable page) {
        log.info("init getAlltByRequfeco  cliente :{} fechCompromiso:{} page:{}", cliente, fechCompromiso, page);
        return iRequerimRepository.findListVwRequerimRequfecoFiltered(cliente, fechCompromiso, page);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EntityVwRequerim> getAlltByRequesta(String cliente, String estado, Pageable page) {
        log.info("init getAlltByRequesta  cliente :{} estado:{} page:{}", cliente, estado, page);
        return iRequerimRepository.findListVwRequerimRequestaFiltered(cliente, estado, page);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VwRequerim2Entity> getEstadisticasDetalle(String cliente, String estado) {
        log.info("init getAlltByRequesta  cliente :{} estado:{}", cliente, estado);
        return vwRequerim2IRepository.findByRequclieAndEstacodi(cliente, estado);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntityVwRequerimSinRelaciones> getEstadisticasDetalleReporte(String cliente, String estado, Integer requcodi, String requfech, String requfeco) {
        log.info("init getAlltByRequesta  cliente :{} estado:{} requcodi:{} requfech:{} requfeco:{}", cliente, estado, requcodi, requfech, requfeco);
        return requerimRepositoryStoredProcedure.getResultListEstadisticasDetalleReporte(cliente, estado, requcodi, requfech, requfeco, 1, 10000);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntityVwRequerim> getAllRequerimRangoFechas(String cliente, Date fechaInicial, Date fechaFinal) {
        log.info("getAllRequerimRangoFechas cliente:{} fechaInicial:{} fechaFinal:{}", cliente, fechaInicial, fechaFinal);
        return iRequerimRepository.findListVwRequerimRangoFechas(cliente, fechaInicial, fechaFinal);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EntityVwRequerim> getAlltWithFilter(Specification<EntityVwRequerim> specification, Pageable page) {
        log.info("init getAlltWithFilter  specification:{} page:{}", specification, page);
        return iRequerimRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntityVwRequerimSinRelaciones> getAlltWithFilterReporte(Specification<EntityVwRequerimSinRelaciones> specification) {
        log.info("init getAlltWithFilterReporte  specification:{}", specification);
        return iRequerimRepositorySinRelaciones.findAll(specification);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> getColumVwRequerimMap() {
        log.info("init getColumVwRequerimMap ");
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkgrequerim")
                .withFunctionName("fun_colum_vwrequerim_refcursor")
                .withoutProcedureColumnMetaDataAccess()
                .withReturnValue()
                .declareParameters(new SqlOutParameter("return", Types.REF_CURSOR));
        return call.execute();
    }

    @Override
    public boolean isExisteRequerim(String requcodi) {
        log.info("init isExisteRequerim requcodi:{}", requcodi);
        try {
            if (requcodi != null && !requcodi.isEmpty()) {
                EntityVwRequerim requerim = iRequerimRepository.findByRequcodi(Integer.valueOf(requcodi));
                return requerim != null;
            }
        } catch (NumberFormatException ex) {
            log.info("isExisteRequerim EntityNotFoundException:{}", ex.toString());
        }
        return false;

    }

    @Override
    public String requerimPendiente(String estado) {
        log.info("init requerimPendiente estado:{}", estado);
        if (estado == null) {
            return "N";
        }

        List<String> listEstadoPendientes = Arrays.asList(
                EnumEstadosPendientes.PENDIENTE.getCodigo(),
                EnumEstadosPendientes.ACTIVO.getCodigo(),
                EnumEstadosPendientes.ANALISIS.getCodigo(),
                EnumEstadosPendientes.DISENO.getCodigo(),
                EnumEstadosPendientes.CONSTRUCION_DESARROLLO.getCodigo(),
                EnumEstadosPendientes.DOCUMENTACION.getCodigo(),
                EnumEstadosPendientes.ASIGNADO.getCodigo(),
                EnumEstadosPendientes.PROGRAMADO.getCodigo(),
                EnumEstadosPendientes.PRUEBAS.getCodigo());

        return listEstadoPendientes.contains(estado) ? "S" : "N";

    }

    public RequerimDetalleDTO getDetalle(String cliente, Integer numeroRequerim) {
        log.info("init getListMantprod cliente:{}  numeroRequerim:{}", cliente, numeroRequerim);
        if (cliente == null || numeroRequerim == null) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_MANTPROD_NULO);
        }

        List<MantprodEntity> listMantprod = mantprodIService.getListMantprod(cliente, numeroRequerim);
        List<Anexsoli> listAnexos = anexsoliIService.getAnexsoli(numeroRequerim);

        return new RequerimDetalleDTO(listMantprod, listAnexos);

    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, String> getCantidaEstadosPorCliente(String cliente) {
        log.info("init getCantidaEstadosPorCliente cliente:{} ", cliente);

        if (cliente == null || cliente.isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_CLIENTE_NO_EXISTE);
        }

        String cantidadEstados = requerimRepositoryStoredProcedure.getCantidadEstadosCliente(cliente);

        if (cantidadEstados == null || cantidadEstados.isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_CANTIDAD_ESTADOS_SIN_REGISTROS);
        }

        Map<String, String> mapCantidadEstado = new HashMap<>();
        mapCantidadEstado.put(Constantes.KEY_MAP_TERMINADOS, LeeCadena.funLeerItems(cantidadEstados, Constantes.TERMINADOS));
        mapCantidadEstado.put(Constantes.KEY_MAP_CERRADOS, LeeCadena.funLeerItems(cantidadEstados, Constantes.CERRADOS));
        mapCantidadEstado.put(Constantes.KEY_MAP_DEVOLUCIONES, LeeCadena.funLeerItems(cantidadEstados, Constantes.DEVOLUCIONES));
        mapCantidadEstado.put(Constantes.KEY_MAP_PENDIENTES, LeeCadena.funLeerItems(cantidadEstados, Constantes.PENDIENTES));
        mapCantidadEstado.put(Constantes.KEY_MAP_PROGRAMADOS, LeeCadena.funLeerItems(cantidadEstados, Constantes.PROGRAMADOS));
        mapCantidadEstado.put(Constantes.KEY_MAP_ROADMAP, LeeCadena.funLeerItems(cantidadEstados, Constantes.ROADMAP));

        return mapCantidadEstado;

    }

    @Transactional
    public String crear(RequerimCreateModel requerim, String clienteIn, String personalIn) {
        log.info("init crear requerimiento personalIn:{}", personalIn);
        try {

            //validaciones
            final String cliente = Optional.ofNullable(clienteIn).filter(client -> !StringUtils.isEmpty(client)).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro personal"));
            final String codigoPersonal = Optional.ofNullable(personalIn).filter(pers -> !StringUtils.isEmpty(pers)).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro cliente"));
            requerim = Optional.ofNullable(requerim).orElseThrow(() -> new IllegalArgumentException("Es requerido el objeto requerim"));
            var listItemCheq = Optional.ofNullable(requerim.getListItemCheq()).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la priorioad de atencion"));
            var optionalItemCheq = listItemCheq.stream().filter(itemchq -> "S".equalsIgnoreCase(itemchq.getItchcheq())).findFirst();
            var itemcheq = optionalItemCheq.orElseThrow(() -> new RecursoNoEncontradoException("No encontro la prioridad de atencion"));
            var personal = personalIService.getPersonalPorUsuario(codigoPersonal);
            personal = Optional.ofNullable(personal).orElseThrow(() -> new RecursoNoEncontradoException("No se encotro el personal con codigo:" + codigoPersonal));

            //se carga el requerim y los items para el registro
            requerim.setRequclie(cliente);
            requerim.setRequsoli(personal.getPersnomb() + " " + personal.getPersapel());
            requerim.setRequpere(personal.getPerscodi());
            requerim.setRequpeso(personal.getPerscodi());
            var itemsSolicitud = requerimSolucionItemsMapper.apply(requerim);
            String itemsChequeo = Constantes.ITEM_ITCHCODI_CODIGO + itemcheq.getItchcodi() + "|" + Constantes.ITEM_ITCHCHEQ_CHEQUEADO + itemcheq.getItchcheq();

            String retorno = requerimRepositoryStoredProcedure.crearRequerim(itemsSolicitud, itemsChequeo);
            log.info("retorno:{}", retorno);
            retorno = retorno.contains(Constantes.ITEM_ERROR)
                    ? LeeCadena.funLeerItems(retorno, Constantes.ITEM_ERROR)
                    : Constantes.ITEM_REQUCODI + LeeCadena.funLeerItems(retorno, Constantes.CAMPO_SOLUCODI);

            log.info("retorno modificando los items:{}", retorno);

            return retorno;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TransaccionBaseDatosException("No se pudo crear el registro");
        }
    }

    private void validarEstadoRequerim(SolucionEntity solucion) {

        solucion = Optional.ofNullable(solucion).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro requerim"));

        //se valida si esta cerrado
        if (Constantes.ESTACERR.equals(solucion.getSoluesta())) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_REQUERIMIENTO_CERRADO);
        }

        //Se valida que este terminado
        if (!Constantes.ESTATERM.equals(solucion.getSoluesta())) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_REGISTRO_SOLUCION_NO_TERMINADO);
        }

    }

    @Transactional
    public boolean cerrar(RequerimCierreModel requerimIn) {
        log.info("init cerrar ");

        try {
            //validaciones
            final var requerimExist = Optional.of(requerimIn).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro requerim"));
            var requerim = iRequerimRepository.findById(Integer.parseInt(requerimExist.getRequcodi())).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el requerimiento con numero:" + requerimExist.getRequcodi()));

            if (requerimExist.getRequcali() <= 0) {
                throw new RecursoNoEncontradoException(Constantes.MENSAJE_CALIFICACION_SERVICIO);
            }

            var solucion = solucionIRepository.findById(requerim.getRequcodi()).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el registro en la tabla solucion con numero:" + requerim.getRequcodi()));
            validarEstadoRequerim(solucion);
            solucion.setSolucicl("S");
            solucion.setSoludevo("N");
            solucion.setSolucade(null);
            solucion.setSoludede(null);
            solucion.setSoluesta(Constantes.ESTACERR);
            solucion.setSolucali(requerimExist.getRequcali());
            //solucion.setSolufecc(Sysdate.getFechaObjectToDate(Constantes.FORMATO_ESTANDAR_FECHA, new Date()));
            solucionIRepository.save(solucion);

            return true;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TransaccionBaseDatosException("No se pudo cerrar el requerimiento");
        }

    }


    @Transactional
    public boolean devolucion(RequerimDevolucionModel requerimIn) {
        log.info("init devolucion");

        try {

            //validaciones
            final var requerimExist = Optional.ofNullable(requerimIn).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro requerim"));
            var requerim = iRequerimRepository.findById(Integer.parseInt(requerimExist.getRequcodi())).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el requerimiento con numero:" + requerimExist.getRequcodi()));

            if (StringUtils.isEmpty(requerimExist.getRequcade())) {
                throw new RecursoNoEncontradoException(Constantes.MENSAJE_REQUERIMIENTO_DEVOLUCION_REQUERIDA_CAUSA);
            }

            if (StringUtils.isEmpty(requerimExist.getRequdede()) || Constantes.CARACTER_NULO.equals(requerimExist.getRequdede())) {
                throw new RecursoNoEncontradoException(Constantes.MENSAJE_REQUERIMIENTO_DEVOLUCION_DETALLAR_CAUSA);
            }
            var solucion = solucionIRepository.findById(requerim.getRequcodi()).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el registro en la tabla solucion con numero:" + requerim.getRequcodi()));
            validarEstadoRequerim(solucion);
            solucion.setSoludevo("S");
            solucion.setSolucicl("N");
            solucion.setSolufecc(null);
            //solucion.setSolufede(Sysdate.getFechaObjectToDate(Constantes.FORMATO_ESTANDAR_FECHA, new Date()));
            solucion.setSolucade(requerimExist.getRequcade());
            solucion.setSoludede(requerimExist.getRequdede());
            solucion.setSoluesta(Constantes.ESTADECL);
            solucionIRepository.save(solucion);
            return true;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TransaccionBaseDatosException("No se pudo hacer la devolucion del requerimiento");
        }

    }

    @Transactional
    public String eliminar(String requcodiIn) {
        log.info("init eliminar requerimiento");
        try {
            final String requcodi = Optional.ofNullable(requcodiIn).filter(requ -> !StringUtils.isEmpty(requ)).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro requcodiIn"));
            var solucion = solucionIRepository.findById(Integer.valueOf(requcodi)).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el registro en la tabla solucion con numero:" + requcodi));
            return requerimRepositoryStoredProcedure.eliminarRequerim(solucion.getSolucodi());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TransaccionBaseDatosException("No se pudo hacer la devolucion del requerimiento");
        }

    }

}

