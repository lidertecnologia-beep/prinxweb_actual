package com.smarttmt.estado;

import java.io.IOException;
import java.util.List;

public interface EstadoIService {

    List<EstadoModificadosRecord> getListEstadoModificado();

    List getListEstadosDetalle(String cliente) throws IOException;

}
