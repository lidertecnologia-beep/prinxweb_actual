import RegistroSolicitud from "../components/solicitud/RegistroSolicitud";
import LoadingPage from "../utilites/modal/LoadingPage";
import { useEffect } from "react";
import ContainerTitle from "../components/layout/ContainerTitle";

const PageRegistroSolicitud = (props) => {
  const { setHideModalEvent } = props;

  useEffect(() => {
    setTimeout(
      () => setHideModalEvent(),
      process.env.NEXT_PUBLIC_ENV_LOCAL_TIME_WAIT_MODAL_PRINX
    );
  }, []);

  return (
    <>
      <div>
        <ContainerTitle title="Registro de solicitud" />
        <RegistroSolicitud />
      </div>
    </>
  );
};

export default LoadingPage(PageRegistroSolicitud);
