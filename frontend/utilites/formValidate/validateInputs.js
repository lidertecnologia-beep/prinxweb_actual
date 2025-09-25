
const getElementId = (id) => document.getElementById(id)
const isExistErrors = (errors) => (Object.values(errors).some(el => el && el !== null))
const isFieldSelect = (el) => (el.querySelector('select'))


const validateInputsForm = (form) => {
    let errors = {};
    const arrayInputs = Array.from(form)
    arrayInputs.forEach((e) => {
        if (e.required && ((e.nodeName.toUpperCase() === 'SELECT' && !e.options[0].value) || e.nodeName.toUpperCase() !== 'SELECT' && !e.value)) {
            //si no tiene id es un select
            const idEl = (e.id) ? e.id : e.name
            const $el = form.querySelector(`label[for='${idEl}']`)
            if ($el) errors[idEl] = `${$el.textContent} es requerido`
        }
    })
    return errors
}
const validForm = (form) => {

    const arrayInputs = Array.from(form)
    if (arrayInputs) {
        return !arrayInputs.some(element => element.required &&
            ((element.nodeName.toUpperCase() === 'SELECT' && !element.options[0].value) || (element.nodeName.toUpperCase() !== 'SELECT' && !element.value)))
    }
    return false
}

export const validateInput = (e, errors) => {

    if (e && errors) {

        const elId = e.target.id
        const $el = getElementId(elId)

        if ($el) {

            const $elSelecet = $el.querySelector('select')
            if ($elSelecet?.hasAttribute('required') && e.value) {
                errors[elId] = null
                return errors
            }

            const elTextLengt = e.target.textLength
            if ($el.nodeName === 'TEXTAREA') {
                errors[elId] = null
                if (elTextLengt < 50) {
                    errors[elId] = 'Se debe ingresar minimo 50 caracteres'
                    return errors
                }
            }

            if ($el.nodeName === 'INPUT' && $el.required && $el.value) {
                errors[elId] = null
                return errors
            }
        }
    }

    return errors
}

export const getBoToValidateForm = (event, form, errors) => {

    if (!event) {
        if (form) return ((validForm(form)) && !isExistErrors(errors))
        return false
    }

    let $form = null
    const $el = getElementId(event.target.id)
    if (!$el) return false

    const isElSelect = isFieldSelect($el)
    $form = (isElSelect ? document.querySelector('form') : $el.form)
    $form = (isElSelect && !$form.contains($el) ? null : $form)
    return ((validForm($form)) && !isExistErrors(errors))
}

export const resetInputs = (form) => {
    const arrayInputs = Array.from(form)
    arrayInputs.forEach(($el) => {
        if ($el?.value?.length > 0) $el.value = ''
    })
}

export function campoSoloNumero(event) {

    if (event.key === 'Delete' || event.key === 'Backspace') {
        return true;
    }

    if (event.keyCode <= 47 || event.keyCode >= 59) {
        event.preventDefault();
        return false;
    } else {
        return true;
    }
}

export default validateInputsForm
