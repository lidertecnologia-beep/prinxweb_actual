import { useRef, useEffect, useState } from "react";
import useCreatePrevieLoadFile from "../../customhooks/useCreatePreviewLoadFile";
import { useSessionContext } from "../../pages/session";
import { sendingFilesPost } from "../../pages/api/api";

const Anexos = ({ files, sendFiles, idSolicitud, clear }) => {
  const refContainerLoadFile = useRef(null);
  const [arrayFilesAnexos, setArrayFilesAnexos] = useState([{}]);
  const { dataInitSession } = useSessionContext();

  const deleteDivArray = (idDiv) => {
    if (arrayFilesAnexos.length > 0 && idDiv) {
      arrayFilesAnexos.reduce((x, currentValue, index) => {
        if (currentValue && currentValue.idDiv == idDiv) {
          arrayFilesAnexos.splice(index, 1);
          setArrayFilesAnexos([...arrayFilesAnexos]);
        }
      }, {});
    }
  };

  const loadFile = (files) => previewLoadFile(files);

  const sendingFiles = (sendFiles, idSolicitud) => {
    if (sendFiles && idSolicitud) {
      arrayFilesAnexos.forEach((loadedFile) => {
        if (loadedFile.file) {
          const formDataFile = new FormData();
          formDataFile.append("cliente", dataInitSession.cliente);
          formDataFile.append("id", idSolicitud);
          formDataFile.append("file", loadedFile.file);
          formDataFile.append("file", loadedFile.file);
          sendingFilesPost(
            dataInitSession.token,
            `${process.env.NEXT_PUBLIC_ENDPOINT_POST_UPLOADFILE_SPRING}`,
            formDataFile
          );
        }
      });
    }
  };

  const { idDiv, file, elDivPreviewLoadFile, arrayFiles, previewLoadFile } =
    useCreatePrevieLoadFile();

  useEffect(() => {
    if (files) loadFile(files);
  }, [files]);

  useEffect(() => {
    if (elDivPreviewLoadFile) {
      typeof elDivPreviewLoadFile === "number"
        ? deleteDivArray(elDivPreviewLoadFile)
        : setArrayFilesAnexos([
            ...arrayFilesAnexos,
            { idDiv, file, elDivPreviewLoadFile },
          ]);
    }
  }, [elDivPreviewLoadFile]);

  useEffect(() => {
    if (sendFiles && idSolicitud) sendingFiles(sendFiles, idSolicitud);
  }, [sendFiles, idSolicitud]);

  useEffect(() => {
    if (arrayFiles) setArrayFilesAnexos([...arrayFilesAnexos, ...arrayFiles]);
  }, [arrayFiles]);

  useEffect(() => {
    if (clear === 'S') {
      setArrayFilesAnexos([]);
    }
    return () => {
      setArrayFilesAnexos([]);
    };
  }, [clear]);

  useEffect(() => {
    return () => {
      setArrayFilesAnexos([]);
    };
  }, []);

  return (
    <div className="p-fluid p-grid">
      <div className="p-field p-col-12 p-sm-12 p-md-12 p-lg-12">
        <div className="previewLoadFile" ref={refContainerLoadFile}>
          {arrayFilesAnexos &&
            arrayFilesAnexos.map(
              (loadedFile) => loadedFile.elDivPreviewLoadFile
            )}
        </div>
      </div>
    </div>
  );
};

export default Anexos;
