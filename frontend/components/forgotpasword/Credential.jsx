import { Card } from 'primereact/card'
import { InputText } from 'primereact/inputtext'
import { Button } from 'primereact/button'
import Link from 'next/link'
import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import useAlert from '../../customhooks/useAlert'
import { Toast } from 'primereact/toast'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import { insertPost2 } from '../../pages/api/api'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTimes } from '@fortawesome/free-solid-svg-icons'
import { LOGIN } from '../../utilites/paths'
import { MENSAJE_RESTABLECEPW_CREDENCIALES_INVALIDAS, MENSAJE_CAMPO_REQUERIDO } from '../../utilites/constants'

const Credential = (props) => {
  const { titleCard, setSendCode, setObjCodiveri } = props
  const [us, setUs] = useState('')
  const [email, setEmail] = useState('')
  const router = useRouter()
  const [showModal, setShowModal] = useState(false)
  const [errors, setErrors] = useState({ us: '', email: '' })

  const modelCodiveri = { us: '', email: '' }

  const mostarErrorCampos = () => setErrors({
    us: (!us) ? MENSAJE_CAMPO_REQUERIDO.replace('@1', 'usuario') : '',
    email: (!email) ? MENSAJE_CAMPO_REQUERIDO.replace('@1', 'correo') : ''
  })

  const cargaCodiVeri = () => {
    modelCodiveri.us = us
    modelCodiveri.email = email
    return modelCodiveri
  }

  const crearCodigoVerificacion = async () => {
    const data = await insertPost2(`${process.env.NEXT_PUBLIC_ENDPOINT_POST_RESTABLECEPW_SPRING}`, cargaCodiVeri())
    if (!data) {
      alertInfo(MENSAJE_RESTABLECEPW_CREDENCIALES_INVALIDAS)
      return false
    }
    return true
  }

  const handleBtnRestablecer = async () => {
    setSendCode(false)
    setShowModal(true)
    mostarErrorCampos()
    const boSucessCodiVeri = await crearCodigoVerificacion()
    if (us && email && boSucessCodiVeri) {
      setShowModal(false)
      setSendCode(true)
      setObjCodiveri(modelCodiveri)
    }
    setShowModal(false)
  }

  const handleBtnCancelar = () => router.push(LOGIN)

  const {
    toast,
    alertInfo
  } = useAlert()

  useEffect(() => {
    return () => {
      setUs(null)
      setEmail(null)
      setShowModal(false)
    }
  }, [])

  return (
    <>
      <Toast ref={toast} position='center' />
      <Card className='p-shadow-24 card-forgotpassword p-mt-4 p-d-flex p-jc-center p-ai-center'>
        <h2 className='p-d-flex p-jc-center p-mb-4 bold'>{titleCard}</h2>
        <div className='p-fluid p-mb-4 p-d-flex p-jc-center p-flex-column p-ai-center'>
          <div className='p-field w-20'>
            <label className='label' htmlFor='us'>Usuario</label>
            <InputText
              id='us'
              type='text'
              value={us ?? ''}
              onChange={(e) => setUs(e.target.value)}
              onBlur={mostarErrorCampos}
              className='input '
              required
            />
            {errors.us && (
              <p className='p-invalid'>
                <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                {errors.us}
              </p>
            )}
          </div>
          <div className='p-field w-20'>
            <label className='label' htmlFor='correo'>Correo empresarial</label>
            <InputText
              id='email'
              type='text'
              value={email ?? ''}
              onChange={(e) => setEmail(e.target.value)}
              onBlur={mostarErrorCampos}
              className='input'
              required
            />
            {errors.email && (
              <p className='p-invalid'>
                <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
                {errors.email}
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
              onClick={handleBtnRestablecer}
            />
          </div>
        </div>

        <div className='p-field p-mb-md-4 p-d-flex p-jc-center'>
          <Link href='/' className='links font-links'>
            ¿Ya tienes una cuenta? ¡Inicia sesión!
          </Link>
        </div>
      </Card>
      <LoadingEvent show={showModal} />

    </>
  )
}

export default Credential
