import { getBlobFetch } from '../pages/api/api'
import { getBlobToFile } from '../utilites/files'
import { useSessionContext } from '../pages/session'

const useAnexos = () => {
  const { dataInitSession } = useSessionContext()

  const cargaListaAnexosToObjFile = async (listAnexos) => {
    const arrayFiles = []
    try {
      for (const anexo of listAnexos) {
        if (anexo.ansosolu && anexo.ansoarch) {
          const endPoint = `${process.env.NEXT_PUBLIC_ENDPOINT_GET_ANEXOS_RESOURCEFILE_SPRING}${dataInitSession.cliente}&requcodi=${anexo.ansosolu}&arsoarch=${anexo.ansoarch}`
          const [objBlob, response] = await Promise.all([
            getBlobFetch(dataInitSession.token, endPoint),
            fetch(`${process.env.NEXT_PUBLIC_ENDPOINT_NODE_GET_MIMETYPE}${anexo.ansoarch.substring(anexo.ansoarch.lastIndexOf('.') + 1)}`)
          ])

          if (objBlob && response) {
            const data = await response.json()
            const file = getBlobToFile(objBlob, anexo.ansoarch, data.mimeType)
            if (file) arrayFiles.push(file)
          }
        }
      }
    } catch (error) {
      console.error('Error cargaListaAnexosToObjFile:', { error })
    }
    return arrayFiles
  }

  return {
    cargaListaAnexosToObjFile
  }
}

export default useAnexos
