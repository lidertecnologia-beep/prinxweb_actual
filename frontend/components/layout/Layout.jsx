import Header from '../layout/Header'
import Footer from '../layout/Footer'
import { useEffect, useRef, useState } from 'react'
import NavBar from './NavBar'
import Menu from './Menu'
import { useController } from '../../pages/contextprovider'

const Layout = (props) => {
  const [hidenMenus, setHidenMenus] = useState(false)
  const { setAddClassShowProfile } = useController()
  const refHeader = useRef(null)

  const clickListenerCloseMenu = (e) => {
    if (refHeader.current) {
      if (!refHeader.current.contains(e.target)) {
        setHidenMenus(false)
        setAddClassShowProfile(false)

        // Se oculta el  menu de subir archivos para descarga y elminar archivo en la opcion de crear requerimientos
        const $elAllBtnEvent = Array.from(document.querySelectorAll('.previewLoadFile__iconbutton'))
        $elAllBtnEvent.forEach((el) => {
          const othersDropdownMenu = el.querySelector('.previewLoadFile__dropdownmenu--show')
          if (othersDropdownMenu) othersDropdownMenu.classList.remove('previewLoadFile__dropdownmenu--show')
        })
      }
    }
  }

  useEffect(() => {
    document.addEventListener('click', clickListenerCloseMenu)
  }, [])

  return (
    <>
      <div>
        <div ref={refHeader}>
          <Header hidenMenus={hidenMenus} />
        </div>
        <main className='main-container'>
          {props.children}
        </main>
        <Footer />
        <div className='navbar-mobile'>
          <NavBar />
        </div>
        <Menu />
      </div>
    </>
  )
}

export default Layout
