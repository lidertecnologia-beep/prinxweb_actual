import { Card } from 'primereact/card'
import { InputText } from 'primereact/inputtext'
import { Button } from 'primereact/button'
import Link from 'next/link'
import { useRouter } from 'next/router'
import { useEffect, useState } from 'react'
import useDecreaesTimer from '../../customhooks/useDecreaseTimer'
import { getPost, insertPost2 } from '../../pages/api/api'
import useAlert from '../../customhooks/useAlert'
import { Toast } from 'primereact/toast'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import { LOGIN } from '../../utilites/paths'
import {
  TIEMPO_ACTIVO_CODIVERI, MENSAJE_ERROR_GLOBAL,
  MENSAJE_REENVIO_CODIVERI, MENSAJE_CODIVERI_INVALIDO
} from '../../utilites/constants'

const Validation = (props) => {
  const { titleCard, objCodiveri, setObjCodiveri, setValidatedCode } = props
  const [codigo, setCodigo] = useState()
  const [showModal, setShowModal] = useState(false)
  const router = useRouter()

  const cargaCodiVeri = () => {
    setObjCodiveri({ ...objCodiveri, codiveri: codigo })
    return { ...objCodiveri, codiveri: codigo }
  }

  const handleBtnCancelar = () => router.push(LOGIN)

  const timer = () => `${minutos}:${segundos < 10 ? '0' + segundos : segundos}`

  const handleBtnValidar = async (e) => {
    e.preventDefault()
    setShowModal(true)
    const data = await getPost(`${process.env.NEXT_PUBLIC_ENDPOINT_POST_RESTABLECEPW_VALIDACODIVERI_SPRING}`, cargaCodiVeri())
    if (data === true) {
      setValidatedCode(true)
      setShowModal(false)
      return
    }
    setShowModal(false)
    alertWarning(MENSAJE_CODIVERI_INVALIDO)
  }

  const handleForwardCode = async (e) => {
    e.preventDefault()
    setShowModal(true)
    const data = await insertPost2(`${process.env.NEXT_PUBLIC_ENDPOINT_POST_RESTABLECEPW_SPRING}`, cargaCodiVeri())
    if (data === false) {
      alertError(MENSAJE_ERROR_GLOBAL)
      setShowModal(false)
      return
    }
    setShowModal(false)
    alertSuccess(MENSAJE_REENVIO_CODIVERI)
  }

  const {
    minutos,
    segundos,
    resetTimer,
    lanzarTimer
  } = useDecreaesTimer(TIEMPO_ACTIVO_CODIVERI, handleBtnCancelar)

  const {
    toast,
    alertWarning,
    alertError,
    alertSuccess
  } = useAlert()

  useEffect(() => {
    lanzarTimer()
    return () => {
      resetTimer()
      setCodigo(null)
      setShowModal(false)
    }
  }, [])

  return (
    <>
      <Toast ref={toast} position='center' />
      <Card className='p-shadow-24 card-forgotpassword p-mt-4 p-d-flex p-jc-center p-ai-center'>
        <h2 className='p-d-flex p-jc-center p-mb-4 bold'>{titleCard}</h2>

        <div className='p-fluid p-mb-4'>
          <div className='p-field p-mb-5'>
            Ingrese el código de verificación que se envió a su correo electrónico empresarial de contacto
          </div>
          <div className='p-d-flex p-jc-center'>
            {timer()}
          </div>
          <div className='p-d-flex p-jc-center'>
            <div className='p-field w-20 '>
              <InputText
                id='codigo'
                value={codigo}
                className='input p-text-center'
                onChange={(e) => setCodigo(e.target.value)}
                required
              />
            </div>
          </div>
        </div>

        <div className='p-field p-mb-md-6 p-d-flex p-jc-end'>
          <span className='span-norecibecode font-links'>¿No recibiste el codigo?</span>
          <Link href='#' onClick={handleForwardCode} className='links font-links font-weight-600'>
            Reenviar codigo
          </Link>
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
              label='Validar'
              className='p-button-raised p-button-rounded ripple w-p-100'
              onClick={handleBtnValidar}
            />
          </div>
        </div>
      </Card>
      <LoadingEvent show={showModal} />
    </>
  )
}

export default Validation
