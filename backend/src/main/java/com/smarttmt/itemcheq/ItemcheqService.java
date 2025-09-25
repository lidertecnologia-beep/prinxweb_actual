package com.smarttmt.itemcheq;


import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ItemcheqService implements ItemcheqIService {

    private final ItemcheqIRepository itemcheIRepository;
    private final ParaoperIService paraoperIService;

    @Override
    public List<ItemcheqEntity> getListaIncidente() {
        log.info("init getListaIncidente");
        List<ItemcheqEntity> listItemCheq = new ArrayList<>();
        var list = itemcheIRepository.getListItemcheBYCodigo(paraoperIService.getParaCodi(Constantes.PARAOPER_LISTA_CHQUEO_INCIDENTES));
        list.forEach(itemcheq -> listItemCheq.add(ItemcheqEntity.builder()
                .itemcheqPK(itemcheq.getItemcheqPK())
                .itchdesc(itemcheq.getItchdesc())
                .itchsecu(itemcheq.getItchsecu())
                .itchtifi("N")
                .build()));
        return listItemCheq;
    }

}
