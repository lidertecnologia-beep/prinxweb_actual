package com.smarttmt.excepciones;

import java.io.IOException;

public class ErrorEntradaSalidaDatos extends IOException {
    public ErrorEntradaSalidaDatos(String mensaje){
        super(mensaje);
    }
}
