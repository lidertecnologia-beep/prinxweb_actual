import { createContext, useState, useContext, useMemo } from 'react'

const controller = createContext({})

export const ControllerProvider = ({ children }) => {
  const [addClassShowProfile, setAddClassShowProfile] = useState(false)
  const [addClassShowDescargaArchivos, setAddClassShowDescargaArchivos] = useState(false)
  const [visibleSideBarMenu, setVisibleSideBarMenu] = useState(false)
  const [ejecutaConsultaFiltros, setEjecutaConsultaFiltros] = useState(false)
  const [consultaFiltrosFechaInit, setConsultaFiltrosFechaInit] = useState(null)
  const [consultaFiltrosFechaFina, setConsultaFiltrosFechaFina] = useState(null)
  const [crudDesdeFiltrosTabla, setCrudDesdeFiltrosTabla] = useState(false)

  const valueContextAuth = useMemo(() => ({
    addClassShowProfile,
    setAddClassShowProfile,
    addClassShowDescargaArchivos,
    setAddClassShowDescargaArchivos,
    visibleSideBarMenu,
    setVisibleSideBarMenu,
    ejecutaConsultaFiltros,
    setEjecutaConsultaFiltros,
    consultaFiltrosFechaInit,
    setConsultaFiltrosFechaInit,
    consultaFiltrosFechaFina,
    setConsultaFiltrosFechaFina,
    crudDesdeFiltrosTabla,
    setCrudDesdeFiltrosTabla
  }), [addClassShowProfile, setAddClassShowProfile,
    addClassShowDescargaArchivos, setAddClassShowDescargaArchivos,
    visibleSideBarMenu, setVisibleSideBarMenu,
    ejecutaConsultaFiltros, setEjecutaConsultaFiltros,
    consultaFiltrosFechaInit, setConsultaFiltrosFechaInit,
    consultaFiltrosFechaFina, setConsultaFiltrosFechaFina,
    crudDesdeFiltrosTabla, setCrudDesdeFiltrosTabla])

  return (
    <controller.Provider value={valueContextAuth}>{children}</controller.Provider>
  )
}

export const useController = () => useContext(controller)
export default ControllerProvider
