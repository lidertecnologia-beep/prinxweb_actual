import Profile from '../profile/Profile'
import { useController } from '../../pages/contextprovider'
import { useSessionContext } from '../../pages/session'

const Header = () => {
  const { addClassShowProfile, setAddClassShowProfile, setVisibleSideBarMenu } = useController()
  const { dataInitSession } = useSessionContext()

  return (
    <>
      <header className='header separator'>
        <div className='header__menu'>
          <div id='toogle-menu' className='toogle-menu' onClick={() => setVisibleSideBarMenu(true)}>
            <div />
          </div>
        </div>
        <div className='header__perfil'>
          <div className='perfil-container'>
            <div className='perfil-nombre'>{dataInitSession.descUsuario}</div>
            <div className='perfil-empresa'>{dataInitSession.descripcionCliente}</div>
          </div>
          <div className='perfil-icon' onClick={() => setAddClassShowProfile(!addClassShowProfile)}>{dataInitSession.personalIniciales}</div>
          <Profile />
        </div>
      </header>
    </>
  )
}

export default Header
