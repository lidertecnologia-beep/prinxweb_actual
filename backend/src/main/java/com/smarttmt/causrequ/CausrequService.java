package com.smarttmt.causrequ;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CausrequService implements CausrequIService {

    private final CausrequIRepository causrequIRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ListaValoresModel> getCausasDevolucion() {
        log.info("init getCausasDevolucion");
        List<CausrequEntity> listCausrequ = causrequIRepository.findAll();
        List<ListaValoresModel> listCausas = new ArrayList<>();
        listCausrequ.forEach(causa -> listCausas.add(new ListaValoresModel(causa.getCarecodi(),causa.getCaredesc())));
        return listCausas;
    }
}
