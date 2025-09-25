/* eslint-disable no-unused-vars */
import z from "zod";
import { useFormik } from "formik";

const form = z.object({
  tipoSolicitud: z
    .string()
    .nonempty({ message: "Es requerido el campo Tipo de solicitud" }),
  prioridad: z
    .string()
    .nonempty({ message: "Es requerido el campo Prioridad" }),
  producto: z.string().nonempty({ message: "Es requerido el campo Producto" }),
  opcion: z.string().nonempty({ message: "Es requerido el campo Opción" }),
  validaAtencion: z.object({
    itchcodi: z.string().nonempty({
      message: "Es requerido el campo Valida prioridad de atención",
    }),
    itchestr: z.string(),
  }),
  detalle: z
    .string()
    .nonempty({ message: "Es requerido el campo Detalle" })
    .min(30, { message: "Se debe ingresar minimo 30 caracteres" })
    .max(4000, { message: "Se debe ingresar maximo 4000 caracteres" }),
});

export type TFormRegistroSolicitud = z.infer<typeof form>;

export const useForm = (setIsDisableBtnSubmit: (isSucces: boolean) => void) => {
  const formik = useFormik<TFormRegistroSolicitud>({
    initialValues: {
      tipoSolicitud: "",
      prioridad: "",
      producto: "",
      opcion: "",
      validaAtencion: { itchcodi: "", itchestr: "" },
      detalle: "",
    },
    validate: (values) => {
      try {
        const dataSchema = form.safeParse(values);
        setIsDisableBtnSubmit(!dataSchema.success);
        if (dataSchema.success) return {};
        const errores: Record<string, string> = {};
        dataSchema.error.issues.some(
          (error) => (errores[error.path[0]] = error.message)
        );
        return errores;
      } catch (error) {
        return error.formErrors.fieldErrors;
      }
    },
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return formik;
};

export const useValidateFormRegistroSolicitud = (setIsDisableBtnSubmit: (isSucces: boolean) => void) => {
  const formValidacion = useForm(setIsDisableBtnSubmit);
  return { formValidacion };
};
