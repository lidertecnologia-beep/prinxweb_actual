package com.smarttmt.anexos;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AnexsoliIService<T> {

    Resource getResourceFile(Integer numeroRequerim, String nombreArchivo);

    String getRutaAnexo(String tercero, String numeroRequerim);

    void moverAnexo(InputStream is, String rutaAnexo, String subDirectorio, String nombreAnexo);

    public String insertarAnexo(String requerimiento, String nombreArchivo, String rutaArchivo);

    List<Anexsoli> getAnexsoli(Integer numeroRequerim);

    String borrarAnexsoli(Integer inAnsoSolu, Integer inAnsoRequ);

    void cargarAnexos(String cliente, String numeroRequerim, MultipartFile[] fileInputStream);

}
