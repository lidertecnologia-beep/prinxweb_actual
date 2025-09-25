import { NOMBRE_CAMPO_REQUFECH, NOMBRE_CAMPO_REQUFECO, NOMBRE_CAMPO_ESTADESC } from '../utilites/constants'
import { Calendar } from 'primereact/calendar'
import { InputText } from 'primereact/inputtext'
import { Dropdown } from 'primereact/dropdown'
import { useState } from 'react'
import getDate from '../utilites/dates'
import { campoSoloNumero } from '../utilites/formValidate/validateInputs'

const useEventFilterTablaSolicitdes = (
  consultaDesdeEstadisticas, cargaListSolicitudes, setShowLoading, firstPage, setFirstPage,
  sizeItems, setSizeItems, estadosIn, listEstados, isFiltered, setIsFiltered, objOptionsFilter, setObjOptionsFilter) => {
  const [filterRequcodi, setFilterRequcodi] = useState('')
  const [filterRequfech, setFilterRequfech] = useState('')
  const [filterRequfeco, setFilterRequfeco] = useState('')
  const [filterRequesta, setFilterRequesta] = useState('')

  const objSetFilter = {
    requcodi: function (value) { setFilterRequcodi(value) },
    requfech: function (value) { setFilterRequfech(value) },
    requfeco: function (value) { setFilterRequfeco(value) },
    estadesc: function (value) { setFilterRequesta(value) }
  }

  const objAfterFiltered = {
    requcodi: function (value) { cargaListSolicitudes(value, '', '', '', firstPage, sizeItems) },
    requfech: function (value) { cargaListSolicitudes('', value, '', '', firstPage, sizeItems) },
    requfeco: function (value) { cargaListSolicitudes('', '', value, '', firstPage, sizeItems) },
    estadesc: function (value) { cargaListSolicitudes('', '', '', value, firstPage, sizeItems) }
  }

  let optionsFilterValueEstacodi = ''

  const filterEstadisticasChangeInputFilter = (e, options) => {
    if (e && options) {
      setShowLoading(true)
      e.preventDefault()
      setIsFiltered(true)

      const { field } = { ...options }

      if (field === NOMBRE_CAMPO_ESTADESC) {
        optionsFilterValueEstacodi = e.value.estacodi
        objSetFilter[field](e.value.estadesc)
        options.filterApplyCallback(e.value.estadesc)
        objAfterFiltered[field](optionsFilterValueEstacodi)
        setObjOptionsFilter({ field, value: optionsFilterValueEstacodi })
        return
      }

      if (field === NOMBRE_CAMPO_REQUFECH) {
        const fechaToFliter = getDate(e.value, 'YYYY-MM-DD')
        if (fechaToFliter) {
          objSetFilter[field](fechaToFliter)
          options.filterApplyCallback(fechaToFliter)
          setObjOptionsFilter({ field, value: optionsFilterValueEstacodi })
          consultaDesdeEstadisticas(estadosIn, '', fechaToFliter, '', firstPage, sizeItems)
        }
        return
      }

      if (field === NOMBRE_CAMPO_REQUFECO) {
        const fechaToFliter = getDate(e.value, 'YYYY-MM-DD')
        if (fechaToFliter) {
          objSetFilter[field](fechaToFliter)
          options.filterApplyCallback(fechaToFliter)
          setObjOptionsFilter({ field, value: fechaToFliter })
          consultaDesdeEstadisticas(estadosIn, '', '', fechaToFliter, firstPage, sizeItems)
        }
        return
      }

      objSetFilter[field](e.target.value)
      options.filterApplyCallback(e.target.value)
      objAfterFiltered[field](e.target.value)
      setObjOptionsFilter({ field, value: e.target.value })
    }
  }

  function handleChangeInputFilter(e, options) {
    if (e && e.target) {
      if (estadosIn) {
        filterEstadisticasChangeInputFilter(e, options)
        return
      }

      setShowLoading(true)
      e.preventDefault()
      setIsFiltered(true)

      const { field } = { ...options }
      if (field === NOMBRE_CAMPO_ESTADESC) {
        optionsFilterValueEstacodi = e.value.estacodi
        objSetFilter[field](e.value.estadesc)
        options.filterApplyCallback(e.value.estadesc)
        objAfterFiltered[field](optionsFilterValueEstacodi)
        setObjOptionsFilter({ field, value: optionsFilterValueEstacodi })
        return
      }

      if (field === NOMBRE_CAMPO_REQUFECH || field === NOMBRE_CAMPO_REQUFECO) {
        const fechaToFliter = getDate(e.value, 'YYYY-MM-DD')
        if (fechaToFliter) {
          objSetFilter[field](fechaToFliter)
          options.filterApplyCallback(fechaToFliter)
          objAfterFiltered[field](fechaToFliter)
          setObjOptionsFilter({ field, value: fechaToFliter })
          return
        }
      }

      objSetFilter[field](e.target.value)
      options.filterApplyCallback(e.target.value)
      objAfterFiltered[field](e.target.value)
      setObjOptionsFilter({ field, value: e.target.value })
    }
  }

  const rowFilterRequcodiTemplate = (options) => <InputText value={filterRequcodi} onChange={(e) => handleChangeInputFilter(e, options)} placeholder='Filtrar solicitud' onKeyDown={(e) => campoSoloNumero(e)} />
  const rowFilterRequfechTemplate = (options) => <Calendar value={filterRequfech} onChange={(e) => handleChangeInputFilter(e, options)} dateFormat='dd-M-yy' placeholder='dd-MMM-yyyy' mask='99/99/9999' onKeyDown={(e) => campoSoloNumero(e)} />
  const rowFilterRequfecoTemplate = (options) => <Calendar value={filterRequfeco} onChange={(e) => handleChangeInputFilter(e, options)} dateFormat='dd-M-yy' placeholder='dd-MMM-yyyy' mask='99/99/9999' onKeyDown={(e) => campoSoloNumero(e)} />
  const rowFilterRequestaTemplate = (options) => <Dropdown className='input' value={filterRequesta} options={listEstados} optionLabel={NOMBRE_CAMPO_ESTADESC} onChange={(e) => handleChangeInputFilter(e, options)} placeholder='Filtrar estado' />

  const handlerPaginator = (e) => {
    setShowLoading(true)
    setFirstPage(e.first)
    setSizeItems(e.rows)

    if (estadosIn) {
      filterEstadisticasPaginator(e)
      return true
    }

    if (filterRequfech && filterRequfech !== '') {
      cargaListSolicitudes('', filterRequfech, '', '', firstPage, sizeItems)
      return true
    }

    if (filterRequfeco && filterRequfeco !== '') {
      cargaListSolicitudes('', '', filterRequfeco, '', firstPage, sizeItems)
      return true
    }

    if (filterRequesta && filterRequesta !== '') {
      const estado = listEstados.filter(e => e.estadesc === filterRequesta)
      if (estado) {
        cargaListSolicitudes('', '', '', estado[0].estacodi, firstPage, sizeItems)
        return true
      }
    }

    cargaListSolicitudes('', '', '', '', e.page, e.rows)
  }

  const filterEstadisticasPaginator = (e) => {
    setShowLoading(true)
    setFirstPage(e.first)
    setSizeItems(e.rows)

    if (estadosIn) {
      if (filterRequfech && filterRequfech !== '') {
        consultaDesdeEstadisticas(estadosIn, '', filterRequfech, '', firstPage, sizeItems)
        return
      }

      if (filterRequfeco && filterRequfeco !== '') {
        consultaDesdeEstadisticas(estadosIn, '', '', filterRequfeco, firstPage, sizeItems)
        return
      }

      if (filterRequesta && filterRequesta !== '') {
        const estado = listEstados.filter(e => e.estadesc === filterRequesta)
        if (estado) {
          cargaListSolicitudes('', '', '', estado[0].estacodi, firstPage, sizeItems)
          return
        }
      }

      consultaDesdeEstadisticas(estadosIn, '', '', '', e.page, e.rows)
    }
  }

  const onFilterClear = (campo) => {
    setShowLoading(true)
    setIsFiltered(false)
    setObjOptionsFilter({ field: '', value: '' })
    objSetFilter[campo]((campo === NOMBRE_CAMPO_REQUFECH || campo === NOMBRE_CAMPO_REQUFECO) ? null : '')
    if (estadosIn) {
      consultaDesdeEstadisticas(estadosIn, '', '', '', firstPage, sizeItems)
      return
    }
    cargaListSolicitudes('', '', '', '', firstPage, sizeItems)
  }

  return {
    handleChangeInputFilter,
    filterEstadisticasChangeInputFilter,
    rowFilterRequcodiTemplate,
    rowFilterRequfechTemplate,
    rowFilterRequfecoTemplate,
    rowFilterRequestaTemplate,
    handlerPaginator,
    filterEstadisticasPaginator,
    onFilterClear
  }
}

export default useEventFilterTablaSolicitdes
