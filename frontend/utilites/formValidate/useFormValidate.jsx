import { useState, useEffect } from 'react'
import validateInputsForm, { validateInput, getBoToValidateForm, resetInputs } from '../../utilites/formValidate/validateInputs'
import { LABEL_BTN_CREAR } from '../../utilites/constants'

const useFormValidate = (callback, initalForm, refForm, setclearAnexos, setIsDisabledBotonEliminar, setLabelBtnCrearSolicitud) => {

    const [dataForm, setDataForm] = useState(
        {
            errors: { ...initalForm },
            isSubmitting: false,
            isDisabledBotonSubmit: true
        })
    const [dataInput, setDataInput] = useState({})

    useEffect(() => {
        if (Object.keys(dataForm.errors).length === 0 && dataForm.isSubmitting) callback()
    }, [dataForm.errors])


    const handleSubmit = (e) => {
        if (e) {
            e.preventDefault()
            setDataForm({ ...dataForm, errors: validateInputsForm(e.target), isSubmitting: true })
        }
    }

    const handleChange = (e) => {
        if (e) {
            e.preventDefault()
            setDataInput({ ...dataInput, [e.target.id]: (!e.value ? e.target.value : e.value) })
            setDataForm({ errors: validateInput(e, dataForm.errors) })
            setDataForm({ ...dataForm, isDisabledBotonSubmit: !getBoToValidateForm(e, null, dataForm.errors) })
        }
    }

    const handleClearForm = () => {
        setclearAnexos(true)
        setIsDisabledBotonEliminar(true)
        setLabelBtnCrearSolicitud(LABEL_BTN_CREAR)
        if (refForm) resetInputs(refForm.current)
        setDataInput({ ...initalForm })
        setDataForm({ errors: { ...initalForm }, isSubmitting: false, isDisabledBotonSubmit: true })
    }

    return {
        ...dataForm,
        dataInput,
        handleSubmit,
        handleChange,
        handleClearForm,
        setDataInput
    }
}

export default useFormValidate
