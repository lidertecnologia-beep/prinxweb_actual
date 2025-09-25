import { Calendar } from 'primereact/calendar'
import { useEffect, useState } from 'react'
import { Dialog } from 'primereact/dialog'
import { Button } from 'primereact/button'
import RegistroSolicitud from './RegistroSolicitud'
import { useController } from '../../pages/contextprovider'
import getDate from '../../utilites/dates'
import useAlert from '../../customhooks/useAlert'
import { Toast } from 'primereact/toast'
import { MENSAJE_FECHA_FINAL_MAYOR, CAMPO_FECHA_INICIAL_NOT_NULL, CAMPO_FECHA_FINAL_NOT_NULL } from '../../utilites/constants'
import { campoSoloNumero } from '../../utilites/validaTipoDatoCampo'

const CabeceraPaginaSolicitudes = () => {
  const [dateInit, setDateInit] = useState(null)
  const [dateEnd, setDateEnd] = useState(null)
  const [mostrarDialogRegistroSolicitud, setMostrarDialogRegistroSolicitud] = useState(false)
  const { setEjecutaConsultaFiltros, setConsultaFiltrosFechaInit, setConsultaFiltrosFechaFina, setCrudDesdeFiltrosTabla } = useController()

  const validarRangoFechas = (fehcInic, fechFina) => {
    if (!fehcInic) {
      alertInfo(CAMPO_FECHA_INICIAL_NOT_NULL)
      return false
    }

    if (!fechFina) {
      alertInfo(CAMPO_FECHA_FINAL_NOT_NULL)
      return false
    }

    if (fechFina < fehcInic) {
      alertInfo(MENSAJE_FECHA_FINAL_MAYOR)
      return false
    }

    return true
  }

  const ejecutarConsulta = () => {
    setEjecutaConsultaFiltros(true)
    if ((dateInit || dateEnd) && validarRangoFechas(dateInit, dateEnd)) {
      setConsultaFiltrosFechaInit(getDate(dateInit, 'DD-MM-YYYY'))
      setConsultaFiltrosFechaFina(getDate(dateEnd, 'DD-MM-YYYY'))
      return
    }
    setConsultaFiltrosFechaInit(null)
    setConsultaFiltrosFechaFina(null)
  }

  const limpiarConsulta = () => {
    setDateInit(null)
    setDateEnd(null)
    setConsultaFiltrosFechaInit(null)
    setConsultaFiltrosFechaFina(null)
    setEjecutaConsultaFiltros(false)
  }

  // manejo de alertas
  const {
    toast,
    alertInfo
  } = useAlert()

  useEffect(() => {
    return () => {
      limpiarConsulta()
    }
  }, [])

  return (
    <div>
      <Toast ref={toast} position='center' />
      <div className='p-fluid p-grid p-justify-center p-ai-center separator p-mb-6'>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-2 p-mt-3'>
          Consulta de rangos por fechas
        </div>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-2'>
          <label className='label' htmlFor='fechaInicial'>Fecha inicial</label>
          <Calendar
            id='fechaInicial'
            value={dateInit}
            onChange={(e) => setDateInit(e.value)}
            showIcon
            dateFormat='dd-MM-yy'
            mask='99/99/9999'
            placeholder='dd-MM-yyyy'
            onKeyDown={(e) => campoSoloNumero(e)}
          />
        </div>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-2'>
          <label className='label' htmlFor='fechaFinal'>Fecha final</label>
          <Calendar
            id='fechaFinal'
            value={dateEnd}
            onChange={(e) => setDateEnd(e.value)}
            showIcon
            dateFormat='dd-MM-yy'
            mask='99/99/9999'
            placeholder='dd-MM-yyyy'
            onKeyDown={(e) => campoSoloNumero(e)}
          />
        </div>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-1 p-lg-text-right'>
          <Button
            label='Consultar'
            className='p-button-raised p-button-rounded ripple p-mt-2'
            onClick={() => ejecutarConsulta()}
          />
        </div>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-1 p-lg-text-right'>
          <Button
            label='Limpiar'
            className='p-button-raised p-button-rounded p-button-ghost  ripple p-mt-2'
            onClick={() => limpiarConsulta()}
          />
        </div>

        <div className='p-field p-md-2 p-lg-offset-2 add-solicitud'>
          <Button
            label='Solicitud'
            icon='pi pi-plus'
            className='p-button-raised p-button-rounded ripple p-mt-2'
            onClick={() => setMostrarDialogRegistroSolicitud(true)}
          />
        </div>

      </div>

      <Dialog
        header='Registro de solicitud'
        visible={mostrarDialogRegistroSolicitud}
        position='center'
        style={{ width: '80vw' }}
        onHide={() => setMostrarDialogRegistroSolicitud(false)}
        draggable={false}
        resizable={false}
        baseZIndex={100}
      >
        <RegistroSolicitud
          crudDesdeFiltrosTabla={setCrudDesdeFiltrosTabla}
        />
      </Dialog>
    </div>
  )
}

export default CabeceraPaginaSolicitudes
