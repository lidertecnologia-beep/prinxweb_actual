package com.smarttmt.excepciones;

public class ExcepcionNoControlada extends RuntimeException{
    public ExcepcionNoControlada(String mensaje) {
        super(mensaje);
    }
}
