import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import { useState, useEffect } from 'react'

const HistorialCamabiosSolicitud = ({ historialCambios }) => {
  const [listCambios, setListCambios] = useState([{}])

  useEffect(() => {
    if (historialCambios) setListCambios(historialCambios)
  }, [historialCambios])

  return (

    <DataTable
      value={listCambios}
      paginator
      responsiveLayout='stack'
      paginatorTemplate='FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown'
      currentPageReportTemplate='Pagina {first} de {last} total  registros {totalRecords}'
      rows={10}
      rowsPerPageOptions={[5, 10, 20]}
      dataKey='recucodi'
    >

      <Column
        field='requResp'
        header='Responsable'
        style={{ textAlign: 'left', width: '9%' }}
        filter filterPlaceholder='Filtrar solicitud'
      />
      <Column
        field='maprfech'
        header='Fecha de registro'
        style={{ textAlign: 'left', width: '8%' }}
        filter filterPlaceholder='Filtrar fecha'
      />
      <Column
        field='maprdeta'
        header='Detalle actividad'
        style={{ textAlign: 'left', width: '65%', wordWrap: 'break-word', /* IE 5.5-7 */whitSpace: '-moz-pre-wrap', /* Firefox 1.0-2.0 */whiteSpace: 'pre-wrap' /* Modern browsers */ }}
        filter filterPlaceholder='Filtrar detalle'
      />
    </DataTable>
  )
}

export default HistorialCamabiosSolicitud
