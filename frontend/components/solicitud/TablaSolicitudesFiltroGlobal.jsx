import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { InputText } from 'primereact/inputtext'
import { useState, useEffect } from 'react'

const TablaSolicitudesFiltroGlobal = ({ modelFilter, filtersGlobalTabla, setFiltersGlobalTabla }) => {
  const [globalFilterValue, setGlobalFilterValue] = useState('')

  const initFilters = () => {
    setFiltersGlobalTabla(modelFilter)
    setGlobalFilterValue('')
  }

  const onGlobalFilterChange = (e) => {
    const value = e.target.value
    const _filters = { ...filtersGlobalTabla }
    _filters.global.value = value
    setFiltersGlobalTabla(_filters)
    setGlobalFilterValue(value)
  }

  // efectos
  useEffect(() => {
    initFilters()
    return () => {
      setFiltersGlobalTabla(null)
      setGlobalFilterValue(null)
    }
  }, [])

  return (
    <>
      <span className='p-container-input-filter'>
        <FontAwesomeIcon
          icon={faSearch}
          size='2x'
          className='color-icons input-filter-global-icon-right'
          width='30'
        />
        <InputText
          className='input input-filter-global'
          value={globalFilterValue}
          onChange={onGlobalFilterChange}
          placeholder='Buscar por palabra clave'
        />
      </span>
    </>
  )
}

export default TablaSolicitudesFiltroGlobal
