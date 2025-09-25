import { Card } from 'primereact/card'
import { Password } from 'primereact/password'
import { Button } from 'primereact/button'
import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import { LOGIN } from '../../utilites/paths'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTimes } from '@fortawesome/free-solid-svg-icons'
import useAlert from '../../customhooks/useAlert'
import { Toast } from 'primereact/toast'
import { getPost } from '../../pages/api/api'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import useDecreaesTimer from '../../customhooks/useDecreaseTimer'
import useValidatePWChange from '../../customhooks/useValidatePwChange'
import {
  MENSAJE_RESTABLECEPW_CAMBIOPW_EXITOSO, STRONG_REGEX_PW, MENSAJE_CAMBIO_PW
} from '../../utilites/constants'

const ChangePassword = (props) => {
  const { titleCard, objCodiveri } = props
  const [pwn, setPwn] = useState('')
  const [pwc, setPwc] = useState('')
  const router = useRouter()
  const [showModal, setShowModal] = useState(false)
  const [updateValidPw, setUpdateValidPw] = useState(false)

  const handleBtnCancelar = () => router.push(LOGIN)

  const responseValidatePw = async () => {
    setShowModal(true)

    if (!boValidPw) {
      if (messageError && messageError !== '') {
        alertInfo(messageError)
      }
      setShowModal(false)
      return
    }

    const data = await getPost(`${process.env.NEXT_PUBLIC_ENDPOINT_POST_RESTABLECEPW_CAMBIAPW_SPRING}`, { ...objCodiveri, pw: pwn })

    if (data === true) {
      alertSuccess(MENSAJE_RESTABLECEPW_CAMBIOPW_EXITOSO)
    }

    setShowModal(false)
    lanzarTimer()
  }

  const actualizar = () => {
    if (pwn || pwc) {
      validatePw()
      setUpdateValidPw(!updateValidPw)
    }
  }

  const {
    boValidPw,
    errorInput,
    messageError,
    validatePw,
    mostarErrorCampos
  } = useValidatePWChange(pwn, pwc)

  const {
    lanzarTimer,
    resetTimer
  } = useDecreaesTimer('1', handleBtnCancelar)

  const {
    toast,
    alertInfo,
    alertSuccess
  } = useAlert()

  useEffect(() => {
    responseValidatePw()
  }, [boValidPw, updateValidPw])

  useEffect(() => {
    return () => {
      resetTimer()
      setPwn('')
      setPwc('')
    }
  }, [])

  return (
    <>
      <Toast ref={toast} position='center' />
      <Card className='p-shadow-24 card-forgotpassword p-mt-4 p-d-flex p-jc-center p-ai-center'>
        <h2 className='p-d-flex p-jc-center p-mb-4 bold'>{titleCard}</h2>
        <p className='p-d-flex p-jc-center p-mb-4 font-size-base'>{MENSAJE_CAMBIO_PW}</p>
        <div className='p-fluid p-mb-4 p-d-flex p-jc-center p-ai-center p-flex-column'>
          <div className='p-field w-15 '>
            <label className='label' htmlFor='us'>Contraseña nueva</label>
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
              onBlur={mostarErrorCampos}
            />
            {errorInput.pwn && (
              <p className='p-invalid'>
                <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                {errorInput.pwn}
              </p>
            )}
          </div>
          <div className='p-field w-15'>
            <label className='label' htmlFor='correo'>Confirmar contraseña</label>
            <Password
              id='pwc'
              value={pwc}
              onChange={(e) => setPwc(e.target.value)}
              promptLabel='Confirme clave'
              weakLabel='Debil'
              mediumLabel='Medio'
              strongLabel='Fuerte'
              strongRegex={STRONG_REGEX_PW}
              onBlur={mostarErrorCampos}
            />
            {errorInput.pwc && (
              <p className='p-invalid'>
                <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                {errorInput.pwc}
              </p>
            )}
          </div>
        </div>

        <div className='p-fluid p-grid p-d-flex p-justify-center p-mb-5'>
          <div className='p-field p-d-flex p-justify-center p-col-12 p-sm-12 p-md-12 p-lg-5'>
            <Button
              label='Cancelar'
              className='p-button-raised p-button-rounded p-button-ghost  ripple w-p-100'
              onClick={handleBtnCancelar}
            />

          </div>
          <div className='p-field p-d-flex p-justify-center p-col-12 p-sm-12 p-md-12 p-lg-5'>
            <Button
              label='Restablecer'
              className='p-button-raised p-button-rounded ripple w-p-100'
              onClick={() => actualizar()}
            />
          </div>
        </div>

      </Card>
      <LoadingEvent show={showModal} />
    </>
  )
}

export default ChangePassword
