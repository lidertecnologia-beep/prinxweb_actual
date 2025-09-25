import { useEffect, useState } from 'react'
import {
  STRONG_REGEX_PW, MENSAJE_CAMPO_REQUERIDO, MENSAJE_RESTABLECEPW_PATRON_FUERTE,
  MENSAJE_RESTABLECEPW_CAMBIOPW_INVALIDO
} from '../utilites/constants'

const useValidatePWChange = (pwnew, pwconfirmed) => {
  const strongRegex = STRONG_REGEX_PW
  const [boValidPw, setBoValidPw] = useState(false)
  const [errorInput, setErrorInput] = useState({ pwn: '', pwc: '' })
  const [messageError, setMessageError] = useState('')

  const clearMostarErrorCampos = () => setErrorInput({ pwn: '', pwc: '' })

  const mostarErrorCampos = () => setErrorInput({
    pwn: (!pwnew) ? MENSAJE_CAMPO_REQUERIDO.replace('@1', 'contraseña nueva') : '',
    pwc: (!pwconfirmed) ? MENSAJE_CAMPO_REQUERIDO.replace('@1', 'confirmar contraseña') : ''
  })

  const validatePw = () => {
    if (!pwnew || !pwconfirmed) {
      mostarErrorCampos()
      setMessageError('')
      setBoValidPw(false)
      return
    }

    const boValidPwn = strongRegex.test(pwnew)
    const boValidPwc = strongRegex.test(pwconfirmed)

    if (!boValidPwn || !boValidPwc) {
      setMessageError(MENSAJE_RESTABLECEPW_PATRON_FUERTE)
      setBoValidPw(false)
      return
    }

    if (pwnew !== pwconfirmed) {
      setMessageError(MENSAJE_RESTABLECEPW_CAMBIOPW_INVALIDO)
      setBoValidPw(false)
      return
    }

    setBoValidPw(true)
  }

  useEffect(() => {
    return () => {
      setErrorInput({ pwn: '', pwc: '' })
      setMessageError('')
      setBoValidPw(false)
    }
  }, [])

  return {
    boValidPw,
    errorInput,
    messageError,
    validatePw,
    setBoValidPw,
    mostarErrorCampos,
    clearMostarErrorCampos
  }
}

export default useValidatePWChange
