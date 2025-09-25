import { InputText } from 'primereact/inputtext'
import { Card } from 'primereact/card'
import { Button } from 'primereact/button'
import Image from 'next/image'
import { Password } from 'primereact/password'
import { useState, useRef } from 'react'
import useFormValidate from '../../utilites/formValidate/useFormValidate'
import { Messages } from 'primereact/messages'
import { useSessionContext } from '../../pages/session'
import Link from 'next/link'
import SpinnerButton from '../../utilites/buttons/SpinnerButton'
import useLoadDropdown from '../../utilites/useLoadDropdown'
import { requestAuth } from '../../pages/api/api'
import useQuerySolicitudes from '../solicitud/useQuerySolicitudes'
import storeGobal from '../../store/storeGobal'

const Login = () => {
  const showMessage = useRef(null)
  const [data, setData] = useState({ us: '', pw: '' })
  const [spinner, setSpinner] = useState(false)
  const { authenticated, setDataInitSession, setDataDropdown } = useSessionContext()
  const initalForm = { us: null, pw: null }
  const setSesion = storeGobal((state) => state.setSesion)

  const handleInputChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value })
    if (errors) errors[e.target.id] = ''
  }

  const loadDataSession = (data) => {
    if (data) {
      loadingDataDropdown(data).then(dataDropdown => setDataDropdown(dataDropdown))
      setDataInitSession({ ...data })
      // carga de sesion con zustand
      setSesion({ ...data })
      authenticated(data)
      preFetchingSolicitudes()
    }
  }

  const auth = () => {
    setSpinner(true)
    requestAuth(data.us, data.pw, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_AUTH_SPRING}`)
      .then(r => {
        if (r.data.body?.ERROR) {
          setSpinner(false)
          showMessage.current.show([{ severity: 'warn', detail: 'Usuario o clave invalida', life: 3000 }])
          return
        }
        if (r && (r.data.statusCode === 'ok' || r.data.statusCode === 'OK')) {
          loadDataSession(r.data.body)
        }
        setSpinner(false)
      }).catch(e => {
        console.error('auth error', e)
        if (showMessage) {
          setSpinner(false)
          showMessage.current.show([{ severity: 'warn', detail: 'Usuario o clave invalida', life: 3000 }])
        }
      })
  }

  const { loadingDataDropdown } = useLoadDropdown()
  const { errors, handleSubmit } = useFormValidate(auth, initalForm)
  const { preFetchingSolicitudes } = useQuerySolicitudes()

  return (

    <div className='login'>
      <Card className='p-shadow-24 card-login p-mt-4'>
        <div className='card-login__head p-mb-4 p-mt-4'>
          <div className='p-d-flex p-jc-center p-mb-2 '>
            <Image
              src='/img/aire-050.png'
              alt='Logo de prinx'
              width={114}
              height={50}
            />
          </div>
          <h1 className='p-text-center title'>
            Inicie sesión en Prinx<br />portal de clientes
          </h1>
        </div>

        <div className='separator p-mb-4' />

        <form onSubmit={handleSubmit}>
          <div className='p-fluid card-login__content'>
            <div className='p-field'>
              <label className='label' htmlFor='us'>Usuario</label>
              <InputText
                id='us'
                name='us'
                type='text'
                className='input'
                required
                value={data.us}
                onChange={handleInputChange}
              />
              {errors.us && (<p className='p-invalid'>{errors.us}</p>)}
            </div>
            <div className='p-field'>
              <label className='label' htmlFor='pw'>Clave</label>
              <Password
                id='pw'
                name='pw'
                required
                value={data.pw}
                onChange={handleInputChange}
                feedback={false}
                toggleMask
              />
              {errors.pw && (<p className='p-invalid'>{errors.pw}</p>)}
            </div>
            <div className='p-field p-mb-md-4'>
              <Link
                href='/forgotpassword'
                className='links'
              >
                Se te olvidó tu contraseña
              </Link>
            </div>
          </div>

          <div className='card-login__footer p-mb-4'>
            <Button
              type='submit'
              label={spinner ? 'Cargando sesión' : 'Iniciar sesión'}
              className={`p-button-raised p-button-rounded p-button__large ripple ${spinner ? 'show-spinner-btn-disabled' : ''}`}
              disabled={spinner}
            >
              <SpinnerButton showSpinner={spinner} />
            </Button>
            <Messages ref={showMessage} />
          </div>
        </form>
      </Card>
    </div>
  )
}

export default Login
