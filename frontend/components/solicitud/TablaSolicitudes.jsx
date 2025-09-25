/* eslint-disable react-hooks/exhaustive-deps */
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import { useState, useEffect } from 'react'
import { Dialog } from 'primereact/dialog'
import { Dropdown } from 'primereact/dropdown'
import DetalleSolicitud from './DetalleSolicitud'
import CierreSolicitud from './CierreSolicitud'
import DevolucionSolicitud from './DevolucionSolicitud'
import getData from '../../pages/api/api'
import { useSessionContext } from '../../pages/session'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import { useController } from '../../pages/contextprovider'
import { Toast } from 'primereact/toast'
import useAlert from '../../customhooks/useAlert'
import useTemplateTablaSolicitudes from '../../customhooks/useTemplateTablaSolicitudes'
import RegistroSolicitud from './RegistroSolicitud'
import {
  NOMBRE_CAMPO_REQUCODI, NOMBRE_CAMPO_FECHFECO, NOMBRE_CAMPO_FECHSOLI, NOMBRE_CAMPO_ESTADESC, NOMBRE_CAMPO_REQUDETA, CONSULTA_DESDE_ESTADISTICA,
  FORMATO_FECHA_ESTANDAR, LABEL_DETALLE, LABEL_NUMERO_TICKET, LABEL_FECHA_SOLICITUD, LABEL_FECHA_COMPROMISO, LABEL_ESTADO
} from '../../utilites/constants'
import TablasSolicitudesHeader from './TablasSolicitudesHeader'
import useQuerySolicitudes from './useQuerySolicitudes'
import { modelFilterTablaSolicitudes } from '../../utilites/modelosFiltrosTablas'
import { getStrToDateFormatEstandar } from '../../utilites/dates'

const TablaSolicitudes = ({ tipoQuery, estadosIn, setIsHideModalPage }) => {
  const { dataInitSession, dataDropdown } = useSessionContext()
  const objDataDropdown = { ...dataDropdown }
  const [listEstados, setListEstados] = useState()
  const [mostrarDialogDetalle, setMostrarDialogDetalle] = useState(false)
  const [mostrarDialogCierre, setMostrarDialogCierre] = useState(false)
  const [mostrarDialogDevolucion, setMostrarDialogDevolucion] = useState(false)
  const [mostrarDialogRegistroSolicitud, setMostrarDialogRegistroSolicitud] = useState(false)
  const [numeroSolicitud, setNumeroSolicitud] = useState(false)
  const [listSolicitudes, setListSolicitudes] = useState([{}])
  const [dataDetalleSolicitud, setDataDetalleSolicitud] = useState()
  const [showLoading, setShowLoading] = useState(false)
  const { ejecutaConsultaFiltros, consultaFiltrosFechaInit, consultaFiltrosFechaFina, setEjecutaConsultaFiltros, setCrudDesdeFiltrosTabla } = useController()
  const tipoCliente = dataInitSession ? dataInitSession.tipoCliente : ''
  const [arrayColumnasExcel, setArrayColumnasExcel] = useState([])
  const [isDescargaReporte, setIsDescargaReporte] = useState(false)
  const [anexos, setAnexos] = useState()
  const [filtersGlobalTabla, setFiltersGlobalTabla] = useState()
  const [listSolicitudesParaExcel, setListSolicitudesParaExcel] = useState([{}])

  const cargarListEstadosFilter = () => setListEstados(objDataDropdown.estados)

  const cargaColumnasExcel = async () => {
    const data = await getData(
      dataInitSession.token,
      `${process.env.NEXT_PUBLIC_ENDPOINT_GET_COLUMNAS_EXCEL_REQUERIM}${dataInitSession.cliente}`)
    setArrayColumnasExcel(data)
  }

  const handleMostrarDialogDetalle = (e, dataDetalle) => {
    document.body.style.overflow = 'hidden'
    setDataDetalleSolicitud(dataDetalle)
    setMostrarDialogDetalle(true)
  }

  const handleEsconderDialogDetalle = () => {
    document.body.style.overflow = 'visible'
    setMostrarDialogDetalle(false)
  }

  const handleMostrarDialogCierre = (e) => {
    if (e?.target?.id) {
      setNumeroSolicitud(e.target.id.replace('cier-', ''))
      setMostrarDialogCierre(true)
    }
  }

  const handleMostrarDialogDevolucion = (e) => {
    if (e?.target?.id) {
      setNumeroSolicitud(e.target.id.replace('devo-', ''))
      setMostrarDialogDevolucion(true)
    }
  }

  const handleMostrarDialogEditarSolicitud = (e, dataDetalle) => {
    if (e?.target?.id) {
      const { listAnexos } = dataDetalle
      setAnexos(listAnexos)
      setNumeroSolicitud(e.target.id.replace('edit-', ''))
      setMostrarDialogRegistroSolicitud(true)
    }
  }

  const handleEsconderDialogEditarSolicitud = () => {
    document.body.style.overflow = 'visible'
    setMostrarDialogRegistroSolicitud(false)
  }

  const bodyTemplateRequdeta = (rowData, column) => {
    const maxLength = 380 // Máxima longitud permitida
    const value = rowData[column.field]
    if (value) return value.length > maxLength ? `${value.substring(0, maxLength)}...` : value
  }

  const handlerFiltroEstados = (options) => {
    return (
      <Dropdown
        id='estados'
        name='estados'
        className='input'
        value={options?.value}
        options={listEstados}
        onChange={(e) => options.filterApplyCallback(e?.value?.estadesc)}
        placeholder='Seleccione un estado'
        optionLabel='estadesc'
        filter
        showClear
      />
    )
  }

  const header = () => (
    <TablasSolicitudesHeader
      filtersGlobalTabla={filtersGlobalTabla}
      setFiltersGlobalTabla={setFiltersGlobalTabla}
      modelFilter={modelFilterTablaSolicitudes}
      columnas={arrayColumnasExcel}
      listSolicitudesParaExcel={listSolicitudesParaExcel}
    />
  )

  const cargaListSolicitudesFechas = (list, fechaInit, fechaFina) => {
    setShowLoading(true)
    const dateFechInit = getStrToDateFormatEstandar(fechaInit, FORMATO_FECHA_ESTANDAR).getTime()
    const dateFechFina = getStrToDateFormatEstandar(fechaFina, FORMATO_FECHA_ESTANDAR).getTime()
    if (list && list.length > 0 && dateFechInit && dateFechFina) {
      const listaFiltrada = list.filter(solicitud => {
        const dateFechaSoli = getStrToDateFormatEstandar(solicitud?.fechsoli, FORMATO_FECHA_ESTANDAR).getTime()
        if (dateFechaSoli >= dateFechInit && dateFechaSoli <= dateFechFina) {
          return solicitud
        }
      })
      setListSolicitudes(listaFiltrada)
      setListSolicitudesParaExcel(listaFiltrada)
    }
    setShowLoading(false)
  }

  const filtarSolicitudesPorEstadosDesdeEstadisticas = (list) => {
    if (list && list.length > 0) {
      const listaFiltrada = list.filter(solicitud => estadosIn === solicitud.estacodi)
      setListSolicitudes(listaFiltrada)
      setListSolicitudesParaExcel(listaFiltrada)
      setIsHideModalPage(true)
      setShowLoading(false)
    }
  }

  const cargaListSolicitudes = (data) => {
    setListSolicitudes(data)
    setListSolicitudesParaExcel(data)
    setIsHideModalPage(true)
    setShowLoading(false)
    setEjecutaConsultaFiltros(false)
  }

  // templates de la tabla de solicitudes
  const {
    actionTemplateDetail,
    actionTemplateClose,
    actionTemplateReturn,
    actionEditSolicitud
  } = useTemplateTablaSolicitudes(handleMostrarDialogDetalle, handleMostrarDialogCierre, handleMostrarDialogDevolucion,
    handleMostrarDialogEditarSolicitud, tipoCliente, '', '', setIsDescargaReporte, modelFilterTablaSolicitudes)

  // manejo de alertas
  const {
    toast,
    alertSuccess,
    alertError
  } = useAlert()

  const {
    solicitudesQuery
  } = useQuerySolicitudes(tipoQuery, estadosIn)

  // efecto para la descarga del reporte
  useEffect(() => {
    isDescargaReporte ? setShowLoading(true) : setShowLoading(false)
  }, [isDescargaReporte])

  useEffect(() => {
    // consulta desde la cabecera de la pagina que tiene los campos por rango de fechas
    if (ejecutaConsultaFiltros && solicitudesQuery.data) {
      (consultaFiltrosFechaInit && consultaFiltrosFechaFina)
        ? cargaListSolicitudesFechas(solicitudesQuery.data, consultaFiltrosFechaInit, consultaFiltrosFechaFina)
        : cargaListSolicitudes(solicitudesQuery.data)
    }
  }, [ejecutaConsultaFiltros])

  useEffect(() => {
    if (tipoQuery === CONSULTA_DESDE_ESTADISTICA && solicitudesQuery.data) {
      filtarSolicitudesPorEstadosDesdeEstadisticas(solicitudesQuery.data)
      return
    }

    // carga normal de las lista de solicitudes cargar todas las solicitudes del cliente
    if (solicitudesQuery.isSuccess && solicitudesQuery.data && !ejecutaConsultaFiltros) {
      cargaListSolicitudes(solicitudesQuery.data)
    }
  }, [solicitudesQuery.isSuccess])

  useEffect(() => {
    cargarListEstadosFilter()
    cargaColumnasExcel()
    return () => {
      setIsDescargaReporte(false)
      setListSolicitudes([{}])
      setEjecutaConsultaFiltros(false)
      setListSolicitudesParaExcel([{}])
    }
  }, [])

  return (
    <>
      {listSolicitudes &&
        <>
          <Toast ref={toast} position='center' />

          <DataTable
            value={listSolicitudes}
            filterDisplay='row'
            dataKey='requcodi'
            paginator
            rows={10}
            header={header}
            filters={filtersGlobalTabla}
            globalFilterFields={['requcodi', 'fechsoli', 'fechfeco', 'estadesc', 'requdeta']}
            className='datatable-solicitudes p-mt-3'
            breakpoint='960px'
            paginatorTemplate='CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown'
            currentPageReportTemplate='Mostrando del {first} al {last} de {totalRecords}'
            rowsPerPageOptions={[10, 20, 30, 40, 50]}
            emptyMessage='No se encontraron resultados'
            onValueChange={(e) => setListSolicitudesParaExcel(e || listSolicitudes)}
          >

            <Column
              columnKey={NOMBRE_CAMPO_REQUCODI}
              field={NOMBRE_CAMPO_REQUCODI}
              header={LABEL_NUMERO_TICKET}
              headerClassName='label p-datatable-col1'
              sortable filter
              filterPlaceholder='No. solicitud'
              showFilterMenu={false}
            />
            <Column
              field={NOMBRE_CAMPO_FECHSOLI}
              header={LABEL_FECHA_SOLICITUD}
              headerClassName='label p-datatable-col3'
              sortable filter
              filterPlaceholder='dd-MM-yyyy'
              showFilterMenu={false}
            />
            <Column
              field={NOMBRE_CAMPO_FECHFECO}
              header={LABEL_FECHA_COMPROMISO}
              headerClassName='label p-datatable-col3'
              sortable filter
              filterPlaceholder='dd-MM-yyyy'
              showFilterMenu={false}
            />
            <Column
              field={NOMBRE_CAMPO_ESTADESC}
              header={LABEL_ESTADO}
              headerClassName='label p-datatable-filter-ta2'
              sortable filter
              filterPlaceholder='Filtrar estado'
              showFilterMenu={false}
              filterElement={handlerFiltroEstados}
            />
            <Column
              field={NOMBRE_CAMPO_REQUDETA}
              header={LABEL_DETALLE}
              headerClassName='label p-datatable-col5'
              sortable filter
              showFilterMenu={false}
              body={bodyTemplateRequdeta}
            />
            <Column
              body={actionEditSolicitud}
              headerClassName='p-datatable-col8'
            />
            <Column
              body={actionTemplateClose}
              headerClassName='p-datatable-col7'
            />
            <Column
              body={actionTemplateReturn}
              headerClassName='p-datatable-col8'
            />
            <Column
              body={actionEditSolicitud}
              headerClassName='p-datatable-col8'
            />
            <Column
              body={actionTemplateDetail}
              headerClassName='p-datatable-col6'
            />

          </DataTable>

        </>}

      <Dialog
        header='Cierre de solicitud'
        visible={mostrarDialogCierre}
        position='center'
        onHide={() => setMostrarDialogCierre(false)}
        draggable={false}
        resizable={false}
        baseZIndex={1000}
        style={{ width: '50vw' }}
        breakpoints={{ '960px': '100vw' }}
      >
        <CierreSolicitud
          numeroSolicitud={numeroSolicitud}
          hideDialogCierre={setMostrarDialogCierre}
          reconsultarTabla={null}
          alertSuccess={alertSuccess}
          alertError={alertError}
          listSolicitudes={listSolicitudes}
          setListSolicitudes={setListSolicitudes}
        />
      </Dialog>

      <Dialog
        header='Devolución de solicitud'
        visible={mostrarDialogDevolucion}
        position='center'
        onHide={() => setMostrarDialogDevolucion(false)}
        draggable={false}
        resizable={false}
        baseZIndex={1000}
        style={{ width: '50vw' }}
        breakpoints={{ '960px': '100vw' }}
      >
        <DevolucionSolicitud
          numeroSolicitud={numeroSolicitud}
          hideDialogDevolucion={setMostrarDialogDevolucion}
          reconsultarTabla={null}
          alertSuccess={alertSuccess}
          alertError={alertError}
          listSolicitudes={listSolicitudes}
          setListSolicitudes={setListSolicitudes}
        />
      </Dialog>

      <Dialog
        header='Detalle de solicitud'
        visible={mostrarDialogDetalle}
        position='right'
        style={{ width: '100vw', minHeight: '100vh' }}
        contentStyle={{ minHeight: '100vh' }}
        onHide={() => handleEsconderDialogDetalle()}
        draggable={false}
        resizable={false}
        baseZIndex={1000}
      >
        <DetalleSolicitud
          dataDetalleSolicitud={dataDetalleSolicitud}
          mostrarDialogDetalle={mostrarDialogDetalle}
        />
      </Dialog>

      <Dialog
        header='Editar solicitud'
        visible={mostrarDialogRegistroSolicitud}
        position='center'
        style={{ width: '80vw' }}
        onHide={() => handleEsconderDialogEditarSolicitud(false)}
        draggable={false}
        resizable={false}
        baseZIndex={100}
      >
        <RegistroSolicitud
          crudDesdeFiltrosTabla={setCrudDesdeFiltrosTabla}
          editar
          numeroSolicitud={numeroSolicitud}
          listAnexos={anexos}
        />
      </Dialog>

      <LoadingEvent show={showLoading} />
    </>
  )
}

export default TablaSolicitudes
