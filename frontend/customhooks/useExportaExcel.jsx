const useExportaExcel = () => {
  const excelGetNombreColumnas = (columns) => {
    if (!columns || !columns?.return) return

    const nameHeader = []
    Object.entries(columns?.return).forEach(([keyCol, valueCol]) => nameHeader[keyCol] = valueCol?.COMENTARIO)
    return nameHeader
  }

  const excelGetIndicesColumnas = (columns) => {
    const indexColumna = []

    if (!columns?.return) {
      return
    }

    Object.entries(columns.return).forEach(([keyCol, valueCol]) => {
      if (valueCol.COLUMNA) indexColumna[keyCol] = valueCol.COLUMNA.toLowerCase()
    })

    return indexColumna
  }

  const excelGetAnchoColumnas = (columns) => {
    if (!columns || !columns?.return) return

    const anchoColumna = []
    Object.entries(columns?.return).forEach(([keyCol, valueCol]) => anchoColumna[keyCol] = { wch: valueCol?.ANCHO })
    return anchoColumna
  }

  const excelGetData = (dataList, columnas) => {
    if (!dataList || dataList?.length <= 0 || !columnas || columnas?.length <= 0) return

    const dataSalida = []
    dataList.forEach((data, index) => {
      const registro = {}
      columnas.forEach((columna) => {
        Object.entries(data).forEach(([key, value]) => {
          if (key.toLowerCase() === columna) registro[key] = value
        })
      })

      dataSalida[index] = registro
    })
    return dataSalida
  }

  const saveAsExcelFile = (buffer, fileName) => {
    import('file-saver').then(module => {
      if (module?.default) {
        const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8'
        const EXCEL_EXTENSION = '.xlsx'
        const data = new Blob([buffer], { type: EXCEL_TYPE })
        module.default.saveAs(data, `${fileName}_export_${new Date().getTime()}${EXCEL_EXTENSION}`)
      }
    })
  }

  const exportExcel = (data, columnas) => {
    if (!data && !data?.length > 0) return

    import('xlsx').then(xlsx => {
      const colExcel = excelGetNombreColumnas(columnas)
      const indexExcel = excelGetIndicesColumnas(columnas)
      const anchoColumnas = excelGetAnchoColumnas(columnas)
      const dataExcel = excelGetData(data, indexExcel)
      if (dataExcel && colExcel && anchoColumnas) {
        const worksheet = xlsx.utils.json_to_sheet(dataExcel)
        // const colWidths = [{ wch: 10 },{ wch: 80 },{ wch: 10 },{ wch: 15 },{ wch: 15 },{ wch: 15 },]//ancho de columnas temporal
        worksheet['!cols'] = anchoColumnas
        xlsx.utils.sheet_add_aoa(worksheet, [colExcel], { origin: 'A1', wch: 150 })
        const workbook = { Sheets: { data: worksheet }, SheetNames: ['data'] }
        const excelBuffer = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' })
        saveAsExcelFile(excelBuffer, 'solicitudes')
      }
    })
  }

  return {
    exportExcel
  }
}

export default useExportaExcel
