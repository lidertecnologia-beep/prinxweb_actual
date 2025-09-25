import { Card } from 'primereact/card'
import Link from 'next/link'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileExcel, faFilePdf } from '@fortawesome/free-solid-svg-icons'

const DescargaArchivos = () => {
  const { addClassShowDescargaArchivos } = useController()

  return (
    <Card
      id='profile'
      header='Descargas'
      className={addClassShowDescargaArchivos ? 'p-shadow-6 descarga-archivo toogle' : 'p-shadow-6 descarga-archivo'}
    >
      <ul>
        <li className='p-pb-3'>
          <Link href='#' className='links'>

            <FontAwesomeIcon icon={faFileExcel} size='1x' className='color-icons p-mr-2' />
            <span>Excel</span>

          </Link>
        </li>
        <li className='p-pb-3'>
          <Link href='#' className='links'>

            <FontAwesomeIcon icon={faFilePdf} size='1x' className='color-icons p-mr-2' />
            <span>PDF</span>

          </Link>
        </li>
      </ul>
    </Card>
  )
}

export default DescargaArchivos
