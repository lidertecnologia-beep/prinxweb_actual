
import 'filepond/dist/filepond.min.css'

// Import the Image EXIF Orientation and Image Preview plugins
// Note: These need to be installed separately
// `npm i filepond-plugin-image-preview filepond-plugin-image-exif-orientation --save`
import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation'
import FilePondPluginImagePreview from 'filepond-plugin-image-preview'
import FilePondPluginGetFile from 'filepond-plugin-get-file';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css'
import 'filepond-plugin-get-file/dist/filepond-plugin-get-file.min.css';
import { useSessionContext } from "../../pages/session";
import { useEffect, useRef } from 'react'
import { sendingFilesPost } from "../../pages/api/api";
import { MENSAJE_ESPACIOS_NOMBRES_ARCHIVOS, MENSAJE_ACENTOS_NOMBRES_ARCHIVOS, REGEX_ESPACIOS_CADENAS, REGEX_CADENAS_ACENTOS } from "../../utilites/constants";
import useAlert from '@customhooks/useAlert'
import { Toast } from 'primereact/toast'
import { FilePond, registerPlugin } from 'react-filepond';


const FileUpload = ({ files, setFiles, enviarArchivo, id, maxFiles = 1, labelIdle, isDisabled = false }) => {

    registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview, FilePondPluginGetFile)

    const regexEspacioCadena = REGEX_ESPACIOS_CADENAS;
    const regesAcentosCadena = REGEX_CADENAS_ACENTOS;
    const { dataInitSession } = useSessionContext();
    const { toast, alertInfo } = useAlert();
    const pondRef = useRef(null);


    const enviarArchivos = async () => {
        if (files && enviarArchivo) {
            const formDataFile = new FormData();
            formDataFile.append("cliente", dataInitSession.cliente);
            formDataFile.append("id", id);
            files.forEach(objFile => formDataFile.append("file", objFile));
            sendingFilesPost(dataInitSession.token, `${process.env.NEXT_PUBLIC_ENDPOINT_POST_UPLOADFILE_SPRING}`, formDataFile);
        }
    }

    const validarArchivos = (fileItems) => {

        const isFileSpaces = fileItems?.some(fileItem => regexEspacioCadena.test(fileItem?.file.name))
        if (isFileSpaces) {
            alertInfo(MENSAJE_ESPACIOS_NOMBRES_ARCHIVOS);
            return false
        }

        const isAcentos = fileItems?.some(fileItem => regesAcentosCadena.test(fileItem?.file.name))
        if (isAcentos) {
            alertInfo(MENSAJE_ACENTOS_NOMBRES_ARCHIVOS);
            return false
        }

        return true

    };

    const cargarArchivos = (fileItems) => {
        const isValido = validarArchivos(fileItems)
        if (!isValido) {
            setFiles(null)
            pondRef.current.removeFiles();
            return
        }
        setFiles(fileItems.map(fileItem => fileItem.file))
    }

    useEffect(() => {
        if (enviarArchivo) enviarArchivos()
    }, [enviarArchivo])

    return (
        <>
            <Toast ref={toast} position='center' />
            <FilePond
                ref={pondRef}
                className='bg-primary'
                files={files}
                onupdatefiles={(fileItems) => cargarArchivos(fileItems)}
                allowMultiple={true}
                maxFiles={maxFiles}
                labelButtonDownloadItem="Descargar archivo"
                credits={false}
                labelIdle={labelIdle ?? (maxFiles === 1 ? 'Cargar archivo' : 'Cargar archivos')}
                disabled={isDisabled}
                allowImageExifOrientation={true}
                allowImagePreview={true}
                allowRevert={false} // Evita que FilePond manipule el archivo después de subirlo
                allowProcess={false} // Desactiva el procesamiento automático si usas tu propia lógica
                server={{
                    // Si usas servidor, configura endpoints específicos
                    process: null, // Desactiva si manejas el envío manualmente
                }}
            />
        </>
    )
}

export default FileUpload