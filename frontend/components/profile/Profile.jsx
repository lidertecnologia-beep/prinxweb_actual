import { Card } from 'primereact/card'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faSignOutAlt } from '@fortawesome/free-solid-svg-icons'
import Link from 'next/link'
import { useSessionContext } from '../../pages/session'
import { useController } from '../../pages/contextprovider'

const Profile = ({ isMobile = false }) => {
  const { logOut } = useSessionContext()
  const { addClassShowProfile, setAddClassShowProfile, setVisibleSideBarMenu } = useController()

  function handlerClickProfile () {
    setAddClassShowProfile(false)
    if (isMobile) setVisibleSideBarMenu(false)
  }

  function handlerCliclLogOut () {
    setAddClassShowProfile(false)
    logOut()
  }

  return (
    <Card
      id='profile' className={
            (isMobile || addClassShowProfile) ? 'p-shadow-6 profile toogle-profile font-title-menu' : 'p-shadow-6 profile font-title-menu'
}
    >
      <ul>
        <li className='p-pb-3'>
          <Link href='/perfil' className='links' onClick={handlerClickProfile}>
            <FontAwesomeIcon icon={faUser} size='1x' className='color-icons p-mr-2' />
            <span className='font-content-base'>Editar usuario</span>
          </Link>
        </li>
        <li className='p-pb-3'>
          <Link href='/' className='links' onClick={handlerCliclLogOut}>
            <FontAwesomeIcon icon={faSignOutAlt} size='1x' className='color-icons p-mr-2' />
            <span className='font-content-base'>Cerrar sesión</span>
          </Link>
        </li>
      </ul>
    </Card>
  )
}

export default Profile
