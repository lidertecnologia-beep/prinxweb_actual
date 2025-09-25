'use client'
import { useEffect, useState } from 'react'
import storeGlobal from '../store/storeGobal'
import getData from '../pages/api/api'

const useCargaListaValoresRegistroSolicitud = (setShowModal) => {
  const [tipoSolicitudes, setTipoSolicitudes] = useState()
  const [prioridades, setPrioridades] = useState()
  const [productos, setProductos] = useState()
  const [validaAtencion, setValidaAtencion] = useState()
  const [productoOpciones, setProductoOpciones] = useState(null)
  const [productoSelecionado, setProductoSelecionado] = useState()

  const cargaListProductoOpciones = async () => {
    if (productoSelecionado) {
      setShowModal(true)
      const productoOpciones = await getData(sesion.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_OBJETO_PRODUCTO}${sesion.cliente}&producto=${productoSelecionado}`)
      setProductoOpciones(productoOpciones)
      setShowModal(false)
    }
  }

  const getDataListaValores = async () => {
    if (sesion?.token && sesion?.cliente) {
      const [listValoTipoSolicitud, listValoPrioridad, listValoProducto, listValoValidaAtencion] = await Promise.all([
        getData(sesion?.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_TIPOREQUERIM_SPRING}${sesion.cliente}`),
        getData(sesion?.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_PRIORIDAD_SPRING}${sesion.cliente}`),
        getData(sesion?.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_PRODUCTO_SPRING}${sesion.cliente}`),
        getData(sesion?.token, `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_INCIDENTE_SPRING}${sesion.cliente}`)
      ])
      setTipoSolicitudes(listValoTipoSolicitud)
      setPrioridades(listValoPrioridad)
      setProductos(listValoProducto)
      setValidaAtencion(listValoValidaAtencion)
    }
  }

  const sesion = storeGlobal(state => state.sesion)

  useEffect(() => {
    cargaListProductoOpciones()
  }, [productoSelecionado])

  useEffect(() => {
    getDataListaValores()
  }, [sesion])

  return {
    tipoSolicitudes,
    setTipoSolicitudes,
    prioridades,
    setPrioridades,
    productos,
    setProductos,
    validaAtencion,
    setValidaAtencion,
    productoOpciones,
    setProductoOpciones,
    productoSelecionado,
    setProductoSelecionado
  }
}

export default useCargaListaValoresRegistroSolicitud
