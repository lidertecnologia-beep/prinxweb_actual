import { useState, useEffect, useRef } from "react";
import getData, {
  insertPost,
  updatePut,
  deleteAnexsoli,
  requestDelete2,
} from "../../pages/api/api";
import formRequerim from "../../pages/api/formsObject";
import moment from "moment";
import useAlert from "../../customhooks/useAlert";
import { useValidateFormRegistroSolicitud } from "../../customhooks/useValidateFormRegistroSolicitud";

import {
  LABEL_BTN_CREAR,
  LABEL_BTN_ACTUALIZAR,
  MENSAJE_REGISTRO_CREADO,
  MENSAJE_ERROR_CREAR_REGISTRO,
  MENSAJE_REGISTRO_ACTUALIZADO,
  MENSAJE_ERROR_ACTUALIZAR_REGISTRO,
  MENSAJE_REGISTRO_ELIMINADO,
  CLASS_CSS_ICONO_PI_PENCIL,
  MEDIO_COMUNICACION_HERRAMIENTA_INTERNET,
} from "../../utilites/constants";
import useAnexos from "../../customhooks/useAnexos";
import RegistroSolicitudForm from "./RegistroSolicitudForm";
import storeGobal from "../../store/storeGobal";
import useCargaListaValoresRegistroSolicitud from "@customhooks/useCargaListaValoresRegistroSolicitud";

const RegistroSolicitud = ({
  crudDesdeFiltrosTabla = null,
  editar = false,
  numeroSolicitud = null,
  listAnexos,
}) => {
  // variables y estados iniciales
  const [showModal, setShowModal] = useState(false);
  const [isInsertAnexos, setIsInsertAnexo] = useState(false);
  const [spinner] = useState(false);
  const [labelBtnCrearSolicitud, setLabelBtnCrearSolicitud] = useState(LABEL_BTN_CREAR);
  const [iconBtnCrearSolicitud, setIconBtnCrearSolicitud] = useState("pi pi-save");
  const [isDisabledBotonEliminar, setIsDisabledBotonEliminar] = useState(true);
  const [isDisableBtnSubmit, setIsDisableBtnSubmit] = useState(true);
  const [files, setFiles] = useState()
  const sesion = storeGobal((state) => state.sesion);
  const refForm = useRef(null);
  const refElNumeroSolicitud = useRef(null);

  const getNumeroSolicitud = () => refElNumeroSolicitud?.current?.value ?? "";

  const cargarOpcionesProductoSelecionado = (e) => {
    setProductoSelecionado(e?.value);
    formValidacion?.setFieldValue("producto", e?.value);
  };

  const cargaBtnParaActualizar = () => {
    setLabelBtnCrearSolicitud(LABEL_BTN_ACTUALIZAR);
    setIconBtnCrearSolicitud(CLASS_CSS_ICONO_PI_PENCIL); // const [dataOpcion, setDataOpcion] = useState(null)
    setIsDisabledBotonEliminar(false);
  };

  const handleCopyText = (e) => {
    e.preventDefault();
    navigator.clipboard.writeText(refElNumeroSolicitud.current.value);
  };

  const handleClearForm = () => {
    refElNumeroSolicitud.current.value = null;
    formValidacion.resetForm();
    setFiles(null)
    setIconBtnCrearSolicitud("pi pi-save");
    setLabelBtnCrearSolicitud(LABEL_BTN_CREAR);
    setIsDisabledBotonEliminar(true);
    setIsDisableBtnSubmit(true);
  };

  const cargaFormRequerim = () => {
    const dataForm = formValidacion.values;
    const item = validaAtencion?.find(
      (item) =>
        item?.itemcheqPK?.itchcodi === dataForm?.validaAtencion?.itchcodi &&
        item?.itemcheqPK?.itchestr === dataForm?.validaAtencion?.itchestr
    );
    formRequerim.requtire = dataForm?.tipoSolicitud;
    formRequerim.requprio = dataForm?.prioridad;
    formRequerim.requprod = dataForm?.producto;
    formRequerim.requobpr = dataForm?.opcion;
    formRequerim.requdeta = dataForm?.detalle;
    formRequerim.requfech = moment().format("DD-MM-YYYY");
    formRequerim.requmeco = MEDIO_COMUNICACION_HERRAMIENTA_INTERNET;
    formRequerim.listItemCheq[0].itchcodi = dataForm?.validaAtencion?.itchcodi;
    formRequerim.listItemCheq[0].itchsecu = item?.itchsecu;
    formRequerim.listItemCheq[0].itchcheq = "S";
    formRequerim.listItemCheq[0].itchdesc = "";
  };

  const cargaSolicituEditar = (solicitud) => {
    if (!solicitud) return null;

    if (formValidacion && formValidacion?.values) {
      formValidacion.values.tipoSolicitud = solicitud?.solutire;
      formValidacion.values.prioridad = solicitud?.soluprio.toString();
      formValidacion.values.producto = solicitud?.soluprod;
      setProductoSelecionado(formValidacion?.values?.producto);
      formValidacion.values.opcion = solicitud?.soluobje;
      formValidacion.values.detalle = solicitud?.soluprob;
      const item = validaAtencion?.find(
        (item) => item?.itchsecu === parseInt(solicitud.soluprioridaAtencion)
      );
      formValidacion.values.validaAtencion.itchcodi =
        item?.itemcheqPK?.itchcodi;
      formValidacion.values.validaAtencion.itchestr =
        item?.itemcheqPK?.itchestr;
    }
  };

  const cargarSolicitud = async (numeroRequerim) => {
    if (!editar && (!numeroSolicitud || numeroSolicitud === 0)) return;

    setShowModal(true);

    try {

      const data = await getData(
        sesion.token,
        `${process.env.NEXT_PUBLIC_ENDPOINT_GET_SOLUCION_SPRING}${sesion.cliente}&solucodi=${numeroRequerim}`
      );

      if (data?.solucodi && data.solucodi > 0) {
        const [listFile] = await Promise.all([
          cargaListaAnexosToObjFile(listAnexos),
        ]);

        if (listFile) setFiles(listFile)  ;
        cargaSolicituEditar(data);
        refElNumeroSolicitud.current.value = data.solucodi;
        cargaBtnParaActualizar();
        setIsDisableBtnSubmit(false);
      }

    } catch (err) {
      console.error("cargarSolicitud", { err });
    }

    setShowModal(false);
  }


  const borrarSolicitu = async (e) => {
    e.preventDefault();

    setShowModal(true);

    const data = await requestDelete2(
      sesion.token,
      `${process.env.NEXT_PUBLIC_ENDPOINT_DELETE_REQUERIM_SRING}${sesion.cliente
      }&id=${refElNumeroSolicitud.current.value ?? ""}`
    );

    if (data === "true") {
      setShowModal(false);
      return false;
    }
    handleClearForm();
    setShowModal(false);
    alertSuccess(MENSAJE_REGISTRO_ELIMINADO);
    if (crudDesdeFiltrosTabla) crudDesdeFiltrosTabla(true);
  }


  const actualizarSolicitud = async () => {
    setShowModal(true);
    try {
      // se reinica el estado de la carga de anexo
      setIsInsertAnexo(false);

      formRequerim.requcodi = getNumeroSolicitud();
      if (!formRequerim.requcodi) return;

      cargaFormRequerim();

      const data = await updatePut(
        sesion.token,
        `${process.env.NEXT_PUBLIC_ENDPOINT_POST_SPRING}${sesion.cliente}`,
        formRequerim
      );

      if (!data || data.toLowerCase() !== "ok") {
        setShowModal(false);
        alertError(MENSAJE_ERROR_ACTUALIZAR_REGISTRO);
        return false;
      }

      await deleteAnexsoli(
        sesion.token,
        `${process.env.NEXT_PUBLIC_ENDPOINT_DELETE_ANEXSOLI_ALLID_SPRING
        }?cliente=${sesion.cliente}&id=${getNumeroSolicitud()}`
      );

      alertSuccess(MENSAJE_REGISTRO_ACTUALIZADO);
      setLabelBtnCrearSolicitud(LABEL_BTN_ACTUALIZAR);
      setIconBtnCrearSolicitud(CLASS_CSS_ICONO_PI_PENCIL);
      setIsDisabledBotonEliminar(false);
      setIsInsertAnexo(true);
      if (crudDesdeFiltrosTabla) crudDesdeFiltrosTabla(true);

    } catch (e) {
      setShowModal(false);
      setIsInsertAnexo(false);
    }
    setShowModal(false);
  }


  const crearSolicitud = async () => {
    try {

      setShowModal(true);

      //se reinica el estado de la carga de anexo
      setIsInsertAnexo(false);

      if (refElNumeroSolicitud?.current?.value) return;

      cargaFormRequerim();

      const data = await insertPost(
        sesion.token,
        `${process.env.NEXT_PUBLIC_ENDPOINT_POST_REQUERIM_SPRING}${sesion.cliente}`,
        formRequerim
      );

      if (!data?.requcodi) {
        setShowModal(false);
        alertError(data ?? MENSAJE_ERROR_CREAR_REGISTRO);
        return false;
      }

      refElNumeroSolicitud.current.value = data.requcodi;

      alertSuccess(MENSAJE_REGISTRO_CREADO);
      cargaBtnParaActualizar();
      setIsInsertAnexo(true);
      if (crudDesdeFiltrosTabla) crudDesdeFiltrosTabla(true);
      setShowModal(false);

    } catch (e) {
      console.error(e)
      setIsInsertAnexo(false);
    } finally {
      setShowModal(false);
    }
  };

  // dispara las funciones para actulizar o crear
  const handleSubmit = async (e) => {
    e.preventDefault();
    const errores = await formValidacion.validateForm();
    if (Object.keys(errores).length > 0) return;
    getNumeroSolicitud() ? actualizarSolicitud() : crearSolicitud();
  }


  // carga de hooks
  const { cargaListaAnexosToObjFile } = useAnexos();
  const { formValidacion } = useValidateFormRegistroSolicitud(setIsDisableBtnSubmit)
  const { toast, alertSuccess, alertError } = useAlert();
  const {
    tipoSolicitudes,
    prioridades,
    productos,
    validaAtencion,
    productoOpciones,
    setProductoSelecionado,
  } = useCargaListaValoresRegistroSolicitud(setShowModal);


  //carga de effectos
  useEffect(() => {
    if (tipoSolicitudes?.length > 0 && validaAtencion?.length > 0) {
      cargarSolicitud(numeroSolicitud);
    }
  }, [tipoSolicitudes, validaAtencion]);

  useEffect(() => {
    return () => {
      setShowModal(false);
      setIsInsertAnexo(false);
    };
  }, []);

  return (
    <>
      <RegistroSolicitudForm
        handleSubmit={handleSubmit}
        isDisableBtnSubmit={isDisableBtnSubmit}
        iconBtnCrearSolicitud={iconBtnCrearSolicitud}
        borrarSolicitu={borrarSolicitu}
        handleClearForm={handleClearForm}
        getNumeroSolicitud={getNumeroSolicitud}
        isInsertAnexos={isInsertAnexos}
        dataValidaAtencion={validaAtencion}
        dataOpcion={productoOpciones}
        dataProducto={productos}
        dataPrioridad={prioridades}
        dataTipoSolicitud={tipoSolicitudes}
        handleCopyText={handleCopyText}
        refElNumeroSolicitud={refElNumeroSolicitud}
        refForm={refForm}
        numeroSolicitud={refElNumeroSolicitud?.current?.value}
        toast={toast}
        labelBtnCrearSolicitud={labelBtnCrearSolicitud}
        spinner={spinner}
        showModal={showModal}
        crearSolicitud={crearSolicitud}
        fileIn={files}
        setFiles={setFiles}
        isDisabledBotonEliminar={isDisabledBotonEliminar}
        formValidacion={formValidacion}
        cargarOpcionesProductoSelecionado={cargarOpcionesProductoSelecionado}
      />
    </>
  );
};

export default RegistroSolicitud;
