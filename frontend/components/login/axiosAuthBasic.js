import Axios from 'axios'

const urls = {
  test: 'http://',
  dev: 'http://',
  production: `${process.env.NEXT_PUBLIC_ENV_LOCAL_PATH_API}`
}

const axiosAuthBasic = Axios.create({
  baseURL: urls[process.env.NODE_ENV],
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export default axiosAuthBasic
