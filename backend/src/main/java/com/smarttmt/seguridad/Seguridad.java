package com.smarttmt.seguridad;

import com.smarttmt.utilities.Constantes;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class Seguridad implements ISeguridad {

    private final ISeguridadRepositoryStoredProcedure iSeguridadRepositoryStoredProcedure;

    @Override
    public String cambiarClave(String us, String email, String pw) {
        log.info("init cambiarClave us:{} email:{}", us, email);
        return  (!StringUtils.isEmpty(us) && !StringUtils.isEmpty(email) && !StringUtils.isEmpty(pw))
                ? iSeguridadRepositoryStoredProcedure.cambiarClave(us, email, pw)
                : Constantes.VALOR_RETORNO_FALSE;
    }
}
