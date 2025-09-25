import { useQuery, useQueryClient } from "@tanstack/react-query";
import getData from "../../pages/api/api";
import { KEY_QUERY_PREFETCH_SOLICITUDES } from "../../utilites/constants";
import storeGlobal from "store/storeGobal";

const cargaListSolicitudes = async (token, cliente) => {
  if (token && cliente) {
    return await getData(
      token,
      `${
        process.env.NEXT_PUBLIC_ENDPOINT_GET_VWREQUERIM_SPRING
      }${cliente}&datareporte=${"N"}`
    );
  }
};

const useQuerySolicitudes = () => {
  const queryClient = useQueryClient();
  const sesion = storeGlobal((state) => state.sesion);

  const solicitudesQuery = useQuery(
    [KEY_QUERY_PREFETCH_SOLICITUDES, sesion?.token, sesion?.cliente],
    () => cargaListSolicitudes(sesion?.token, sesion?.cliente),
    {
      enabled: !!sesion?.token && !!sesion?.cliente,
    }
  );

  const preFetchingSolicitudes = async () => {
    await queryClient.prefetchQuery({
      queryKey: [KEY_QUERY_PREFETCH_SOLICITUDES, sesion?.token, sesion?.cliente],
      queryFn: () => cargaListSolicitudes(sesion?.token, sesion?.cliente),
      enabled: !!sesion?.token && !!sesion?.cliente,
    });
  };

  return {
    solicitudesQuery,
    preFetchingSolicitudes,
  };
};

export default useQuerySolicitudes;
