package com.smarttmt.solucion;

import com.smarttmt.enums.EnumIncidente;
import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.requerim.EntityVwRequerimSinRelaciones;
import com.smarttmt.requerim.IRepositoryRequerimSinRelaciones;
import com.smarttmt.tiporequ.TiporequEntity;
import com.smarttmt.tiporequ.TiporequIService;
import com.smarttmt.utilities.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class SolucionService implements SolucionIService {

    private final SolucionIRepository solucionIRepository;
    private final SolucionDTOMapper solucionDTOMapper;
    private final ParaoperIService iServiceGenericParaoper;
    private final IRepositoryRequerimSinRelaciones iRepositoryRequerimSinRelaciones;
    private final TiporequIService tiporequIService;

    @Autowired
    public SolucionService(
            SolucionIRepository solucionIRepository,
            SolucionDTOMapper solucionDTOMapper,
            ParaoperIService iServiceGenericParaoper,
            IRepositoryRequerimSinRelaciones iRepositoryRequerimSinRelaciones,
            TiporequIService tiporequIService) {
        this.solucionIRepository = solucionIRepository;
        this.solucionDTOMapper = solucionDTOMapper;
        this.iServiceGenericParaoper = iServiceGenericParaoper;
        this.iRepositoryRequerimSinRelaciones = iRepositoryRequerimSinRelaciones;
        this.tiporequIService = tiporequIService;
    }


    @Override
    public SolucionRecordDTO getSolucionRecordDTO(String numeroRequerim) {
        log.info("init getMapSolucion numeroRequerim:{}", numeroRequerim);
        Optional<SolucionEntity> optionalSolucion = solucionIRepository.findById(Integer.valueOf(numeroRequerim));
        if (optionalSolucion.isPresent()) {
            SolucionEntity solucion = optionalSolucion.get();
            String tipoRequeIncidenteDefecto = iServiceGenericParaoper.getParaCodi("TRDEINCI");
            solucion.setSoluprioridaAtencion(tipoRequeIncidenteDefecto.equals(solucion.getSolutipo()) ? Constantes.CODIGCALIFSERVC1 : Constantes.CODIGCALIFSERVC5);
            return solucionDTOMapper.apply(solucion);
        }
        return null;
    }

    public SolucionEntity getRequerimToSolucion(EntityVwRequerimSinRelaciones requerim) {

        log.info("init getCargaRequerimToSolucion");

        if (Optional.ofNullable(requerim).isEmpty()) {
            return null;
        }

        SolucionEntity solucion = solucionIRepository.findById(requerim.getRequcodi())
                .orElseThrow(() -> new IllegalArgumentException(Constantes.MENSAJE_REGISTRO_SOLUCION_NO_EXISTE));

        solucion.setSoluprio(Optional.of(requerim.getRequprio()).orElseThrow(() -> new IllegalArgumentException("Campo requprio es requerido")));
        solucion.setSoluprod(Optional.of(requerim.getRequprod()).orElseThrow(() -> new IllegalArgumentException("Campo requprod es requerido")));
        solucion.setSoluobje(Optional.of(requerim.getRequobpr()).orElseThrow(() -> new IllegalArgumentException("Campo requobpr es requerido")));
        solucion.setSoluprob(Optional.of(requerim.getRequdeta()).orElseThrow(() -> new IllegalArgumentException("Campo requdeta es requerido")));
        solucion.setSolutire(String.valueOf(Optional.of(requerim.getRequtire()).orElseThrow(() -> new IllegalArgumentException("Campo Requtire es requerido"))));
        solucion.setSolutipo(requerim.getRequtire());

        //Se valida si es un incidente
        boolean boIncidente = requerim.getListItemCheq().stream().anyMatch(itemcheq -> EnumIncidente.isTipoInicidente(itemcheq.itchcodi()));

        if (boIncidente) {
            solucion.setSolutire(iServiceGenericParaoper.getParaCodi("TIREINCI"));
            solucion.setSolutipo(iServiceGenericParaoper.getParaCodi("TRDEINCI"));
            TiporequEntity tiporequ = tiporequIService.getClasificacionTiporequ(solucion.getSolutire(), solucion.getSolutipo());
            if (Optional.ofNullable(tiporequ).isPresent()) {
                solucion.setSoluclas(tiporequ.getClasrequ().getClrecodi());
            }
        }

        return solucion;
    }


    @Transactional
    @Override
    public void update(EntityVwRequerimSinRelaciones requerim) {
        log.info("init update solucion:{}", requerim);

        if (Optional.ofNullable(requerim).isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_ENTIDAD_REQUERIM_NULA);
        }

        Optional<EntityVwRequerimSinRelaciones> optionalRequerim = iRepositoryRequerimSinRelaciones.findById(requerim.getRequcodi());

        if (optionalRequerim.isEmpty()) {
            throw new RecursoNoEncontradoException(Constantes.MENSAJE_REGISTRO_SOLUCION_NO_EXISTE);
        }

        solucionIRepository.save(getRequerimToSolucion(requerim));

    }


}
