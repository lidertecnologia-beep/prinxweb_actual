/* eslint-disable no-unused-vars */
import Link from 'next/link'
import { Button } from 'primereact/button'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPaperclip } from '@fortawesome/free-solid-svg-icons'
import { ESTADO_TERMINADO, CODIGO_TIPO_TERCERO_CLIENTE, ESTADO_ACTIVO, ESTADO_PENDIENTE } from '../utilites/constants'
import { useSessionContext } from '../pages/session'
import getData from '../pages/api/api'
import useGlobalFiltroTabla from './useGlobalFiltroTabla'

const useTemplateTablaSolicitudes = (handleMostrarDialogDetalle, handleMostrarDialogCierre, handleMostrarDialogDevolucion,
  handleMostrarDialogEditarSolicitud, tipoCliente, arrayColumnasExcel, parameterQueryReporte, setIsDescargaReporte, modelFilter) => {
  const { dataInitSession } = useSessionContext()

  const templatePaginator = {
    layout: 'CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown',
    CurrentPageReport: (options) => {
      return (
        `Mostrando del ${options.first} al ${options.last} de ${options.totalRecords}`
      )
    }
  }

  const actionTemplateDetail = (rowData, column) => {
    return (
      <div>
        <Button
          id={`deta-${rowData.requcodi}`}
          className='p-button-ghost p-button-ghost-small ripple'
          onClick={(e) => handleMostrarDialogDetalle(e, rowData)}
        >
          Detalle
        </Button>
      </div>
    )
  }

  const actionTemplateClose = (rowData, column) => {
    return (
      <>
        {(rowData?.estacodi === ESTADO_TERMINADO && tipoCliente === CODIGO_TIPO_TERCERO_CLIENTE)
          ? <div>
            <Button
              id={`cier-${rowData.requcodi}`}
              className='p-button p-button-small ripple p-ml-2'
              onClick={(e) => handleMostrarDialogCierre(e)}
            >
              Cierre
            </Button>
          </div>
          : ''}
      </>
    )
  }

  const actionTemplateReturn = (rowData, column) => {
    return (
      <>
        {(rowData?.estacodi === ESTADO_TERMINADO && tipoCliente === CODIGO_TIPO_TERCERO_CLIENTE)
          ? <div>
            <Button
              id={`devo-${rowData.requcodi}`}
              className='p-button-ghost p-button-ghost-small ripple p-ml-2'
              onClick={(e) => handleMostrarDialogDevolucion(e)}
            >
              Devolución
            </Button>
          </div>
          : ''}
      </>
    )
  }

  const actionEditSolicitud = (rowData, column) => {
    return (
      <>
        {(ESTADO_PENDIENTE === rowData.requesta && tipoCliente === CODIGO_TIPO_TERCERO_CLIENTE)
          ?
          <div>
            <Button
              id={`edit-${rowData.requcodi}`}
              className='p-button-ghost p-button-ghost-small ripple'
              onClick={(e) => handleMostrarDialogEditarSolicitud(e, rowData)}
            >
              Editar
            </Button>
          </div>
          : ''}
      </>
    )
  }

  const saveAsExcelFile = (buffer, fileName) => {
    import('file-saver').then(module => {
      if (module?.default) {
        const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8'
        const EXCEL_EXTENSION = '.xlsx'
        const data = new Blob([buffer], {
          type: EXCEL_TYPE
        })

        module.default.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION)
      }
    })
  }

  // Función para renombrar las columnas o eliminar propiedades no deseadas
  const excelGetData = (dataList, columns) => {
    const arrayDataTrasnform = []
    dataList.forEach((data, index) => {
      const objeRegi = {}
      columns.forEach((valueCol) => {
        Object.entries(data).forEach(([key, value]) => {
          if (key.toLowerCase() === valueCol) {
            objeRegi[key] = value
          }
        })
      })
      arrayDataTrasnform[index] = objeRegi
    })
    return arrayDataTrasnform
  }

  const excelGetNombreColumnas = (columns) => {
    const nameHeader = []
    return Object.entries(columns.return).forEach(([keyCol, valueCol]) => nameHeader[keyCol] = valueCol.COMENTARIO)
  }

  const excelGetIndicesColumnas = (columns) => {
    const indexColumna = []
    Object.entries(columns.return).forEach(([keyCol, valueCol]) => {
      if (valueCol.COLUMNA) {
        indexColumna[keyCol] = valueCol.COLUMNA.toLowerCase()
      }
    })
    return indexColumna
  }

  const exportExcel = async () => {
    setIsDescargaReporte(true)
    const data = await getData(dataInitSession.token, parameterQueryReporte)
    if (data) {
      import('xlsx').then(xlsx => {
        const colExcel = excelGetNombreColumnas(arrayColumnasExcel)
        const indexExcel = excelGetIndicesColumnas(arrayColumnasExcel)
        const dataExcel = excelGetData(data, indexExcel)
        const worksheet = xlsx.utils.json_to_sheet(dataExcel)
        xlsx.utils.sheet_add_aoa(worksheet, [colExcel], { origin: 'A1' })
        const workbook = { Sheets: { data: worksheet }, SheetNames: ['data'] }
        const excelBuffer = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' })
        saveAsExcelFile(excelBuffer, 'solicitudes')
      })
    }
    setIsDescargaReporte(false)
  }

  const headerTable = (
    <div className='flex align-items-center export-buttons'>
      <Button
        label='Exportar'
        icon='pi pi-file-excel'
        onClick={exportExcel}
        className='p-button-raised p-button-rounded ripple mr-2' data-pr-tooltip='Exportar XLS'
      />
    </div>
  )

  const {
    filtersGlobalTabla,
    $elFiltroGlobalTabla
  } = useGlobalFiltroTabla(modelFilter)

  return {
    templatePaginator,
    actionTemplateDetail,
    actionTemplateClose,
    actionTemplateReturn,
    actionEditSolicitud,
    headerTable,
    filtersGlobalTabla,
    $elFiltroGlobalTabla
  }
}

export default useTemplateTablaSolicitudes
