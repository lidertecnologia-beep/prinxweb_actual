import Image from 'next/image'

const Footer = () => {
  return (
    <>
      <footer className='footer'>
        <div className='footer__logo-left'>
          <Image
            src='/img/logo.png'
            alt='Logo de prinx'
            width={94}
            height={25}
          />
        </div>
      </footer>
    </>
  )
}

export default Footer
