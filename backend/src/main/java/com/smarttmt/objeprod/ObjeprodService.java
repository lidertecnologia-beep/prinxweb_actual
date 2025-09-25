package com.smarttmt.objeprod;

import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ObjeprodService implements ObjeprodIService {

    private  final ObjeprodRepository objeprodRepository;
    private final ParaoperIService paraoperIService;

    @Override
    public List<ObjeprodModel> getListaObjetoProducto(String producto) {
        log.info("init getListaValores producto:{}", producto);
        List<ObjeprodModel> listObjetoProducto = new ArrayList<>();
        var list = objeprodRepository.findObjectoProduco(producto,paraoperIService.getParaCodi(Constantes.PARAOPER_CLASES_OBJETOS_FORMA));
        list.forEach(objetoProducto -> listObjetoProducto.add(new ObjeprodModel(String.valueOf(objetoProducto[0]), objetoProducto[0] + " - " + objetoProducto[1], String.valueOf(objetoProducto[2]))));
        return listObjetoProducto;
    }

}
