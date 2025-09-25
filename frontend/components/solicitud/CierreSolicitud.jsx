import { useState, useEffect } from 'react'
import { Rating } from 'primereact/rating'
import { Button } from 'primereact/button'
import { updatePut } from '../../pages/api/api'
import formRequerim from '../../pages/api/formsObject'
import { useSessionContext } from '../../pages/session'
import SpinnerButton from '../../utilites/buttons/SpinnerButton'
import { ESTADO_CERRADO, MENSAJE_REGISTRO_ACTUALIZADO } from '../../utilites/constants'

const CierreSolicitud = ({ numeroSolicitud, hideDialogCierre, alertSuccess, alertError, listSolicitudes, setListSolicitudes }) => {
  const [califacionNumero, setCalifacionNumero] = useState(null)
  const [califacionLetras, setCalifacionLetras] = useState(null)
  const [solicitud, setSolicitud] = useState(null)
  const [disabledBtn, setDisabledBtn] = useState(true)
  const [spinner, setSpinner] = useState(false)
  const { dataInitSession, dataDropdown } = useSessionContext()

  const objCalificaLetras = {
    1: function () { setCalifacionLetras('Malo') },
    2: function () { setCalifacionLetras('Deficiente') },
    3: function () { setCalifacionLetras('Aceptable') },
    4: function () { setCalifacionLetras('Bueno') },
    5: function () { setCalifacionLetras('Excelente') },
    default: function () { setCalifacionLetras(null) }
  }

  const modficarEstadoLista = () => {
    let solicitudEditar = {}
    const listaEditar = [...listSolicitudes]
    const numberSolicitud = +numeroSolicitud
    listaEditar.some(solicitud => {
      if (numberSolicitud === solicitud?.requcodi) {
        solicitudEditar = { ...solicitud }
        return true
      }
    })

    if (solicitudEditar && Object.entries(solicitudEditar).length > 0) {
      const arrayEstado = dataDropdown?.estados?.filter(estado => estado?.estacodi === ESTADO_CERRADO)
      solicitudEditar.estacodi = arrayEstado[0].estacodi
      solicitudEditar.estadesc = arrayEstado[0].estadesc
      const indice = listaEditar.findIndex((elemento) => elemento?.requcodi === solicitudEditar?.requcodi)
      if (indice >= 0) {
        listaEditar.splice(indice, 1, solicitudEditar)
        setListSolicitudes([...listaEditar])
      }
    }
  }

  const cerrar = async () => {
    try {
      if (solicitud && califacionNumero) {
        setSpinner(true)
        formRequerim.requcodi = solicitud
        formRequerim.requcali = califacionNumero
        const returnData = await updatePut(dataInitSession.token, `${process.env.NEXT_PUBLIC_ENDPOINT_REQUERIM_CIERRE_SRING}${dataInitSession.cliente}`, formRequerim)
        if (returnData === 'ok' || returnData === 'OK') {
          modficarEstadoLista()
          alertSuccess(MENSAJE_REGISTRO_ACTUALIZADO)
        } else {
          alertError(returnData)
        }
      }
    } catch (e) {
      console.error('Error cerrar requerimiento ', e)
    } finally {
      setSpinner(false)
      hideDialogCierre(false)
    }
  }

  useEffect(() => {
    if (califacionNumero) {
      setDisabledBtn(false)
      typeof califacionNumero === 'number' ? objCalificaLetras[califacionNumero]() : objCalificaLetras.default()
    }
  }, [califacionNumero])

  useEffect(() => {
    if (numeroSolicitud) setSolicitud(numeroSolicitud)
  })

  return (
    <div className='p-grid p-flex-column p-justify-center p-ai-center'>

      <h2 className='p-mb-4'>¿Como valoras la calificación del servicio?</h2>
      <div className='p-mb-4'>
        <Rating
          value={califacionNumero}
          cancel={false}
          onChange={(e) => setCalifacionNumero(e.value)}
        />
      </div>
      <h3 className='p-mb-4'>{califacionLetras}</h3>
      <div className='p-mb-4'>
        <p>Número de solicitud a cerrar {solicitud}</p>
      </div>
      <div className='p-mb-4'>
        <Button
          label={spinner ? 'Cerrando' : 'Cerrar solicitud'}
          className={`p-button-raised p-button-rounded ripple p-mt-2${spinner ? 'show-spinner-btn-disabled' : ''}`}
          onClick={() => cerrar()}
          disabled={disabledBtn || spinner}
        >
          <SpinnerButton showSpinner={spinner} />
        </Button>

      </div>
    </div>
  )
}

export default CierreSolicitud
