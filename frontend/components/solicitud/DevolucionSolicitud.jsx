import { useState, useEffect, useRef } from 'react'
import { Dropdown } from 'primereact/dropdown'
import { InputTextarea } from 'primereact/inputtextarea'
import { Button } from 'primereact/button'
import { useSessionContext } from '../../pages/session'
import useFormValidate from '../../utilites/formValidate/useFormValidate'
import SpinnerButton from '../../utilites/buttons/SpinnerButton'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTimes } from '@fortawesome/free-solid-svg-icons'
import { updatePut } from '../../pages/api/api'
import { MENSAJE_DEVOLUCION_EXITOSA, ESTADO_DEVOLUCION } from '../../utilites/constants'

const DevolucionSolicitud = ({ numeroSolicitud, hideDialogDevolucion, alertSuccess, alertError, listSolicitudes, setListSolicitudes }) => {
  const [solicitud, setSolicitud] = useState()
  const [listCausasDevolucion, setListCausasDevolucion] = useState(null)
  const { dataInitSession, dataDropdown } = useSessionContext()
  const [spinner, setSpinner] = useState(false)
  const initalForm = { causa: null, detalle: null }
  const refForm = useRef(null)

  const cargaListValo = () => {
    if (dataDropdown) {
      setListCausasDevolucion(dataDropdown.causrequ)
    }
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
      const arrayEstado = dataDropdown?.estados?.filter(estado => estado?.estacodi === ESTADO_DEVOLUCION)
      solicitudEditar.estacodi = arrayEstado[0].estacodi
      solicitudEditar.estadesc = arrayEstado[0].estadesc
      const indice = listaEditar.findIndex((elemento) => elemento?.requcodi === solicitudEditar?.requcodi)
      if (indice >= 0) {
        listaEditar.splice(indice, 1, solicitudEditar)
        setListSolicitudes([...listaEditar])
      }
    }
  }

  const ejecutarDevolucion = async () => {
    setSpinner(true)
    try {
      if (isSubmitting) {
        const formRequerim = { requcodi: null, requcade: null, requdede: null }
        let data = null
        formRequerim.requcodi = solicitud
        formRequerim.requcade = dataInput.causa.codigo
        formRequerim.requdede = dataInput.detalle
        data = await updatePut(dataInitSession.token, `${process.env.NEXT_PUBLIC_ENDPOINT_REQUERIM_DEVOLUCION_SPRING}${dataInitSession.cliente}`, formRequerim)
        if (data === 'ok' || data === 'OK') {
          modficarEstadoLista()
          alertSuccess(MENSAJE_DEVOLUCION_EXITOSA)
          hideDialogDevolucion(false)
          setSpinner(false)
          return
        }
        alertError(data)
        hideDialogDevolucion(false)
        setSpinner(false)
      }
    } catch (e) {
      setSpinner(false)
    }
  }

  const {
    isSubmitting,
    errors,
    dataInput,
    isDisabledBotonSubmit,
    handleChange,
    handleSubmit
  } = useFormValidate(ejecutarDevolucion, initalForm, refForm)

  useEffect(() => {
    if (numeroSolicitud) {
      cargaListValo()
      setSolicitud(numeroSolicitud)
    }
  }, [])

  return (

    <form id='formRegistroSolicitud' noValidate onSubmit={handleSubmit} ref={refForm}>
      <div className='p-fluid p-grid'>
        <div className='p-field  p-md-2'>
          <label className='label' htmlFor='us'>Numero solicitud</label>
        </div>
        <div className='p-md-2'>
          <div>{solicitud}</div>
        </div>
      </div>

      <div className='p-fluid p-grid'>
        <div className='p-field p-col-12 p-sm-12 p-md-6 p-flex p-lg-6'>
          <label className='label' htmlFor='causa'>Causa</label>
          <Dropdown
            id='causa'
            name='causa'
            className={` input ${(errors.causa) ? 'input-invalid' : ''} `}
            value={dataInput.causa}
            options={listCausasDevolucion}
            onChange={handleChange}
            optionLabel='descripcion'
            filter showClear
            filterBy='descripcion'
            placeholder='Seleccione una causa'
            emptyFilterMessage='No se encontraron resultados'
            required
          />
          {errors.causa && (
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors.causa}
            </p>)}
        </div>
      </div>
      <div className='p-fluid p-grid'>
        <div className='p-field p-col-12 p-sm-12 p-md-6 p-flex p-lg-12'>
          <label className='label' htmlFor='detalle'>Detalle</label>
          <InputTextarea
            id='detalle'
            name='detalle'
            className={` input ${(errors.detalle) ? 'input-invalid' : ''} `}
            value={dataInput.detalle}
            onChange={handleChange}
            rows={8}
            cols={30}
            autoResize
            required
          />
          {errors.detalle && (
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors.detalle}
            </p>
          )}
        </div>
      </div>
      <div className='p-field p-md-12 p-grid p-flex p-jc-end'>
        <Button
          type='submit'
          label={spinner ? 'Devolviendo' : 'Devolver'}
          className={`p-button-raised p-button-rounded ripple ${spinner ? 'show-spinner-btn-disabled' : ''}`}
          disabled={isDisabledBotonSubmit || spinner}
        >
          <SpinnerButton showSpinner={spinner} />
        </Button>
      </div>
    </form>
  )
}

export default DevolucionSolicitud
