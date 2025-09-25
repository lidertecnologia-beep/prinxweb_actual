import useExportaExcel from '../../customhooks/useExportaExcel'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import TablaSolicitudesFiltroGlobal from './TablaSolicitudesFiltroGlobal'
import { Button } from 'primereact/button'
import { useState } from 'react'

const TablasSolicitudesHeader = ({ filtersGlobalTabla, setFiltersGlobalTabla, modelFilter, columnas, listSolicitudesParaExcel }) => {
  const [showLoading, setShowLoading] = useState(false)

  const {
    exportExcel
  } = useExportaExcel()

  const exportar = () => {
    setShowLoading(true)
    exportExcel(listSolicitudesParaExcel, columnas)
    setShowLoading(false)
  }

  return (
    <div className='tabla-header'>
      <Button
        label='Exportar'
        icon='pi pi-file-excel'
        onClick={exportar}
        className='p-button-raised p-button-rounded ripple mr-2 btn-export' data-pr-tooltip='Exportar XLS'
      />
      <TablaSolicitudesFiltroGlobal
        modelFilter={modelFilter}
        filtersGlobalTabla={filtersGlobalTabla}
        setFiltersGlobalTabla={setFiltersGlobalTabla}
      />
      <LoadingEvent show={showLoading} />
    </div>
  )
}

export default TablasSolicitudesHeader
