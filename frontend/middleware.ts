// middleware.ts
import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";
import { LOGIN } from "./utilites/paths";
import { KEY_COOKIE_AUTH } from "./utilites/constants";

export const config = {
  matcher: ["/home", "/perfil", "/registrosolicitud", "/consultasolicitudes"],
};

export function middleware(request: NextRequest) {
  const cookieIsAuth = request.cookies.get(KEY_COOKIE_AUTH);

  if (!cookieIsAuth) {
    request.nextUrl.pathname = LOGIN;
    return NextResponse.redirect(request.nextUrl);
  }
}
