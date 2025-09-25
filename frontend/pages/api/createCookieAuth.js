/* eslint-disable import/no-anonymous-default-export */
import { serialize } from "cookie";
import { KEY_COOKIE_AUTH, KEY_COOKIE_PERSONAL } from "../../utilites/constants";

export default function (req, res) {
  const cookieAuth = req.body;
  const { personal } = req.body;

  if (
    cookieAuth &&
    cookieAuth.authSucces &&
    cookieAuth.contexSession === "AuthProvider"
  ) {
    const currentHours = new Date().getHours();
    const hoursDay = 24;
    const missingHoursXDay = hoursDay - currentHours;

    const serialized = serialize(KEY_COOKIE_AUTH, true, {
      httpOnly: true,
      // secure: process.env.NODE_ENV === 'production',
      sameSite: "strict",
      maxAge: missingHoursXDay * 3600, // tiempo en segundos expira en lo que falta del dia habil
      path: "/",
    });

    const serializedPersonal = serialize(
      KEY_COOKIE_PERSONAL,
      JSON.stringify(personal),
      {
        httpOnly: true,
        // secure: process.env.NODE_ENV === 'production',
        sameSite: "strict",
        maxAge: missingHoursXDay * 3600, // tiempo en segundos expira en lo que falta del dia habil
        path: "/",
      }
    );

    res.setHeader("Set-Cookie", [serialized, serializedPersonal]);
    return res.status(200).send({ succes: true, error: null });
  }

  return res
    .status(500)
    .send({ succes: false, error: "failed to create cookie" });
}
