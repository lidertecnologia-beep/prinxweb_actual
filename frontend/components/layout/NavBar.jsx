import Image from 'next/image'
import { useController } from '../../pages/contextprovider'
import { useSessionContext } from '../../pages/session'

const NavBar = () => {
  const { setVisibleSideBarMenu } = useController()
  const { dataInitSession } = useSessionContext()

  return (
    <>
      <div className='navbar'>
        <a href='#' className='logo' />
        <nav className='bottom-nav'>
          <ul className='bottom-nav__menu'>
            <li className='item p-d-flex p-jc-center'>
              <a href='#' className='link active'>
                <Image
                  src='/img/logo.png'
                  alt='Logo de prinx'
                  width={104}
                  height={30}
                />
              </a>
            </li>
            <li className='item p-d-flex p-jc-center'>
              <div onClick={() => setVisibleSideBarMenu(true)} className='link perfil-icon p-d-flex p-ai-center'>{dataInitSession.personalIniciales}</div>
            </li>
          </ul>
        </nav>
      </div>
    </>
  )
}

export default NavBar
