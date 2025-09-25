package com.smarttmt.requerim;


public class FilterVwRequerim {

    public FilterVwRequerim() {
    }

    public FilterVwRequerim(String numeroRequerim, String fechaSolicitud, String fechaCompromiso, String estado, String detalle) {
        this.numeroRequerim = numeroRequerim;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaCompromiso = fechaCompromiso;
        this.estado = estado;
        this.detalle = detalle;
    }

    private String numeroRequerim;
    private String fechaSolicitud;
    private String fechaCompromiso;
    private String estado;
    private String detalle;

    public String getNumeroRequerim() {
        return numeroRequerim;
    }

    public void setNumeroRequerim(String numeroRequerim) {
        this.numeroRequerim = numeroRequerim;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(String fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
