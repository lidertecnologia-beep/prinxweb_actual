import { useRef, useEffect } from 'react'
import Image from 'next/image'

export const LoadingPage = (WrappedComponent) => {
  const HocModalLoading = (props) => {
    const refModalClass = useRef(null)
    const showModalEvent = () => refModalClass.current ? refModalClass.current.classList.add('showModalEvent') : ''
    const hideModalEvent = () => refModalClass.current ? refModalClass.current.classList.remove('showModalEvent') : ''

    useEffect(() => {
      showModalEvent()
    }, [])

    return (
      <>
        <div id='modalEvent' className='modalEvent' ref={refModalClass}>
          <div className='modalEvent__loader-img'>
            <Image
              src='/img/aire-logo-img.png'
              alt='Logo de prinx loader'
              width={53}
              height={81}
            />
          </div>
          <div className='modalEvent__text p-mb-4'>
            El Aliado en la Optimización de sus Rentas
          </div>
          <div className='modalEvent__text p-d-flex p-jc-center font-content'>
            Cargando...
          </div>
        </div>
        <WrappedComponent
          {...props}
          setShowModalEvent={showModalEvent}
          setHideModalEvent={hideModalEvent}
        />
      </>
    )
  }

  return HocModalLoading
}

export default LoadingPage
