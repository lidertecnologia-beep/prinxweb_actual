/* eslint-disable no-unused-vars */
import { useEffect, useMemo, useState } from 'react'
import Anexos from './Anexos'

const ListaAnexos = ({ listAnexos }) => {
  const [anexos, setAnexos] = useState([])

  const memoListaAnexos = useMemo(() => {
    return (
      <>
        <div className='p-fluid p-grid p-d-flex p-mt-4 p-mb-4'>
          <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-12'>
            <p className='p-ml-4'>Cantidad:{(listAnexos?.length) ? listAnexos?.length : 0}</p>
          </div>
        </div>

        <div className='p-fluid p-grid p-d-flex p-jc-center'>
          <div className='p-field p-col-12 p-sm-12 p-md-10 p-lg-10'>
            <Anexos
              files={listAnexos}
              sendFiles={false}
              idSolicitud={null}
              clear={false}
            />
          </div>
        </div>
      </>
    )
  }, [listAnexos])

  useEffect(() => {
    setAnexos(listAnexos)
    return () => {
      setAnexos([])
    }
  }, [])

  return memoListaAnexos
}

export default ListaAnexos
