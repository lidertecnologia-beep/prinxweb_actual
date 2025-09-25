import Image from 'next/image'
import Link from 'next/link'
import { Sidebar } from 'primereact/sidebar'
import { useEffect, useState } from 'react'
import { useController } from '../../pages/contextprovider'
import Profile from '../profile/Profile'

const Menu = () => {
  const [sidebarModal, setSidebarModal] = useState(false)

  const { visibleSideBarMenu, setVisibleSideBarMenu } = useController()

  const onHideMenu = (valor) => setVisibleSideBarMenu(valor)

  useEffect(() => {
    setSidebarModal(true)
    if (!visibleSideBarMenu) {
      onHideMenu(false)
      setSidebarModal(false)
    }
  }, [visibleSideBarMenu])

  useEffect(() => {
    return () => {
      setVisibleSideBarMenu(false)
      setSidebarModal(false)
    }
  }, [])

  return (
    <Sidebar
      visible={visibleSideBarMenu}
      onHide={() => onHideMenu(false)}
      modal={sidebarModal}
    >
      <nav id='menu' className='menu'>
        <div className='menu__img  p-mb-4'>
          <Link href='/Home'>
            <Image
              src='/img/logo.png'
              alt='Logo de prinx'
              width={194}
              height={50}
            />
          </Link>
        </div>
        <ul>
          <li>
            <Link href='/home' className='links font-title-menu' onClick={() => setVisibleSideBarMenu(false)}>
              Inicio
            </Link>
          </li>
          <li>
            <Link href='/registrosolicitud' className='links font-title-menu' onClick={() => setVisibleSideBarMenu(false)}>
              Nueva solicitud
            </Link>
          </li>
          <li>
            <Link href='/consultasolicitudes' className='links font-title-menu' onClick={() => setVisibleSideBarMenu(false)}>
              Consultar solicitudes
            </Link>
          </li>
          <li>
            <Link
              href='https://docs.google.com/forms/d/e/1FAIpQLSd5x9sB-nAxdpuYRv50PNuRHmCaNBCXsEk7pBh6IEZCDQOyqw/viewform'
              className='links font-title-menu'
              target='_blank'
              onClick={() => setVisibleSideBarMenu(false)} rel='noreferrer'
            >
              PQRS
            </Link>
          </li>
        </ul>
        <ul className='menu__items-mobile'>
          <Profile isMobile />
        </ul>
      </nav>
    </Sidebar>
  )
}

export default Menu
