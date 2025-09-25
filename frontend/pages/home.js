import Statistics from "../components/home/Statistics";
import LoadingPage from "../utilites/modal/LoadingPage";
import { useEffect, useState } from "react";
import ContainerTitle from "../components/layout/ContainerTitle";

const Home = (props) => {
  const [isHideModalPage, setIsHideModalPage] = useState(false);
  const { setHideModalEvent} = props;

  useEffect(() => {
    if (isHideModalPage)
      setTimeout(
        () => setHideModalEvent(),
        process.env.NEXT_PUBLIC_ENV_LOCAL_TIME_WAIT_MODAL_PRINX
      );
  }, [isHideModalPage]);

  return (
    <>
      <ContainerTitle title="Estado de las solicitudes" />
      <Statistics setIsHideModalPage={setIsHideModalPage} />
    </>
  );
};


export default LoadingPage(Home);
