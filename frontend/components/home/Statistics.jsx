import { Chart } from "primereact/chart";
import Link from "next/link";
import { CONSULTA_DESDE_ESTADISTICA } from "../../utilites/constants";
import { useEffect } from "react";
import { useEstadosEstadisticas } from "../../customhooks/useEstadosEstadisticas";

const Statistics = ({ setIsHideModalPage }) => {
  const { dataChar, listLinks } = useEstadosEstadisticas();

  useEffect(() => {
    if (listLinks && dataChar) {
      setIsHideModalPage(true);
    }
  }, [dataChar, listLinks]);

  return (
    <>
      {listLinks && dataChar && (
        <div className="statistics">
          <div className="statistics__content">
            <Chart
              type="doughnut"
              data={dataChar}
              className="statistics__graphic"
            />
            <div>
              {listLinks.length > 0 &&
                listLinks.map((link) => (
                  <Link
                    className="links"
                    key={link.codigo}
                    href={{
                      pathname: "/consultasolicitudes",
                      query: {
                        tq: CONSULTA_DESDE_ESTADISTICA,
                        estacodi: link?.codigo,
                      },
                    }}
                  >
                    <div className="statistics__links">
                      <span
                        className="statistics__links-item"
                        style={{ backgroundColor: `${link?.color}` }}
                      />
                      <div>{`${link?.estado}:`}</div>
                      <div className="statistics__link-item--final">{`${link?.cantidad}`}</div>
                    </div>
                  </Link>
                ))}
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Statistics;
