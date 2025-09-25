import Login from '../components/login/Login'
import LoginFooter from '../components/login/LoginFooter'

const PageLogin = () => {
  return (
    <div className='page_login'>
      <div className='p-d-flex p-jc-center p-ai-center p-mb-4' style={{ minHeight: '80vh' }}>
        <Login />
      </div>
      <LoginFooter />
    </div>
  )
}

export default PageLogin
