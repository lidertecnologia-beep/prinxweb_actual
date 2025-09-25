import { useRef } from 'react'

const useAlert = () => {
  const toast = useRef(null)

  // se usara cuando una transancion al sistema donde se genera impacto en la base de datos fue exitoso
  const alertSuccess = (mensaje) => {
    toast.current.show({ severity: 'success', summary: 'Mensaje de exito', detail: mensaje, life: 15000 })
  }

  // se utilizar para notificaciones informativas donde no hay impacto en la base de datos
  const alertInfo = (mensaje) => {
    toast.current.show({ severity: 'info', summary: 'Mensaje de información', detail: mensaje, life: 15000 })
  }

  // Se utilizara para preveinir al usuario se situaciones que puede generar un error o son peligrosas
  const alertWarning = (mensaje) => {
    toast.current.show({ severity: 'warn', summary: 'Mensaje de atención', detail: mensaje, life: 15000 })
  }

  // Se utilizara cuando se generen errors no controlados en el sistema
  const alertError = (mensaje) => {
    toast.current.show({ severity: 'error', summary: 'Mensaje de error', detail: mensaje, life: 15000 })
  }

  return {
    toast,
    alertSuccess,
    alertInfo,
    alertWarning,
    alertError
  }
}

export default useAlert
