import { useEffect, useState } from "react";
import getData from "../pages/api/api";
import { useSessionContext } from "../pages/session";
import { useQuery } from "@tanstack/react-query";

export const useEstadosEstadisticas = () => {
  const { dataInitSession } = useSessionContext();
  const [dataChar, setDataChart] = useState();
  const [listLinks, setListLinks] = useState();
  const listEstadosLinks = [{}];

  const getEstados = async () => {
    return await getData(
      dataInitSession?.token,
      `${process.env.NEXT_PUBLIC_ENV_LOCAL_PATH_API_SPRING}estado/estadosDetalle?cliente=${dataInitSession?.cliente}`
    );
  };

  const estadosQuery = useQuery(["keyQr-estados-estadisticas"], getEstados);

  const cargatEstadisticas = () => {
    if (!estadosQuery.isLoading) {
      const estados = estadosQuery.data;

      if (Object.keys(estados).length > 0) {
        const chartData = {
          labels: [],
          datasets: [
            { data: [], backgroundColor: [], hoverBackgroundColor: [] },
          ],
        };
        estados?.forEach((el, index) => {
          chartData.datasets[0].data[index] = el.cantidad;
          chartData.datasets[0].backgroundColor[index] = el.color;
          chartData.datasets[0].hoverBackgroundColor[index] = el.hover;
          listEstadosLinks[index] = {
            estado: el.labellink,
            cantidad: el.cantidad,
            color: el.color,
            codigo: el.codigo,
          };
        });
        setListLinks([...listEstadosLinks]);
        setDataChart(chartData);
      }
    }
  };

  useEffect(() => {
    cargatEstadisticas();
  }, [estadosQuery.data]);

  useEffect(() => {
    return () => {
      setListLinks(null);
      setDataChart(null);
    };
  }, []);

  return {
    dataChar,
    listLinks,
    estadosQuery,
  };
};
