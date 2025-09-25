package com.smarttmt.solucion;

import com.smarttmt.requerim.IServiceRequerim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SolucionDTOMapper implements Function<SolucionEntity,SolucionRecordDTO> {

    private final IServiceRequerim iServiceRequerim;

    @Autowired
    public SolucionDTOMapper(
            IServiceRequerim iServiceRequerim) {
        this.iServiceRequerim = iServiceRequerim;
    }

    @Override
    public SolucionRecordDTO apply(SolucionEntity solucion) {
        return new SolucionRecordDTO(
                solucion.getSolucodi(),
                solucion.getSoluprob(),
                solucion.getSolufeso(),
                solucion.getSoluesta(),
                solucion.getSolupere(),
                solucion.getSolupeso(),
                solucion.getSoluclie(),
                solucion.getSolumeco(),
                solucion.getSolutipo(),
                solucion.getSolusoli(),
                solucion.getSolutire(),
                solucion.getSoluprio(),
                solucion.getSoluprod(),
                solucion.getSoluobje(),
                solucion.getSolupers(),
                solucion.getSolusolu(),
                solucion.getSoluclas(),
                iServiceRequerim.requerimPendiente(solucion.getSoluesta()),
                solucion.getSoluprioridaAtencion()
        );
    }
}
