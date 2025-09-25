import { useEffect, useState } from 'react'

const useDecreaesTimer = (tiempoMinutos, callBack) => {
  const [minutos, setMinutos] = useState(0)
  const [segundos, setSegundos] = useState(0)
  let idInterval = 0

  const resetTimer = () => {
    clearInterval(idInterval)
    setSegundos(0)
    setMinutos(0)
  }

  const lanzarTimer = () => {
    let minutosNumber = +tiempoMinutos
    let segundosNumber = 59
    minutosNumber--
    setMinutos(minutosNumber)

    idInterval = setInterval(() => {
      if (segundosNumber > 0) {
        segundosNumber--
        setSegundos(segundosNumber)
        return
      }

      if (minutosNumber === 0 && segundosNumber === 0) {
        minutosNumber = 0
        segundosNumber = 0
        setMinutos(minutosNumber)
        setSegundos(segundosNumber)
        resetTimer()
        // llegue a cero el temporizador ejecutar el callback
        clearInterval(idInterval)
        if (typeof callBack === 'function') {
          callBack()
        }
        return
      }

      minutosNumber--
      segundosNumber = 59
      setMinutos(minutosNumber)
      setSegundos(segundosNumber)
    }, 1000)
  }

  useEffect(() => {
    return () => {
      resetTimer()
    }
  }, [])

  return {
    minutos,
    segundos,
    setMinutos,
    setSegundos,
    resetTimer,
    lanzarTimer
  }
}

export default useDecreaesTimer
