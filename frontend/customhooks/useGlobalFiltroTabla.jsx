import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { InputText } from 'primereact/inputtext'
import { useState, useEffect } from 'react'

const useGlobalFiltroTabla = (modelFilter) => {
  const [globalFilterValue, setGlobalFilterValue] = useState('')
  const [filtersGlobalTabla, setFiltersGlobalTabla] = useState(null)

  const initFilters = () => {
    setFiltersGlobalTabla(modelFilter)
    setGlobalFilterValue('')
  }

  const onGlobalFilterChange1 = (e) => {
    const value = e.target.value
    const _filters = { ...filtersGlobalTabla }
    _filters.global.value = value
    setFiltersGlobalTabla(_filters)
    setGlobalFilterValue(value)
  }

  const $elFiltroGlobalTabla = (
    <span className='p-container-input-filter'>
      <FontAwesomeIcon
        icon={faSearch}
        size='2x'
        className='color-icons input-filter-global-icon-right'
      />
      <InputText
        className='input input-filter-global'
        value={globalFilterValue}
        onChange={onGlobalFilterChange1}
        placeholder='Buscar por palabra clave'
      />
    </span>
  )

  // efectos
  useEffect(() => {
    initFilters()
    return () => {
      setFiltersGlobalTabla(null)
      setGlobalFilterValue(null)
    }
  }, [])

  return {
    filtersGlobalTabla,
    $elFiltroGlobalTabla
  }
}

export default useGlobalFiltroTabla
