package com.smarttmt.anexos;

import com.smarttmt.excepciones.ExcepcionNoControlada;
import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.tercero.IServiceTercero;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.LeeCadena;
import com.smarttmt.utilities.MueveArchivos;

import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class AnexsoliService implements AnexsoliIService<Anexsoli2> {

    Logger logger = LoggerFactory.getLogger(AnexsoliService.class);

    protected IAnexsoli2Repository iAnexsoli2Repository;
    protected IServiceTercero iServiceTercero;
    protected ParaoperIService iServiceGenericParaoper;
    protected AnexsoliIRepositoryStoredProcedure iRepositoryStoredProcedureAnexsoli;
    protected AnexsoliIRepository anexsoliIRepository;

    @Autowired
    public AnexsoliService(
            IAnexsoli2Repository iAnexsoli2Repository,
            IServiceTercero iServiceTercero,
            ParaoperIService iServiceGenericParaoper,
            AnexsoliIRepositoryStoredProcedure iRepositoryStoredProcedureAnexsoli,
            AnexsoliIRepository anexsoliIRepository) {
        this.iAnexsoli2Repository = iAnexsoli2Repository;
        this.iServiceTercero = iServiceTercero;
        this.iServiceGenericParaoper = iServiceGenericParaoper;
        this.iRepositoryStoredProcedureAnexsoli = iRepositoryStoredProcedureAnexsoli;
        this.anexsoliIRepository = anexsoliIRepository;
    }

    @Override
    public Resource getResourceFile(Integer numeroRequerim, String nombreArchivo) {
        log.info("init getAnexsoliCodigoSolicitudNombreArchivo  numeroRequerim:{} nombreArchivo:{}", numeroRequerim, nombreArchivo);
        Resource resourceFile = null;
        if (numeroRequerim != null && nombreArchivo != null) {
            Optional<Anexsoli2> optionalAnexsoli = iAnexsoli2Repository.getAnexsoliByAnsosoluAnAndAnsoarch(numeroRequerim, nombreArchivo);
            if (optionalAnexsoli.isPresent()) {
                Anexsoli2 anexsoli2 = optionalAnexsoli.get();
                if (StringUtils.hasText(anexsoli2.getAnsoarch()) && StringUtils.hasText(anexsoli2.getAnsoruar())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(anexsoli2.getAnsoruar()).append(anexsoli2.getAnsoarch());
                    resourceFile = new FileSystemResource(stringBuilder.toString());
                    log.info("getResourceFile name:{}", resourceFile.getFilename());
                    return resourceFile;
                }

            }
        }
        return resourceFile;
    }

    @Override
    public String getRutaAnexo(String tercero, String numeroRequerim) {
        log.info("init getRutaAnexo  tercero:{} numeroRequerim:{}", tercero, numeroRequerim);
        String rutaArch = iServiceGenericParaoper.getParaCodi(Constantes.PARAOPER_RUTA_ARCHIVOS_PRINX_ASP);
        String items = iServiceTercero.getItemsTercero(tercero);
        log.info("moverAnexo items:{}", items);
        String terccoal = (items != null && !items.isEmpty()) ? LeeCadena.funLeerItems(items, Constantes.TERCCOAL) : null;
        rutaArch = (terccoal != null && !terccoal.isEmpty())
                ? rutaArch + terccoal + System.getProperty("file.separator") + numeroRequerim + System.getProperty("file.separator")
                : rutaArch + System.getProperty("file.separator") + numeroRequerim + System.getProperty("file.separator");
        return rutaArch;
    }

    @Override
    public void moverAnexo(InputStream is, String rutaAnexo, String subDirectorio, String nombreAnexo) {
        log.info("init moverAnexo  is:{} subDirec:{} archivo:{} numeroRequerim:{}", is, rutaAnexo, subDirectorio, nombreAnexo);
        if (is != null && rutaAnexo != null && nombreAnexo != null) {
            MueveArchivos.mover(is, rutaAnexo, subDirectorio, nombreAnexo);
        }
    }

    @Override
    public String insertarAnexo(String requerimiento, String nombreArchivo, String rutaArchivo) {
        log.info("init insertarAnexo  requerimiento:{} nombreArchivo:{} rutaArchivo:{}", requerimiento, nombreArchivo, rutaArchivo);
        StringBuilder sb = new StringBuilder();
        sb.append("ANSOSOLU:").append(requerimiento).append("|");
        sb.append("ANSOARCH:").append(nombreArchivo).append("|");
        sb.append("ANSORUAR:").append(rutaArchivo);
        return iRepositoryStoredProcedureAnexsoli.crear(sb.toString());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Anexsoli> getAnexsoli(Integer numeroRequerim) {
        log.info("init getAnexsoli");
        if (numeroRequerim == null) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_ERROR_NO_EXISTE_REQUERIM);
        }
        return anexsoliIRepository.getListAnexsoli(numeroRequerim);
    }

    @Transactional(readOnly = true)
    @Override
    public String borrarAnexsoli(Integer inAnsoSolu, Integer inAnsoRequ) {
        log.info("init getAnexsoli inAnsoSolu:{} inAnsoRequ:{}", inAnsoSolu, inAnsoRequ);
        if (!Optional.ofNullable(inAnsoSolu).isPresent() && !Optional.ofNullable(inAnsoRequ).isPresent()) {
            throw new IllegalArgumentException("Es requerido el numero del requerimiento");
        }
        return iRepositoryStoredProcedureAnexsoli.borrar(inAnsoSolu, inAnsoRequ);
    }

    public void cargarAnexos(String cliente, String numeroRequerim, MultipartFile[] fileInputStream) {
        log.info("init cargarAnexos cliente:{} numeroRequerim:{}",cliente,numeroRequerim);
        try {
            fileInputStream = Optional.ofNullable(fileInputStream).orElseThrow(()->new IllegalArgumentException("Es requerido el array de archivos"));
            String rutaAnexo = getRutaAnexo(cliente, numeroRequerim);
            for (MultipartFile file : fileInputStream) {
                String nombreAnexo = file.getOriginalFilename();
                moverAnexo(file.getInputStream(), rutaAnexo, null, nombreAnexo);
                var result = insertarAnexo(numeroRequerim, nombreAnexo, rutaAnexo);
                log.info("cargarAnexos:{}",result);
            }
        } catch (IOException e) {
            throw new ExcepcionNoControlada(e.getMessage());
        }
    }

}
