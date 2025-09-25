import { FilterMatchMode } from 'primereact/api'

export const modelFilterTablaSolicitudes = {
  global: { value: null, matchMode: FilterMatchMode.CONTAINS },
  requcodi: { value: null, matchMode: FilterMatchMode.CONTAINS },
  fechsoli: { value: null, matchMode: FilterMatchMode.CONTAINS },
  fechfeco: { value: null, matchMode: FilterMatchMode.CONTAINS },
  estadesc: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  requdeta: { value: null, matchMode: FilterMatchMode.CONTAINS }
}
