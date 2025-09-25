import { useState, useEffect } from 'react'
import DatosBasicosSolicitud from './DatosBasicosSolicitud'
import ActividadesSolicitud from './ActividadesSolicitud'
import HistorialCamabiosSolicitud from './HistorialCambiosSolicitud'
import { Accordion, AccordionTab } from 'primereact/accordion'
import ListaAnexos from './ListaAnexos'
import useAnexos from '../../customhooks/useAnexos'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import getData from '../../pages/api/api'
import { useSessionContext } from '../../pages/session'
import { CODIGO_ACTIVIDAD_CAFE } from '../../utilites/constants'

const DetalleSolicitud = ({ dataDetalleSolicitud, mostrarDialogDetalle }) => {
  const { dataInitSession } = useSessionContext()
  const [dataDatosBasicos, setDataDatosBasicos] = useState({})
  const [dataActividades, setDataActividades] = useState()
  const [dataHistorialCambios, setDataHistorialCambios] = useState()
  const [filesIn, setFilesIn] = useState(null)
  const [showLoading, setShowLoading] = useState(false)

  const getListDetalle = async (requcodi) => {
    const data = await getData(
      dataInitSession.token,
      `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LIST_REQUERIM_DETALLE_SPRING}${dataInitSession.cliente}&requcodi=${requcodi}`)
    if (data) {
      const listActividades = data?.listMantprod
      const listAnexos = data?.listAnexos
      if (listActividades) {
        setDataActividades(listActividades.filter(detalle => detalle.mapracti !== CODIGO_ACTIVIDAD_CAFE))
        setDataHistorialCambios(listActividades.filter(detalle => detalle.mapracti === CODIGO_ACTIVIDAD_CAFE))
      }

      if (listAnexos) {
        cargaFilesInt(listAnexos)
      }
    }
    setShowLoading(false)
  }

  const cargaFilesInt = async (listAnexos) => {
    try {
      const data = await cargaListaAnexosToObjFile(listAnexos)
      if (data) setFilesIn(data)
      setShowLoading(false)
    } catch (err) {
      setShowLoading(false)
    }
  }

  const { cargaListaAnexosToObjFile } = useAnexos()

  useEffect(() => {
    if (!mostrarDialogDetalle) {
      setDataDatosBasicos(null)
      setDataActividades(null)
      setDataHistorialCambios(null)
      setFilesIn([])
    }
  }, [mostrarDialogDetalle])

  useEffect(() => {
    setShowLoading(true)
    if (dataDetalleSolicitud) {
      setDataDatosBasicos(dataDetalleSolicitud)
      getListDetalle(dataDetalleSolicitud?.requcodi)
    }
    return () => {
      setDataDatosBasicos(null)
      setDataActividades(null)
      setDataHistorialCambios(null)
      setFilesIn([])
      setShowLoading(false)
    }
  }, [])

  return (
    <div>

      <div className='p-mb-4 '>
        <DatosBasicosSolicitud dataDatosBasicos={dataDatosBasicos} />
      </div>

      <Accordion activeIndex={-1}>

        <AccordionTab header='Gestión de actividades'>
          <ActividadesSolicitud actividades={dataActividades} />
        </AccordionTab>

        <AccordionTab header='Historial de cambios fecha de compromiso'>
          <HistorialCamabiosSolicitud historialCambios={dataHistorialCambios} />
        </AccordionTab>

        <AccordionTab header='Anexos'>
          <ListaAnexos listAnexos={filesIn} />
        </AccordionTab>

        <LoadingEvent show={showLoading} />

      </Accordion>

      <LoadingEvent show={showLoading} />

    </div>
  )
}

export default DetalleSolicitud
