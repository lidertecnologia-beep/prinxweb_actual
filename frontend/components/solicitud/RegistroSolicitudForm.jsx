import { InputText } from 'primereact/inputtext'
import { Dropdown } from 'primereact/dropdown'
import { Button } from 'primereact/button'
import { InputTextarea } from 'primereact/inputtextarea'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faTimes } from '@fortawesome/free-solid-svg-icons'
import Link from 'next/link'
import LoadingEvent from '../../utilites/modal/LoadingEvent'
import { Toast } from 'primereact/toast'

import {
  LABEL_BTN_CREANDO, LABEL_BTN_ELIMINAR, CLASS_CSS_ICONO_PI_TIMES, LABEL_TIPO_SOLICITUD, LABEL_PRIORIDAD,
  LABEL_PRODUCTO, LABEL_OPCION, LABEL_PRIORIDAD_ATENCION, LABEL_NUMERO_TICKET, LABEL_DETALLE
} from '../../utilites/constants'
import FileUpload from '@components/archivos/FileUpload'

const RegistroSolicitudForm = (props) => {
  const { formValidacion, cargarOpcionesProductoSelecionado } = props
  const { errors, values } = formValidacion
  return (

    <form id='formRegistroSolicitud' noValidate onSubmit={props?.handleSubmit} ref={props?.refForm}>

      <LoadingEvent show={props?.showModal} />

      <Toast ref={props?.toast} position='center' />

      <div className='p-fluid p-grid p-d-flex'>
        <div className='p-field p-md-2 p-md-offset-2 p-sm-10 p-col-10'>
          <label className='label' htmlFor='numeroSolicitud'>{LABEL_NUMERO_TICKET}</label>
          <InputText
            defaultValue=''
            ref={props?.refElNumeroSolicitud}
            id='numeroSolicitud'
            className='input p-text-center numero-solicitud'
            disabled
          />
        </div>
        <div className='p-field p-md-2 p-sm-1 p-col-1 p-grid p-d-flex p-jc-start p-ai-end'>
          <Button className='bottom-copytext' icon='pi pi-copy' onClick={props?.handleCopyText} />
        </div>
      </div>

      <div className='p-fluid p-grid p-d-flex p-jc-center'>

        <div className='p-field p-md-2 p-sm-12 p-col-12'>
          <label className='label' htmlFor='tipoSolicitud'>{LABEL_TIPO_SOLICITUD}</label>
          <Dropdown
            id='tipoSolicitud'
            name='tipoSolicitud'
            className={` input ${(errors?.tipoSolicitud) ? 'input-invalid' : ''} `}
            options={props?.dataTipoSolicitud}
            value={values?.tipoSolicitud}
            onChange={(e) => formValidacion.setFieldValue('tipoSolicitud', e.value)}
            onBlur={() => formValidacion.handleBlur}
            optionLabel='descripcion'
            optionValue='codigo'
            filter
            filterBy='descripcion'
            placeholder='Seleccione un tipo de solicitud'
            emptyFilterMessage='No se encontraron resultados'
            required
            showClear={false}
          />
          {errors?.tipoSolicitud &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.tipoSolicitud}
            </p>
          }
        </div>

        <div className='p-field p-md-2 p-sm-12 p-col-12'>
          <label className='label' htmlFor='prioridad'>{LABEL_PRIORIDAD}</label>
          <Dropdown
            id='prioridad'
            name='prioridad'
            className={` input ${(errors?.prioridad) ? 'input-invalid' : ''} `}
            options={props?.dataPrioridad}
            value={values?.prioridad}
            onChange={(e) => formValidacion.setFieldValue('prioridad', e.value)}
            onBlur={() => formValidacion.handleBlur}
            optionLabel='descripcion'
            optionValue='codigo'
            filter
            filterBy='descripcion'
            placeholder='Seleccione un tipo de prioridad'
            emptyFilterMessage='No se encontraron resultados'
            required
            showClear={false}
          />
          {errors?.prioridad &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.prioridad}
            </p>
          }
        </div>

        <div className='p-field p-md-2 p-sm-12 p-col-12'>
          <label className='label' htmlFor='producto'>{LABEL_PRODUCTO}</label>
          <Dropdown
            id='producto'
            name='producto'
            className={` input ${(errors?.producto) ? 'input-invalid' : ''} `}
            options={props?.dataProducto}
            value={values?.producto}
            onChange={cargarOpcionesProductoSelecionado}
            onBlur={() => formValidacion.handleBlur}
            optionLabel='descripcion'
            optionValue='codigo'
            filter
            filterBy='descripcion'
            placeholder='Seleccione un tipo de producto'
            emptyFilterMessage='No se encontraron resultados'
            required
            showClear={false}
          />
          {errors?.producto &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.producto}
            </p>
          }
        </div>

        <div className='p-field p-md-2 p-sm-12 p-col-12'>
          <label className='label' htmlFor='opcion'>{LABEL_OPCION}</label>
          <Dropdown
            id='opcion'
            name='opcion'
            className={` input ${(errors?.opcion) ? 'input-invalid' : ''} `}
            options={props?.dataOpcion}
            value={values?.opcion}
            onChange={(e) => formValidacion.setFieldValue('opcion', e.value)}
            onBlur={() => formValidacion.handleBlur}
            optionLabel='descripcion'
            optionValue='codigo'
            filter
            filterBy='codigo'
            placeholder='Seleccione un tipo de opción'
            emptyFilterMessage='No se encontraron resultados'
            required
            showClear={false}
          />
          {errors?.opcion &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.opcion}
            </p>
          }

        </div>

      </div>

      <div className='p-fluid p-grid p-d-flex p-jc-center'>
        <div className='p-field p-sm-12 p-md-8 p-col-12'>
          <label className='label' htmlFor='validaAtencion'>{LABEL_PRIORIDAD_ATENCION}</label>
          <Dropdown
            id='validaAtencion'
            name='validaAtencion'
            className={` input ${(errors?.validaAtencion) ? 'input-invalid' : ''} `}
            options={props?.dataValidaAtencion}
            value={values?.validaAtencion}
            onChange={(e) => formValidacion.setFieldValue('validaAtencion', e?.value)}
            onBlur={() => formValidacion.handleBlur}
            optionLabel='itchdesc'
            optionValue='itemcheqPK'
            filter
            filterBy='itchdesc'
            placeholder='Seleccione prioridad de atención'
            emptyFilterMessage='No se encontraron resultados'
            required
            showClear={false}
          />
          {errors?.validaAtencion &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.validaAtencion}
            </p>
          }
        </div>
      </div>
      <div className='p-fluid p-grid p-d-flex p-jc-center'>
        <div className='p-field p-md-8 p-sm-12 p-col-12'>
          <label className='label' htmlFor='detalle'>{LABEL_DETALLE}</label>
          <InputTextarea
            id='detalle'
            name='detalle'
            className={` input ${(errors?.detalle) ? 'input-invalid' : ''} `}
            value={values?.detalle}
            onChange={formValidacion.handleChange}
            onBlur={formValidacion.handleBlur}
            rows={8}
            cols={30}
            autoResize={false}
            required
          />
          {errors?.detalle &&
            <p className='p-invalid'>
              <FontAwesomeIcon icon={faTimes} size='2x' className='color-icons p-mr-2' />
              {errors?.detalle}
            </p>
          }
        </div>
      </div>

      <div className='p-fluid p-grid p-d-flex p-jc-center '>
        <div className='p-field p-col-12 p-sm-12 p-md-12 p-lg-8'>
          <FileUpload
            files={props?.fileIn}
            maxFiles={process.env.NEXT_PUBLIC_MAXIMO_ARCHIVOS_CARGAR}
            enviarArchivo={props?.isInsertAnexos}
            id={props?.numeroSolicitud}
            setFiles={props?.setFiles} />
        </div>
      </div>

      <div className='p-fluid p-grid p-mb-4 '>
        <div className='p-field p-grid p-col-8 p-sm-8 p-md-offset-2 p-md-4 p-lg-offset-2 p-lg-2 p-ai-center'>
        </div>

        <div className='p-field p-grid p-flex p-jc-end p-ai-center p-col-4 p-sm-4 p-md-1  p-lg-1 p-lg-offset-2'>
          <Link
            href='#'
            className='links'
            onClick={props?.handleClearForm}
            title='Limpiar formulario'>
            <FontAwesomeIcon
              icon={faTrash}
              size='2x'
              className='color-icons p-mr-4 p-ml-4'
            />
          </Link>
        </div>

        <div className='p-field p-grid p-flex p-jc-center p-col-12 p-sm-4 p-md-2 p-lg-2'>
          <Button
            type='Eliminar'
            icon={CLASS_CSS_ICONO_PI_TIMES}
            label={LABEL_BTN_ELIMINAR}
            className='p-button-raised p-button-rounded ripple'
            disabled={props?.isDisabledBotonEliminar}
            onClick={props?.borrarSolicitu}
          />
        </div>

        <div className='p-field p-grid p-flex p-jc-start p-col-12 p-sm-4 p-md-2 p-lg-2'>
          <Button
            type='submit'
            icon={props?.spinner ? '' : props?.iconBtnCrearSolicitud}
            label={props?.spinner ? LABEL_BTN_CREANDO : props?.labelBtnCrearSolicitud}
            disabled={props?.isDisableBtnSubmit}
            className={`p-button-raised p-button-rounded ripple ${props?.spinner ? 'show-spinner-btn-disabled' : ''}`}
          />
        </div>

      </div>

    </form>
  )
}

export default RegistroSolicitudForm
