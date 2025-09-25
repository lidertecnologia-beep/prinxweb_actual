import { InputText } from 'primereact/inputtext'
import { Password } from 'primereact/password'
import { Button } from 'primereact/button'
import { useEffect, useState, useRef } from 'react'
import { useSessionContext } from '../../pages/session'
import getData, { getPost, requestPut } from '../../pages/api/api'
import useValidatePwChange from '../../customhooks/useValidatePwChange'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import { Toast } from 'primereact/toast'
import useAlert from '../../customhooks/useAlert'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTimes } from '@fortawesome/free-solid-svg-icons'
import useFormValidate from '../../utilites/formValidate/useFormValidate'
import {
  STRONG_REGEX_PW, MENSAJE_CAMBIO_PW, MENSAJE_REGISTRO_ACTUALIZADO, MENSAJE_ERROR_ACTUALIZAR_REGISTRO2, MENSAJE_RESTABLECEPW_CAMBIOPW_INVALIDO
} from '../../utilites/constants'

const FormProfile = () => {
  const { dataInitSession } = useSessionContext()
  const [pwn, setPwn] = useState('')
  const [pwc, setPwc] = useState('')
  const [showModal, setShowModal] = useState(false)
  const [updateValidPw, setUpdateValidPw] = useState(false)
  const refForm = useRef(null)
  const initalForm = {
    perscodi: '',
    persterc: '',
    persnomb: '',
    persapel: '',
    perstele: '',
    persemai: ''
  }

  const getPersonal = async () => {
    const data = await getData(dataInitSession.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_PERSONAL_SPRING}${dataInitSession.cliente}&idPersonal=${dataInitSession.personal}`)
    setDataInput(data)
  }

  const actualizarPersonal = async () => {
    const data = await requestPut(dataInitSession.token, `${process.env.NEXT_PUBLIC_ENDPOINT_PUT_PERSONAL_SPRING}`, dataInput)
    if (data && data.toLowerCase() !== 'ok') {
      alertError(MENSAJE_ERROR_ACTUALIZAR_REGISTRO2)
      return
    }
    (!pwn && !pwc) ? alertSuccess(MENSAJE_REGISTRO_ACTUALIZADO) : actualizarClave()
  }

  const responseValidatePw = async () => {
    if (!boValidPw) {
      messageError && messageError !== '' ? alertInfo(messageError) : setShowModal(false)
      return
    }

    setShowModal(true)
    const objChangePw = { us: dataInput.persusua, email: dataInput.persemai, pw: pwn }
    const data = await getPost(`${process.env.NEXT_PUBLIC_ENDPOINT_POST_RESTABLECEPW_CAMBIAPW_SPRING}`, objChangePw)
    setShowModal(false)
    if (data === false) {
      alertError(MENSAJE_RESTABLECEPW_CAMBIOPW_INVALIDO)
      return
    }
    if (data === true) alertSuccess(MENSAJE_REGISTRO_ACTUALIZADO)
  }

  const actualizarClave = () => {
    if (pwn || pwc) {
      validatePw()
      setUpdateValidPw(!updateValidPw)
    }
  }

  const handleInputPw = () => (pwn || pwc) ? mostarErrorCampos() : clearMostarErrorCampos()

  const sendForm = () => {
    setShowModal(true)
    if (isSubmitting) {
      actualizarPersonal()
      actualizarClave()
    }
    setShowModal(false)
  }

  const {
    boValidPw,
    errorInput,
    messageError,
    validatePw,
    mostarErrorCampos,
    setBoValidPw,
    clearMostarErrorCampos
  } = useValidatePwChange(pwn, pwc)

  const {
    toast,
    alertInfo,
    alertSuccess,
    alertError
  } = useAlert()

  const {
    isSubmitting,
    errors,
    dataInput,
    setDataInput,
    handleSubmit,
    handleChange
  } = useFormValidate(sendForm, initalForm, refForm)

  useEffect(() => {
    responseValidatePw()
  }, [boValidPw, updateValidPw])

  useEffect(() => {
    getPersonal()
    return () => {
      setPwn('')
      setPwc('')
      setBoValidPw(false)
    }
  }, [])

  return (
    <>
      <form id='formPersonal' noValidate onSubmit={handleSubmit} ref={refForm}>
        <Toast ref={toast} position='center' />
        <div className='p-grid nested-grid'>
          <div className='p-md-6 p-lg-offset-3'>
            <div className='p-fluid p-grid p-justify-center'>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-8'>
                <label className='label' htmlFor='us'>Código de empresa</label>
                <InputText
                  id='persterc'
                  name='persterc'
                  className='input'
                  value={dataInput.persterc}
                  disabled
                />
              </div>
            </div>
            <div className='p-fluid p-grid p-justify-center'>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-8'>
                <label className='label' htmlFor='persnomb'>Nombres</label>
                <InputText
                  id='persnomb'
                  name='persnomb'
                  className={` input ${(errors.persnomb) ? 'input-invalid' : ''} `}
                  value={dataInput.persnomb}
                  required
                  onChange={handleChange}
                />
                {errors.persnomb && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errors.persnomb}
                  </p>
                )}
              </div>
            </div>
            <div className='p-fluid p-grid p-justify-center'>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-8'>
                <label className='label' htmlFor='persapel'>Apellidos</label>
                <InputText
                  id='persapel'
                  name='persapel'
                  className={` input ${(errors.persapel) ? 'input-invalid' : ''} `}
                  value={dataInput.persapel}
                  required
                  onChange={handleChange}
                />
                {errors.persapel && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errors.persapel}
                  </p>
                )}
              </div>
            </div>
            <div className='p-fluid p-grid p-justify-center p-mb-4'>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-4'>
                <label className='label' htmlFor='perstele'>Celular</label>
                <InputText
                  id='perstele'
                  name='perstele'
                  className={` input ${(errors.perstele) ? 'input-invalid' : ''} `}
                  value={dataInput.perstele}
                  required
                  onChange={handleChange}
                />
                {errors.perstele && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errors.perstele}
                  </p>
                )}
              </div>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-4'>
                <label className='label' htmlFor='persemai'>Email</label>
                <InputText
                  id='persemai'
                  name='persemai'
                  className={` input ${(errors.persemai) ? 'input-invalid' : ''} `}
                  value={dataInput.persemai}
                  required
                  onChange={handleChange}
                />
                {errors.persemai && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errors.persemai}
                  </p>
                )}
              </div>
            </div>
          </div>
        </div>
        <div className='p-grid nested-grid'>
          <div className='p-md-6 p-lg-offset-3'>
            <div className='p-mb-5 p-fluid p-grid p-justify-center'>
              <div className='p-col-12 p-md-12 p-lg-offset-4 subtitle p-mb-2'>
                <h2>Cambio de clave</h2>
              </div>

              <div className='p-col-12 p-md-12 p-lg-offset-4 p-mb-2'>
                <p className='font-size-base'>{MENSAJE_CAMBIO_PW}</p>
              </div>

              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-4'>
                <label className='label' htmlFor='us'>Clave nueva</label>
                <Password
                  id='pwn'
                  name='pwn'
                  value={pwn ?? ''}
                  onChange={(e) => setPwn(e.target.value)}
                  promptLabel='Ingrese clave'
                  weakLabel='Debil'
                  mediumLabel='Medio'
                  strongLabel='Fuerte'
                  strongRegex={STRONG_REGEX_PW}
                  onBlur={handleInputPw}
                />
                {errorInput.pwn && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errorInput.pwn}
                  </p>
                )}
              </div>
              <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-4'>
                <label className='label' htmlFor='us'>Confirmar clave</label>
                <Password
                  id='pwc'
                  name='pwc'
                  value={pwc ?? ''}
                  onChange={(e) => setPwc(e.target.value)}
                  promptLabel='Ingrese clave'
                  weakLabel='Debil'
                  mediumLabel='Medio'
                  strongLabel='Fuerte'
                  strongRegex={STRONG_REGEX_PW}
                  onBlur={handleInputPw}
                />
                {errorInput.pwc && (
                  <p className='p-invalid'>
                    <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                    {errorInput.pwc}
                  </p>
                )}
              </div>
            </div>
            <div className='p-fluid p-grid p-justify-end'>
              <div className='p-col-4'>
                <Button
                  type='submit'
                  label='Actualizar'
                  className='p-button-raised p-button-rounded ripple'
                />
              </div>
            </div>
            <div className='p-md-3' />
          </div>
        </div>
      </form>
      <LoadingEvent show={showModal} />
    </>
  )
}

export default FormProfile
