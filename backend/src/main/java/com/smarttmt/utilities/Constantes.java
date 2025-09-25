/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarttmt.utilities;

/**
 * @author Usuario
 */
public class Constantes {

    public static final String CARACTER_NULO = ".";
    public  static final String PARAMETRO_RETURN = "return";
    public  static final String PARAMETRO_SBCLIENTE = "sbCliente";
    public  static final String PARAMETRO_SBESTADO = "sbEstado";
    public  static final String VALOR_RETORNO_TRUE = "true";
    public  static final String VALOR_RETORNO_FALSE = "false";


    //registro de clasifiacion de requerimiento
    public static final String CLASREQUDESCPETICION = "Petición"; //Clasificacion de requerimiento Peticion
    public static final String CLASREQUDESCQR = " Quejas y Reclamos"; //Clasificacion de requerimiento Quejas y Reclasos
    public static final String CLASREQUCODIGPETICION = "PE"; //Clasificacion de requerimiento Peticion
    public static final String CLASREQUCODIGQR = "QR"; //Clasificacion de requerimiento Quejas y Reclasos

    //registro de prioridad
    public static final String CODIGPRIORIDAD1 = "1"; //Codigo prioridad 1
    public static final String CODIGPRIORIDAD2 = "2"; //Codigo prioridad 2
    public static final String CODIGPRIORIDAD3 = "3"; //Codigo prioridad 3
    public static final String CODIGPRIORIDAD4 = "4"; //Codigo prioridad 4
    public static final String CODIGPRIORIDAD5 = "5"; //Codigo prioridad 5
    public static final String CODIGPRIORIDAD1DESC = "1 - Alta"; //Descripcion prioridad 1
    public static final String CODIGPRIORIDAD2DESC = "2 - Media - Alta"; //Descripcion prioridad 2
    public static final String CODIGPRIORIDAD3DESC = "3 - Media"; //Descripcion prioridad 3
    public static final String CODIGPRIORIDAD4DESC = "4 - Media - Baja"; //Descripcion prioridad 4
    public static final String CODIGPRIORIDAD5DESC = "5 - Baja"; //Descripcion prioridad 5

    //Registro de califacion del servicio
    public static final String CODIGCALIFSERVC1 = "1"; //Codigo calificacion 1
    public static final String CODIGCALIFSERVC2 = "2"; //Codigo calificacion 2
    public static final String CODIGCALIFSERVC3 = "3"; //Codigo calificacion 3
    public static final String CODIGCALIFSERVC4 = "4"; //Codigo calificacion 4
    public static final String CODIGCALIFSERVC5 = "5"; //Codigo calificacion 5
    public static final String CODIGCALIFSERVCDESC1 = "Malo"; //Descripcion calificacion 1
    public static final String CODIGCALIFSERVCDESC2 = "Deficiente"; //Descripcion calificacion 2
    public static final String CODIGCALIFSERVCDESC3 = "Regular"; //Descripcion calificacion 3
    public static final String CODIGCALIFSERVCDESC4 = "Bueno"; //Descripcion calificacion 4
    public static final String CODIGCALIFSERVCDESC5 = "Excelente"; //Descripcion calificacion 5

    //estados
    public static final String ESTACERR = "ce"; //cerrado
    public static final String ESTAACTI = "ac"; //activo
    public static final String ESTATERM = "te"; //terminado
    public static final String ESTADECL = "dc"; //terminado
    public static final String ESTADO_PROGRAMADO = "pr";
    public static final String DESC_ESTADO_PENDIENTE = "Pendiente";

    //Url
    public static final String URL_DESCARGA_ANEXOS = "ce"; //cerrado
    public static final String PATH_DOCROOT = "com.sun.aas.instanceRoot"; //cerrado
    public static final String ID_DOCROOT = "docroot"; //cerrado

    public static final String USUARIO_ADMINISTRADOR_BLOG = "A"; //cerrado
    public static final String USUARIO_DE_GESTION = "U"; //cerrado

    //Codigo de reportes excel estado de requerimiento
    public static final String REPORTE_EXCEL_ER = "ER"; //Estado de requerimientos
    public static final String REPORTE_EXCEL_HC = "HC"; //Historial de cambio fechas de requerimientos
    public static final String REPORTE_EXCEL_AC = "AC"; //Actividades de requerimientos

    //parametros
    public static final String VIGENCIAS_CANTIDAD_ESTADOS = "0"; //vigencias en años
    public static final String PARAOPER_URL_PRINX_CLIENTE = "URAPPRCL";
    public static final String PARAOPER_RUTA_ARCHIVOS_PRINX_CLIENTE = "RDARPRCL";
    public static final String PARAOPER_RUTA_ARCHIVOS_PRINX_ASP = "RUTAARCH";
    public static final String PARAOPER_LISTA_CHQUEO_INCIDENTES = "LISTCHIN";
    public static final String PARAOPER_CLASES_OBJETOS_FORMA = "CLASOBFO";



    //Lista de chqueo de incidentes
    public static final String PRESENTA_BLOQUEO_TOTAL_LA_OPERACION = "01";
    public static final String PRESENTA_BLOQUEO_EN_UN_PROCESO_GESTION = "02";
    public static final String BLOQUEA_PROCESO_PUEDE_CONTINUAR_OPERACION = "03";
    public static final String ERROR_FUNCIONAL_NO_BLOQUEOS = "04";
    public static final String NO_APLICA = "05";
    public static final String CODIGOS_REQUERIMIENTO_PRIORIDAD = "12345";
    public static final String ITEM_ITCHCODI_CODIGO = "ITCHCODI:";
    public static final String ITEM_ITCHCHEQ_CHEQUEADO = "ITCHCHEQ:";


    public static final String KEY_MENSAJE_ERROR1 = "mensaje.error1";
    public static final String KEY_MENSAJE_ERROR2 = "mensaje.error2";
    public static final String KEY_MENSAJE_ERROR3 = "mensaje.error3";
    public static final String KEY_MENSAJE_ERROR4 = "mensaje.error4";
    public static final String KEY_MENSAJE_ERROR5 = "mensaje.error5";
    public static final String KEY_MENSAJE_ERROR6 = "mensaje.error6";
    public static final String KEY_MENSAJE_ERROR7 = "mensaje.error7";
    public static final String KEY_MENSAJE_ERROR9 = "mensaje.error9";
    public static final String KEY_MENSAJE_ERROR20 = "mensaje.error20";
    public static final String KEY_MENSAJE_ERROR28 = "mensaje.error28";
    public static final String KEY_MENSAJE_ERROR29_ID_SOLICITUD_NULO = "mensaje.error29";
    public static final String CLASIFICACION_REQUERIM_INCIDENTE = "TIREINCI";
    public static final String TIPO_REQUERIM_INCIDENTE = "TRDEINCI";
    public static final String PARAOPER_ID_TOKEN = "IDTOPRWS";
    public static final String FORMATO_ESTANDAR_FECHA = "dd-MM-yyyy";
    public static final String FORMATO_FECHA_YYYYMMDD = "yyyy-MM-dd";
    public static final String FORMATO_FECHA2 = "dd-MMM-yyyy";
    public static final String RETORNO_STRING_TRUE = "true";

    public static final String KEY_MAP_TERMINADOS = "terminados";
    public static final String KEY_MAP_CERRADOS = "cerrados";
    public static final String KEY_MAP_DEVOLUCIONES = "devoluciones";
    public static final String KEY_MAP_PENDIENTES = "pendientes";
    public static final String KEY_MAP_PROGRAMADOS = "programados";
    public static final String KEY_MAP_ROADMAP = "roadmap";

    //mensajes
    public static final String MENSAJE_ERROR_NO_EXISTE_REQUERIM = "No existe el requerimiento";
    public static final String MENSAJE_RESPUESTA_OK = "ok";
    public static final String MENSAJE_ERROR_NO_EXISTE_PERSONAL = "No existe el personal";
    public static final String MENSAJE_ERROR_NO_EXISTE_TERCERO = "No existe el tercero";
    public static final String MENSAJE_ERROR_NO_EXISTE_USUABADA = "No existe el usuario";
    public static final String MENSAJE_MANTPROD_NULO = "No existe el mantprod numero requerimiento @1";
    public static final String MENSAJE_CAUSAS_DEVOLUCION = "No existe la lista de causas de devolucion";
    public static final String MENSAJE_ENTIDAD_REQUERIM_NULA = "El objeto entidad solucion esta nulo";
    public static final String MENSAJE_REGISTRO_SOLUCION_NO_EXISTE = "El registro de soluccion no existe numero de requerimiento @1";
    public static final String TOKEN_INVALIDO = "Token invalido";
    public static final String TIPOREQU_CAMPO_TIRETIRE_NULO = "Campo tiretire es requerido";
    public static final String TIPOREQU_CAMPO_TIRECODI_NULO = "Campo tirecodi es requerido";
    public static final String LISTA_CLASIFICACION_REQUERIMIENTO_VACIA = "Lista  de clasificacion de requerimientos no existe";




    //nombre columnas
    public static final String TERCCOAL = "TERCCOAL";
    public static final String TERCTIPO = "TERCTIPO";
    public static final String TERCDESC = "TERCDESC";
    public static final String TERCCODI = "TERCCODI";
    public static final String DEVOLUCIONES = "DEVOLUCIONES";
    public static final String PENDIENTES = "PENDIENTES";
    public static final String TERMINADOS = "TERMINADOS";
    public static final String CERRADOS = "CERRADOS";
    public static final String PROGRAMADOS = "PROGRAMADOS";
    public static final String ROADMAP = "ROADMAP";
    public static final String CAMPO_REQUCODI = "REQUCODI";
    public static final String ITEM_REQUCODI = "REQUCODI:";
    public static final String CAMPO_SOLUCODI = "SOLUCODI";
    public static final String ITEM_SOLUCODI = "SOLUCODI:";
    public static final String COLUMNA_CODIGO_VERIFICACION_CODIGO_ALTERNO = "COVECOAL";
    public static final String COLUMNA_CODIGO_VERIFICACION_TERCERO = "COVETERC";




    //items
    public static final String ITEM_ERROR = "ERROR";

    //metodos controller mensajed de validacion
    public static final String MENSAJE_REQUESTPARAM_CLIENTE_NO_NULO = "El campo cliente no debe ser nulo";
    public static final String MENSAJE_REQUESTPARAM_CLIENTE_NO_VACIO = "El campo cliente no debe estar vacío";
    public static final String MENSAJE_REQUESTPARAM_FECHA_INICIAL_NO_NULO = "El campo fechaInicial no debe ser nulo";
    public static final String MENSAJE_REQUESTPARAM_FECHA_INICIAL_NO_VACIO = "El campo fechaInicial  no debe estar vacío";
    public static final String MENSAJE_REQUESTPARAM_FECHA_FINAL_NO_NULO = "El campo fechaFinal no debe ser nulo";
    public static final String MENSAJE_REQUESTPARAM_FECHA_FINAL_NO_VACIO = "El campo fechaFinal  no debe estar vacío";
    public static final String MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_NULO = "El campo numero_del requerimiento no debe ser nulo";
    public static final String MENSAJE_REQUESTPARAM_NUMERO_REQUERIMIENTO_NO_VACIO = "El campo numero_del requerimiento  no debe estar vacío";
    public static final String MENSAJE_REQUESTPARAM_ESTADO_NO_NULO = "El campo estado no debe ser nulo";
    public static final String MENSAJE_REQUESTPARAM_ESTADO_NO_VACIO = "El campo estado  no debe estar vacío";
    public static final String MENSAJE_CLIENTE_NO_EXISTE = "El cliente no existe";
    public static final String MENSAJE_CANTIDAD_ESTADOS_SIN_REGISTROS = "No se encontro registro de cantidad de estados";
    public static final String MENSAJE_CALIFICACION_SERVICIO =  "Debe registrar la calificacion del servicio";
    public static final String MENSAJE_REGISTRO_SOLUCION_NO_TERMINADO =  "Para proceso de cierre o devolucion el requerimiento debe de estar en estado terminado";
    public static final String MENSAJE_REQUERIMIENTO_CERRADO =  "Requerimiento se encuentra cerrado";
    public static final String MENSAJE_REQUERIMIENTO_DEVOLUCION_REQUERIDA_CAUSA =  "Debe registrar una causa de devolucion";
    public static final String MENSAJE_REQUERIMIENTO_DEVOLUCION_DETALLAR_CAUSA =  "Debe describir la causa de devolucion";


}
