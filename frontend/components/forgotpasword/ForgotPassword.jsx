import Image from 'next/image'
import { useEffect, useState } from 'react'
import Credential from '../forgotpasword/Credential'
import Validation from '../forgotpasword/Validation'
import ChangePassword from '../forgotpasword/ChangePassword'

const ForgotPassword = () => {
  const [objCodiveri, setObjCodiveri] = useState({})
  const [sendCode, setSendCode] = useState(false)
  const [validatedCode, setValidatedCode] = useState(false)
  const titleCard = 'Restablecer contraseña'

  useEffect(() => {
    return () => {
      setSendCode(false)
      setValidatedCode(false)
    }
  }, [])

  return (
    <>
      <div className='forgotpassword p-d-flex p-flex-column p-jc-center p-ai-center'>
        <div className='forgotpassword__img p-d-flex  p-jc-center'>
          <Image
            src='/img/logo.png'
            alt='Logo de prinx'
            width={194}
            height={50}
            className='p-mb-2'
          />
        </div>
        <h1 className='p-text-center title'> Prinx portal de clientes</h1>
        {!sendCode &&
          <Credential
            titleCard={titleCard}
            setSendCode={setSendCode}
            setObjCodiveri={setObjCodiveri}
          />}
        {sendCode && !validatedCode &&
          <Validation
            titleCard={titleCard}
            setValidatedCode={setValidatedCode}
            objCodiveri={objCodiveri}
            setObjCodiveri={setObjCodiveri}
          />}
        {validatedCode &&
          <ChangePassword
            titleCard={titleCard}
            objCodiveri={objCodiveri}
          />}
      </div>
    </>
  )
}

export default ForgotPassword
