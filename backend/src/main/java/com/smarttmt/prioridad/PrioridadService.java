package com.smarttmt.prioridad;

import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Data
@Service
public class PrioridadService implements PrioridadIService {

    @Override
    public List<ListaValoresModel> getListaValores() {
        log.info("inti getListaValores");
        List<ListaValoresModel>  listPrioridad = new ArrayList<>();
        listPrioridad.add(new ListaValoresModel(Constantes.CODIGPRIORIDAD1, Constantes.CODIGPRIORIDAD1DESC));
        listPrioridad.add(new ListaValoresModel(Constantes.CODIGPRIORIDAD2, Constantes.CODIGPRIORIDAD2DESC));
        listPrioridad.add(new ListaValoresModel(Constantes.CODIGPRIORIDAD3, Constantes.CODIGPRIORIDAD3DESC));
        listPrioridad.add(new ListaValoresModel(Constantes.CODIGPRIORIDAD4, Constantes.CODIGPRIORIDAD4DESC));
        listPrioridad.add(new ListaValoresModel(Constantes.CODIGPRIORIDAD5, Constantes.CODIGPRIORIDAD5DESC));
        return listPrioridad;
    }

}
