package com.smarttmt.excepciones;


public class TransaccionBaseDatosException extends RuntimeException {
    public TransaccionBaseDatosException(String mensaje) {
        super(mensaje);
    }
}
