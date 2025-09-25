package com.smarttmt.tiporequ;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TiporequService implements TiporequIService {

    private final TiporequIRepository tiporequIRepository;

    @Transactional(readOnly = true)
    @Override
    public TiporequEntity getClasificacionTiporequ(String tiretire, String tirecodi) {

        log.info("init getClasificacionTiporequ tiretire:{} tirecodi:{}", tiretire, tirecodi);

        if (Optional.ofNullable(tiretire).isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.TIPOREQU_CAMPO_TIRETIRE_NULO);
        }

        if (Optional.ofNullable(tirecodi).isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.TIPOREQU_CAMPO_TIRECODI_NULO);
        }

        return tiporequIRepository.getClasificaionTipoRequirimeinto(tiretire, tirecodi)
                .orElseThrow(() -> new RecursoNoEncontradoException(Constantes.LISTA_CLASIFICACION_REQUERIMIENTO_VACIA));

    }

    public List<ListaValoresModel> getListaValores(){
        log.info("inti getListaValores");
        List<ListaValoresModel> listTipoRequ = new ArrayList<>();
        listTipoRequ.add(new ListaValoresModel(Constantes.CLASREQUCODIGPETICION,Constantes.CLASREQUDESCPETICION));
        listTipoRequ.add(new ListaValoresModel(Constantes.CLASREQUCODIGQR,Constantes.CLASREQUDESCQR));
        return listTipoRequ;
    }

}
