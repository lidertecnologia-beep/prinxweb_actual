import Image from 'next/image'
import { useEffect } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookF, faYoutube, faLinkedin } from '@fortawesome/free-brands-svg-icons'

const LoginFooter = () => {
  // corregir se debe poner en un listener de load o DOMContentLoaded y resize de la ventana
  useEffect(() => {
    const elFooterImgLogo = document.getElementById('login-footer-img-logo')
    const elFooterContent = document.getElementById('login-footer-content')
    if (elFooterImgLogo && elFooterContent) {
      const heightFooterImgLogo = elFooterImgLogo.getBoundingClientRect().height
      const heightFooterContent = elFooterContent.getBoundingClientRect().height
      const elFooter = document.getElementById('login-footer')
      if (elFooter) {
        elFooter.style.height = `${(heightFooterImgLogo * 2) + heightFooterContent}px`
      }
    }
  }, [])

  return (
    <>
      <div id='login-footer' className='login-footer'>

        <Image
          src='/img/footer-login.jpg'
          alt='Imagen del footer login'
          fill
        />

        <div className='login-footer__layer'>
          <div className='p-d-flex p-jc-center p-ai-center p-mt-4 p-mb-4'>
            <Image
              id='login-footer-img-logo'
              src='/img/logo.png'
              alt='Imagen del footer login'
              width={194}
              height={50}
            />
          </div>
          <div id='login-footer-content' className='p-grid login-footer__content p-mb-6'>
            <div className='p-col-12 p-md-3 p-mb-md-4'>
              <h2 className='p-mb-2 title'>Smart TMT</h2>
              <ul className='col-borderL'>
                <li>Cali - Colombia</li>
              </ul>
            </div>
            <div className='p-col-12 p-md-3 p-mb-md-4'>
              <h2 className='p-mb-2 title'>Contáctenos</h2>
              <ul className='col-borderL'>
                <li>Asesoría Comercial: 3186085239</li>
                <li><a href='https://smarttmt.com/' className='links' target='_blank' rel='noreferrer'>Página Web</a></li>
              </ul>
            </div>
            <div className='p-col-12 p-md-3 p-mb-md-4'>
              <h2 className='p-mb-2 title'>Acerca de la empresa</h2>
              <ul className='col-borderL'>
                <li><a href='https://smarttmt.com/quienes-somos/' className='links' target='_blank' rel='noreferrer'>Quienes somos</a></li>
                <li><a href='https://smarttmt.com/productos/' className='links' target='_blank' rel='noreferrer'>Productos</a></li>
                <li><a href='https://smarttmt.com/servicios_smart/implantacion-y-actualizacion/' className='links' target='_blank' rel='noreferrer'>Servicios</a></li>
              </ul>
            </div>
            <div className='p-col-12 p-md-3 p-mb-md-4'>
              <h2 className='p-mb-2 title'>Síguenos</h2>
              <ul className='col-borderL'>
                <li><a href='https://www.facebook.com/SMART-TMT-710484722681912/' className='links' target='_blank' rel='noreferrer'><FontAwesomeIcon icon={faFacebookF} size='2x' /></a></li>
                <li><a href='https://www.youtube.com/channel/UCb1797ml1gwB4M8hpYMEznQ' className='links' target='_blank' rel='noreferrer'><FontAwesomeIcon icon={faYoutube} size='2x' /></a></li>
                <li><a href='https://www.linkedin.com/company/smart-tmt/' className='links' target='_blank' rel='noreferrer'><FontAwesomeIcon icon={faLinkedin} size='2x' /></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default LoginFooter
