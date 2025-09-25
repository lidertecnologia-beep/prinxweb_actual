import LoadingPage from "../utilites/modal/LoadingPage";
import { useEffect, useState } from "react";
import CabeceraPaginaSolicitudes from "../components/solicitud/CabeceraPaginaSolicitudes";
import TablaSolicitudes from "../components/solicitud/TablaSolicitudes";
import ContainerTitle from "../components/layout/ContainerTitle";
import { useRouter } from "next/router";

const PageConsultaSolicitudes = (props) => {
  const [isHideModalPage, setIsHideModalPage] = useState(false);
  const { setHideModalEvent } = props;
  const router = useRouter();

  useEffect(() => {
    if (isHideModalPage)
      setTimeout(
        () => setHideModalEvent(),
        process.env.NEXT_PUBLIC_ENV_LOCAL_TIME_WAIT_MODAL_PRINX
      );
  }, [isHideModalPage]);

  return (
    <>
      <ContainerTitle title="Consulta de solicitudes" />
      <div className="p-mb-4">
        <CabeceraPaginaSolicitudes />
      </div>
      <div>
        <TablaSolicitudes
          tipoQuery={router.query.tq}
          estadosIn={router.query.estacodi}
          // cuando se carga la tabla se esconde el loanding de pagina por eso el paso de props loadingPage isLoadingPage
          setIsHideModalPage={setIsHideModalPage}
          isHideModalPage={isHideModalPage}
        />
      </div>
    </>
  );
};

export default LoadingPage(PageConsultaSolicitudes);
