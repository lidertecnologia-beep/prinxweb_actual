import { useEffect, useState } from 'react'

const DatosBasicosSolicitud = ({ dataDatosBasicos }) => {
  const reqPriorities = {
    1: 'Alta',
    2: 'Media Alta',
    3: 'Media',
    4: 'Media Baja',
    5: 'Baja'
  }

  const [detalleSolicitud, setDetalleSolicitud] = useState()

  useEffect(() => {
    if (dataDatosBasicos) setDetalleSolicitud(dataDatosBasicos)
  }, [dataDatosBasicos])

  return (
    <>
      {detalleSolicitud &&
        <div className='font-size-base'>
          <div className='p-fluid p-grid'>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Número de solicitud</label>
            </div>
            <div className='p-md-2'>
              <div>{detalleSolicitud.requcodi}</div>
            </div>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Fecha de registro</label>
            </div>
            <div className='p-md-2 '>
              <div>{detalleSolicitud.fechsoli}</div>
            </div>
          </div>
          <div className='p-fluid p-grid'>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Solicitado por</label>
            </div>
            <div className='p-md-2'>
              <div>{detalleSolicitud.requsoli}</div>
            </div>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Medio</label>
            </div>
            <div className='p-md-2 '>
              <div>{detalleSolicitud.mecodesc}</div>
            </div>
          </div>
          <div className='p-fluid p-grid'>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Tipo</label>
            </div>
            <div className='p-md-2'>
              <div>{detalleSolicitud.clredesc}</div>
            </div>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Producto</label>
            </div>
            <div className='p-md-2 '>
              <div>{detalleSolicitud.proddesc}</div>
            </div>
          </div>
          <div className='p-fluid p-grid'>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Prioridad</label>
            </div>
            <div className='p-md-2'>
              <div>{reqPriorities[detalleSolicitud.requprio] || ''}</div>
            </div>
          </div>
          <div className='p-fluid p-grid'>
            <div className='p-md-2'>
              <label className='label' htmlFor='us'>Detalle</label>
            </div>
            <div className='p-md-10'>
              <div>{detalleSolicitud.requdeta}</div>
            </div>
          </div>
        </div>}
    </>
  )
}

export default DatosBasicosSolicitud
