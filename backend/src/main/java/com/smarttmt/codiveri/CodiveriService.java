package com.smarttmt.codiveri;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class CodiveriService implements ICodiveriService<CodiveriService> {

    private final ICodiveriRepositoryStoredProcedure iCodiveriRepositoryStoredProcedure;
    private final ICodiveriRepository iCodiveriRepository;

    @Override
    @Transactional(readOnly = true)
    public String crearCodiveri(String us, String email) {
        log.info("init crearCodiveri us:{} email:{}", us, email);
        StringBuilder sb = new StringBuilder();
        sb.append("COVEUSSI:").append(us).append("|");
        sb.append("COVEMAIL:").append(email);
        return iCodiveriRepositoryStoredProcedure.crearCodiveri(sb.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public String validarCodiveri(String codigo, String tercero, String us, String email) {
        log.info("init validarCodiveri codigo:{} tercero:{} us:{} email:{}", codigo, tercero, us, email);
        return iCodiveriRepositoryStoredProcedure.validarCodiveri(codigo, tercero, us, email);
    }

}
