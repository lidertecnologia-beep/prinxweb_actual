import { useRef, useState } from 'react'
import isFileImage from '../utilites/files'


const useCreatePrevieLoadFile = () => {
  const [file, setFile] = useState(null)
  const [idDiv, setIdDiv] = useState(null)
  const [elDivPreviewLoadFile, setElDivPreviewLoadFile] = useState(null)
  const [progressValue, setProgressValue] = useState(0)
  const [arrayFiles, setArrayFiles] = useState([{}])
  const refDrowDownFiles = useRef(null)

  const handlerShowListDropDown = (e) => {
    if (e) {
      e.stopPropagation()
      const nameClassMenu = 'previewLoadFile__dropdownmenu--show'
      const $elAllBtnEvent = document.querySelectorAll('.previewLoadFile__iconbutton')
      for (const element of $elAllBtnEvent) {
        if (e.target.parentElement.getAttribute('name') !== element.getAttribute('name')) {
          const othersDropdownMenu = element.querySelector(`${nameClassMenu}`)
          if (othersDropdownMenu) othersDropdownMenu.classList.toggle(nameClassMenu)
        }
      }

      const $targetViewportElement = e.target.viewportElement
      if ($targetViewportElement) {
        const $divIconButtonShowDropDown = $targetViewportElement.nextElementSibling
        if ($divIconButtonShowDropDown) $divIconButtonShowDropDown.classList.toggle(nameClassMenu)
      }
    }
  }

  const cargaDivOverlay = () => <div className='previewLoadFile__overlay' />

  const cargaDivIconbutton = (file, idDiv) => {
    return (
      <div className='previewLoadFile__iconbutton' onClick={handlerShowListDropDown} name={file.name}>
        <svg height='100%' width='100%' viewBox='-40 -40 193 230' name={file.name}>
          <image href='/img/chevron-down-solid.svg' name={file.name} />
        </svg>
        <div className='previewLoadFile__dropdownmenu' ref={refDrowDownFiles}>
          <a className='previewLoadFile__dropdownmenu--link' href={(URL.createObjectURL(file))} download>
            <span className='previewLoadFile__dropdownmenu--text'>Descargar archivo</span>
          </a>
          <a className='previewLoadFile__dropdownmenu--link' onClick={() => setElDivPreviewLoadFile(idDiv)}>
            <span className='previewLoadFile__dropdownmenu--text'>Eliminar archivo</span>
          </a>
        </div>
      </div>
    )
  }

  const cargaDivLoaderProgress = (file, idDiv) => {
    return (
      <div key={idDiv} id={idDiv} className='previewLoadFile__loader-progress'>
        <div className='previewLoadFile__loader-progress--img'>
          <svg height='100%' width='100%' viewBox='-40 -40 193 230'>
            <image href='/img/file-alt-solid.svg' />
          </svg>
        </div>
        <div className='previewLoadFile__loader-progress--content'>
          <span className='previewLoadFile__text'>{file.name}</span>
          <progress value={progressValue} max='100' />
        </div>
        {cargaDivOverlay(file.name)}
        {cargaDivIconbutton(file, idDiv)}
      </div>
    )
  }

  const cargaDivFileLoadImg = (file, idDiv) => {
    return (
      <div key={idDiv} id={idDiv} className='previewLoadFile__load'>
        <div className='previewLoadFile__load--img'>
          <img src={URL.createObjectURL(file)} />
        </div>
        {cargaDivOverlay()}
        {cargaDivIconbutton(file, idDiv)}

      </div>
    )
  }

  const previewLoadFile = (file) => {
    let arrayFiles2 = [{}]
    if (file) {
      Array.from(file).forEach(file => {
        const fileReader = new FileReader()
        fileReader.readAsDataURL(file)

        fileReader.addEventListener('progress', e => {
          setProgressValue(parseInt(e.loaded * 100 / e.total))
        })

        arrayFiles2 = [...arrayFiles2, {
          idDiv: file.lastModified,
          file,
          elDivPreviewLoadFile:
            isFileImage(file)
              ? cargaDivFileLoadImg(file, file.lastModified)
              : cargaDivLoaderProgress(file, file.lastModified)
        }]
      })

      setArrayFiles([...arrayFiles2])
    }
  }

  return {
    file,
    idDiv,
    elDivPreviewLoadFile,
    arrayFiles,
    setArrayFiles,
    setFile,
    previewLoadFile,
    setElDivPreviewLoadFile,
    handlerShowListDropDown,
    setIdDiv
  }
}
export default useCreatePrevieLoadFile
