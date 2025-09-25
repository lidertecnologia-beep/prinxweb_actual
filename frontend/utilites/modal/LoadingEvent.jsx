import { useRef, useEffect } from 'react'
import Image from 'next/image'

export const LoadingEvent = ({ show }) => {
  const refModalClass = useRef(null)
  const showModalEvent = () => refModalClass.current ? refModalClass.current.classList.add('showModalEventElement') : ''
  const hideModalEvent = () => refModalClass.current ? refModalClass.current.classList.remove('showModalEventElement') : ''

  useEffect(() => {
    show ? showModalEvent() : hideModalEvent()
  }, [show])

  return (
    <>
      <div id='modalEventElement' className='modalEventElement modalEventElement--backgroundDark' ref={refModalClass}>
        <div className='modalEvent__loader-img'>
          <Image
            src='/img/aire-logo-img.png'
            alt='Logo de prinx loader'
            width={53}
            height={81}
          />
        </div>
      </div>
    </>
  )
}

export default LoadingEvent
