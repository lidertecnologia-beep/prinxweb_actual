import getData from "../pages/api/api";

const useLoadDropdown = () => {
  const loadingDataDropdown = async ({ token, cliente }) => {
    if (token && cliente) {
      const [causrequ, estados] = await Promise.all([
        getData(
          token,
          `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_CAUSAS_DEVOLUCION_SPRING}${cliente}`
        ),
        getData(
          token,
          `${process.env.NEXT_PUBLIC_ENDPOINT_GET_LISTVAL_ESTADO_SPRING}${cliente}`
        ),
      ]);
      const dataDropdowns = { causrequ, estados };
      return dataDropdowns;
    }
  };

  return { loadingDataDropdown };
};

export default useLoadDropdown;
