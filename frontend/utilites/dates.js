import moment from 'moment'
import 'moment/locale/es'

export const fechVali = (fech) => moment(fech).isValid()

export const templateRowDataFech = (fech, format) => {
  if (fechVali(fech)) return <> {moment(fech).format(format)}</>
}

export const isFecha = (str) => {
  const timestamp = Date.parse(str)
  return !isNaN(timestamp)
}

export const isFechaValidForFilterTable = (fecha) => {
  const formatos = ['DD-MM-YYYY']
  const fechaMoment = moment(fecha, formatos, true)
  return fechaMoment.isValid()
}

// formato estandar DD-MM-YYYY
export const getStrToDateFormatEstandar = (fechaString) => {
  if (fechaString) {
    const datoFecha = fechaString.split('-')
    return new Date(datoFecha[2], datoFecha[1], datoFecha[0])
  }
}

const getDate = (fech, format) => {
  if (fechVali(fech)) {
    return moment(fech, format).format(format)
  } else {
    return ''
  }
}

export default getDate
