import { serialize } from "cookie";
import { KEY_COOKIE_AUTH, KEY_COOKIE_PERSONAL } from "../../utilites/constants";

export default function (req, res) {
  const serialized = serialize(KEY_COOKIE_AUTH, false, {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "strict",
    maxAge: 0, // tiempo en segundos expira en lo que falta del dia habil
    path: "/",
  });

  const serializedPersonal = serialize(KEY_COOKIE_PERSONAL, null, {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "strict",
    maxAge: 0, // tiempo en segundos expira en lo que falta del dia habil
    path: "/",
  });

  res.setHeader("Set-Cookie", [serialized, serializedPersonal]);
  return res.status(200).send({ succes: true, error: null });
}
