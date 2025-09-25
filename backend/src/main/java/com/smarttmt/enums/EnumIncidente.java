package com.smarttmt.enums;

public enum EnumIncidente {

    PRESENTA_BLOQUEO_TOTAL_LA_OPERACION("01"),
    PRESENTA_BLOQUEO_EN_UN_PROCESO_GESTION("02"),
    BLOQUEA_PROCESO_PUEDE_CONTINUAR_OPERACION("03"),
    ERROR_FUNCIONAL_NO_BLOQUEOS("04"),
    NO_APLICA("05");

    public String incidente;

    EnumIncidente(String incidente) {
        this.incidente = incidente;
    }

    public static boolean isTipoInicidente(String itemcheq) {
        for (EnumIncidente tipo : values()) {

            if (NO_APLICA.incidente.equals(itemcheq)) {
                return false;
            }

            if (tipo.incidente.equals(itemcheq)) {
                return true;
            }
        }
        return false;
    }


}
