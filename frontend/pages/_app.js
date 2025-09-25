import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.min.css";
import "../styles/scss/style.css";
import { AuthProvider } from "./session";
import Layout from "../components/layout/Layout";
import { useRouter } from "next/router";
import { addLocale, locale } from "primereact/api";
import { useEffect } from "react";
import ControllerProvider from "./contextprovider";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ROOT, RECORDAR_PASSWORD } from "../utilites/constants";
import ErrorBoundary from "./ErrorBoundary";

const queryClient = new QueryClient();

function MyApp({ Component, pageProps }) {
  useEffect(() => {
    addLocale("es", {
      firstDayOfWeek: 1,
      dayNames: [
        "domingo",
        "lunes",
        "martes",
        "miércoles",
        "jueves",
        "viernes",
        "sábado",
      ],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
      monthNames: [
        "enero",
        "febrero",
        "marzo",
        "abril",
        "mayo",
        "junio",
        "julio",
        "agosto",
        "septiembre",
        "octubre",
        "noviembre",
        "diciembre",
      ],
      monthNamesShort: [
        "ene",
        "feb",
        "mar",
        "abr",
        "may",
        "jun",
        "jul",
        "ago",
        "sep",
        "oct",
        "nov",
        "dic",
      ],
      today: "Hoy",
      clear: "Limpiar",
    });
    locale("es");
  }, []);

  const router = useRouter();

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <AuthProvider>
          <ControllerProvider>
            <ErrorBoundary>
              {router.pathname === ROOT ||
              router.pathname === RECORDAR_PASSWORD ? (
                <Component {...pageProps} />
              ) : (
                <Layout>
                  <Component {...pageProps} />
                </Layout>
              )}
            </ErrorBoundary>
          </ControllerProvider>
        </AuthProvider>
      </QueryClientProvider>
    </>
  );
}

export default MyApp;
