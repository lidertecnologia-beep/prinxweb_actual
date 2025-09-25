package com.smarttmt.requerim;

import java.util.Date;
import java.util.List;

public interface IServiceRequerimSinRelacion<T> {

    List<T> getAlltByClienteReporte(String cliente);

    List<T> getAllRequerimRangoFechasReporte(String cliente, Date fechaInicial, Date fechaFinal);

}
