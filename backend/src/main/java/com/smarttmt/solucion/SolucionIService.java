package com.smarttmt.solucion;


import com.smarttmt.requerim.EntityVwRequerimSinRelaciones;

public interface SolucionIService {

    SolucionRecordDTO getSolucionRecordDTO(String numeroRequerim);

    void update(EntityVwRequerimSinRelaciones requerim);

    SolucionEntity getRequerimToSolucion(EntityVwRequerimSinRelaciones requerim);
}
