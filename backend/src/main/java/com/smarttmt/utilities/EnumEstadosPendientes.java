package com.smarttmt.utilities;

public enum EnumEstadosPendientes {

    ACTIVO("ac"),
    ASIGNADO("as"),
    CONSTRUCION_DESARROLLO("co"),
    ANALISIS("ea"),
    PRUEBAS("eb"),
    DISENO("ed"),
    DOCUMENTACION("eo"),
    PENDIENTE("pe"),
    PROGRAMADO("pr");

    private String codigo;

    EnumEstadosPendientes(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
