package com.smarttmt.mantprod;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.utilities.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MantprodService implements MantprodIService {

    private final MantprodIRepository mantprodIRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MantprodEntity> getListMantprod(String cliente, Integer numeroRequerim) {
        log.info("init getListMantprod cliente:{}  numeroRequerim:{}", cliente, numeroRequerim);
        if (cliente == null || numeroRequerim == null) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_MANTPROD_NULO);
        }
        return mantprodIRepository.getListMantprod(cliente, numeroRequerim);
    }

}
