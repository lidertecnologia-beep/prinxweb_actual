package com.smarttmt.paraoper;

import com.smarttmt.utilities.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParaoperService implements ParaoperIService {

    Logger logger = LoggerFactory.getLogger(ParaoperService.class);

    protected IParaoperRepository iParaoperRepository;

    @Autowired
    public ParaoperService(IParaoperRepository iParaoperRepository) {
        this.iParaoperRepository = iParaoperRepository;
    }

    @Override
    public String getIdTokeWs() {
        return iParaoperRepository.getReferenceById(Constantes.PARAOPER_ID_TOKEN).getParacova();
    }

    @Override
    public String getParaCodi(String codigo) {
        logger.info("init getParaCodi codigo:{}", codigo);
        if (codigo != null && !codigo.isEmpty()) {
            return iParaoperRepository.getReferenceById(codigo).getParacova();
        }
        return null;
    }
}
