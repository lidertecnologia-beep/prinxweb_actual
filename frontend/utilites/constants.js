export const CONSULTA_DESDE_ESTADISTICA = 'EDE'
export const CODIGO_TIPO_TERCERO_CLIENTE = '01'
export const TIEMPO_ACTIVO_CODIVERI = '2'
export const TIEMPO_INACTIVIDAD_SESION = 30
export const CAMPO_FECHA_INICIAL_NOT_NULL = 'Campo fecha inicial no puede ser nulo'
export const CAMPO_FECHA_FINAL_NOT_NULL = 'Campo fecha final no puede ser nulo'
export const LIST_ESTADO_PENDIENTES = 'ac,as,co,ea,eb,ed,eo,pe,pr'
export const CLASS_CSS_ICONO_PI_PENCIL = 'pi pi-pencil'
export const CLASS_CSS_ICONO_PI_TIMES = 'pi pi-times'
export const CODIGO_ACTIVIDAD_CAFE = 'CAFE'
export const FORMATO_FECHA_ESTANDAR = 'DD-MM-YYYY'

// rutas
export const ROOT = '/'
export const RECORDAR_PASSWORD = '/forgotpassword'

// key de cookies
export const KEY_COOKIE_AUTH = 'au'
export const KEY_COOKIE_PERSONAL = 'pers'

// nombre de campos de base de datos o objetos json
export const NOMBRE_CAMPO_REQUCODI = 'requcodi'
export const NOMBRE_CAMPO_REQUFECH = 'requfech'
export const NOMBRE_CAMPO_REQUFECO = 'requfeco'
export const NOMBRE_CAMPO_ESTADESC = 'estadesc'
export const NOMBRE_CAMPO_REQUDETA = 'requdeta'
export const NOMBRE_CAMPO_FECHSOLI = 'fechsoli'
export const NOMBRE_CAMPO_FECHFECO = 'fechfeco'

// estados de las solicitudes
export const ESTADO_TERMINADO = 'te'
export const ESTADO_PENDIENTE = 'pe'
export const ESTADO_DEVOLUCION = 'de'
export const ESTADO_CERRADO = 'ce'
export const ESTADO_ACTIVO = 'ac'
export const ESTADO_PROGRAMADO = 'pr'
export const ESTADO_ROADMAP = 'rm'
export const ESTADO_TERMINADO_DESC = 'Terminado'
export const ESTADO_PENDIENTE_DESC = 'Pendiente'
export const ESTADO_DEVOLUCION_DESC = 'Devolucion'
export const ESTADO_CERRADO_DESC = 'Cerrado'

// label etiquetas
export const LABEL_BTN_CREAR = 'Crear'
export const LABEL_BTN_CREANDO = 'Creando'
export const LABEL_BTN_ACTUALIZAR = 'Actualizar'
export const LABEL_BTN_ELIMINAR = 'Eliminar'
export const LABEL_NUMERO_TICKET = 'Número de ticket'
export const LABEL_TIPO_SOLICITUD = 'Tipo de solicitud'
export const LABEL_PRIORIDAD = 'Prioridad'
export const LABEL_PRODUCTO = 'Producto'
export const LABEL_OPCION = 'Opción'
export const LABEL_PRIORIDAD_ATENCION = 'Valida prioridad de atención'
export const LABEL_DETALLE = 'Detalle'
export const LABEL_ESTADO = 'Estado'
export const LABEL_FECHA_SOLICITUD = 'Fecha de solicitud'
export const LABEL_FECHA_COMPROMISO = 'Fecha de compromiso'

// mensajes alertas
export const MENSAJE_ERROR_ACTUALIZAR_REGISTRO2 = 'Error al actualizar el registro'
export const MENSAJE_REGISTRO_CREADO = 'Registro creado correctamente'
export const MENSAJE_REGISTRO_ACTUALIZADO = 'Registro actualizado correctamente'
export const MENSAJE_REGISTRO_ELIMINADO = 'Registro eliminado correctamente'
export const MENSAJE_ERROR_CREAR_REGISTRO = 'Error al crear la solicitud'
export const MENSAJE_ERROR_ACTUALIZAR_REGISTRO = 'Error al actualizar la solicitud'
export const MENSAJE_ERROR_GLOBAL = 'Error al realizar la transacción'
export const MENSAJE_DEVOLUCION_EXITOSA = 'Devolución realizada correctamente'
export const MENSAJE_FECHA_FINAL_MAYOR = 'fecha final debe ser mayor a la fecha inicial'
export const MENSAJE_RESTABLECEPW_CREDENCIALES_INVALIDAS = 'Usuario o correo inválido'
export const MENSAJE_CAMPO_REQUERIDO = 'El campo @1 es requerido'
export const MENSAJE_REENVIO_CODIVERI = 'El código de verificación se reenvió a su correo electrónico empresarial de contacto'
export const MENSAJE_CODIVERI_INVALIDO = 'El código de verificación es invalido'
export const MENSAJE_RESTABLECEPW_PATRON_FUERTE = 'Debe ingresar un patrón de clave fuerte'
export const MENSAJE_RESTABLECEPW_CAMBIOPW_INVALIDO = 'Cambio de clave invalido'
export const MENSAJE_RESTABLECEPW_CAMBIOPW_EXITOSO = 'Cambio de clave exitoso'
export const MENSAJE_CAMBIO_PW = 'Para el cambio de contraseña se debe ingresar números, letras minúsculas y mayúscula'
export const MENSAJE_ESPACIOS_NOMBRES_ARCHIVOS = 'No se permite que el nombre de los archivos contengan espacios'
export const MENSAJE_ACENTOS_NOMBRES_ARCHIVOS = 'No se permite que el nombre de los archivos contengan tildes o acentos'

// patrones expresiones regulares
export const STRONG_REGEX_PW = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/
export const REGEX_ESPACIOS_CADENAS = /\s/
export const REGEX_CADENAS_ACENTOS = /[áéíóúÁÉÍÓÚñÑ]/

// key de reqct query
export const KEY_QUERY_PREFETCH_SOLICITUDES = 'prefetch-solicitudes'
export const KEY_QUERY_SOLICITUDES = 'solicitudes'

export const MEDIO_COMUNICACION_HERRAMIENTA_INTERNET = '03'