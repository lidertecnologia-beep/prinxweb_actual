import {
  createContext,
  useState,
  useContext,
  useCallback,
  useEffect,
  useMemo,
} from "react";
import { useRouter } from "next/router";
import { HOME } from "../utilites/paths";
import useLoadDropdown from "../utilites/useLoadDropdown";

const sessionContext = createContext({});
export const useSessionContext = () => useContext(sessionContext);

const cookieAuth = {
  authSucces: false,
  contexSession: "AuthProvider",
  personal: null,
};

export const AuthProvider = ({ children }) => {
  const router = useRouter();
  const [dataInitSession, setDataInitSession] = useState({
    token: "",
    cliente: "",
    tipoCliente: "",
    descripcionCliente: "",
    usuario: "",
    logoCliente: "",
    descUsuario: "",
    personal: "",
    personalIniciales: "",
  });
  const [addClassShowMenuMovil, setAddClassShowMenuMovil] = useState(false);
  const [dataDropdown, setDataDropdown] = useState({});

  const createCookieAuth = async (personal) => {
    try {
      cookieAuth.personal = personal;
      cookieAuth.authSucces = true;
      const resp = await fetch("/api/createCookieAuth", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cookieAuth),
      });
      if (resp.statusText === "OK" || resp.status === 200) {
        router.push(HOME);
      }
    } catch (e) {
      console.error("cookie log", e);
    }
  };

  const deleteCookieAuth = async () => {
    await fetch("/api/deleteCookieAuth");
  };

  const getCookieAuth = async () => {
    const datAuth = await fetch("/api/getCookieAuth");
    const { authSucces, personal } = await datAuth.json();
    if (authSucces) {
      const personalParse = JSON.parse(personal);
      setDataInitSession({ ...personalParse });
      loadingDataDropdown({ ...personalParse }).then((dataDropdown) =>
        setDataDropdown(dataDropdown)
      );
    }
  };

  const { loadingDataDropdown } = useLoadDropdown();

  const authenticated = useCallback((data) => {
    createCookieAuth(data);
  }, []);

  const logOut = useCallback(() => {
    setAddClassShowMenuMovil(false);
    deleteCookieAuth();
  }, []);

  const valueContextAuth = useMemo(
    () => ({
      authenticated,
      addClassShowMenuMovil,
      setAddClassShowMenuMovil,
      dataInitSession,
      setDataInitSession,
      logOut,
      dataDropdown,
      setDataDropdown,
    }),
    [
      authenticated,
      addClassShowMenuMovil,
      setAddClassShowMenuMovil,
      dataInitSession,
      setDataInitSession,
      logOut,
      dataDropdown,
      setDataDropdown,
    ]
  );

  useEffect(() => {
    getCookieAuth();
  }, []);

  return (
    <sessionContext.Provider value={valueContextAuth}>
      {children}
    </sessionContext.Provider>
  );
};

export default AuthProvider;
