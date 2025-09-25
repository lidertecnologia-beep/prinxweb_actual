package com.smarttmt.solucion;

import com.smarttmt.enums.EnumIncidente;
import com.smarttmt.excepciones.RecursoNoEncontradoException;
import com.smarttmt.itemcheq.Itemcheq;
import com.smarttmt.paraoper.ParaoperIService;
import com.smarttmt.requerim.EntityVwRequerimSinRelaciones;
import com.smarttmt.requerim.IRepositoryRequerimSinRelaciones;
import com.smarttmt.tiporequ.TiporequEntity;
import com.smarttmt.tiporequ.TiporequIService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
public class TestSolucion {

    @Autowired
    SolucionIService solucionIService;

    @SpyBean
    SolucionIService solucionIServiceSpy;

    @MockBean
    SolucionIRepository solucionIRepository;

    @MockBean
    IRepositoryRequerimSinRelaciones iRepositoryRequerimSinRelaciones;

    @MockBean
    ParaoperIService iServiceGenericParaoper;

    @MockBean
    TiporequIService tiporequIService;

    @DisplayName("Test solucion para validar que la implemetacion funciona, es valida")
    @Test
    void testVaslidaImplementacionGetSolucionRecordDTO() {
        SolucionRecordDTO srdto = solucionIService.getSolucionRecordDTO("20080455");
        Assertions.assertEquals(20080455, srdto.solucodi());
    }

    @DisplayName("Test solucion para validar que la implemetacion funciona, es valida")
    @Test
    void testGetSolucionRecordDTOValidaRetornoNull() {
        SolucionRecordDTO srdto = solucionIService.getSolucionRecordDTO("1123111");
        Assertions.assertEquals(null, srdto);
    }

    @DisplayName("Test metodo SolucionRecordDTO clase solucionIService paso numero solicitud nulo")
    @Test
    void testtGetSolucionRecordDTOPasandoValoNulo() {
        SolucionRecordDTO srdto = solucionIService.getSolucionRecordDTO(null);
        Assertions.assertEquals(null, srdto);
    }

    @Test
    void updateSolucion() {

     /*   //GIVE
        SolucionEntity solucionNulo = null;

        EntityVwRequerimSinRelaciones requerimVacio = new EntityVwRequerimSinRelaciones();
        requerimVacio.setRequcodi(1222);

        EntityVwRequerimSinRelaciones requerimExiste = new EntityVwRequerimSinRelaciones();
        requerimExiste.setRequcodi(20239999);
        requerimExiste.setRequprio(0);
        requerimExiste.setRequprod("IMP");
        requerimExiste.setRequobpr("ISIM");
        requerimExiste.setRequtire(".");
        requerimExiste.setRequdeta("Detalle anterior");
        List<Itemcheq> list = List.of(new Itemcheq("01", "", "", ""));
        requerimExiste.setListItemCheq(list);

        Optional<EntityVwRequerimSinRelaciones> optionalRequerimUpdate = Optional.of(requerimExiste);

        SolucionEntity solucionEditar = new SolucionEntity();
        solucionEditar.setSolucodi(requerimExiste.getRequcodi());
        solucionEditar.setSoluprob("Detalle actualizado");
        String datoTire = "TIRE";
        String datoTrde = "TRDE";
        solucionEditar.setSolutire(datoTire);
        solucionEditar.setSolutipo(datoTrde);

        TiporequEntity tiporequ = new TiporequEntity();


        //WHEN
        when(iRepositoryRequerimSinRelaciones.findById(Integer.parseInt(requerimVacio.getRequcodi()))).thenReturn(Optional.empty());
        when(iRepositoryRequerimSinRelaciones.findById(requerimExiste.getRequcodi()))).thenReturn(optionalRequerimUpdate);
        when(solucionIRepository.findById(Integer.parseInt(requerimExiste.getRequcodi()))).thenReturn(Optional.of(solucionEditar));
        when(iServiceGenericParaoper.getParaCodi("TIRE")).thenReturn(datoTire);
        when(iServiceGenericParaoper.getParaCodi("TRDE")).thenReturn(datoTrde);
        when(tiporequIService.getClasificacionTiporequ(solucionEditar.getSolutire(), solucionEditar.getSolutipo())).thenReturn(tiporequ);
        when(solucionIServiceSpy.getRequerimToSolucion(optionalRequerimUpdate.get())).thenReturn(solucionEditar);
        when(solucionIRepository.save(any(SolucionEntity.class))).thenReturn(solucionEditar);

        solucionIService.update(requerimExiste);


        //THEN
        assertThrows(RecursoNoEncontradoException.class, () -> solucionIService.update(null));
        assertThrows(RecursoNoEncontradoException.class, () -> solucionIService.update(requerimVacio));
        //assertNotEquals(requerimExiste.getRequdeta(), solucionEditar.getSoluprob());

        verify(iRepositoryRequerimSinRelaciones).findById(Integer.parseInt(requerimVacio.getRequcodi()));
        verify(iRepositoryRequerimSinRelaciones).findById(Integer.parseInt(requerimExiste.getRequcodi()));
        *//*verify(iServiceGenericParaoper).getParaCodi("TIRE");
        verify(iServiceGenericParaoper).getParaCodi("TRDE");
        verify(tiporequIService).getClasificacionTiporequ(solucionEditar.getSolutire(), solucionEditar.getSolutipo());*/

    }

}
