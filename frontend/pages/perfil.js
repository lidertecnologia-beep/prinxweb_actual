import FormProfile from "../components/profile/FormProfile";
import { useEffect } from "react";
import LoadingPage from "../utilites/modal/LoadingPage";
import ContainerTitle from "../components/layout/ContainerTitle";

const PagePerfil = (props) => {
  const { setHideModalEvent } = props;

  useEffect(() => {
    setTimeout(
      () => setHideModalEvent(),
      process.env.NEXT_PUBLIC_ENV_LOCAL_TIME_WAIT_MODAL_PRINX
    );
  }, []);

  return (
    <>
      <ContainerTitle title="Edición del perfil" />
      <FormProfile />
    </>
  );
};

export default LoadingPage(PagePerfil);
