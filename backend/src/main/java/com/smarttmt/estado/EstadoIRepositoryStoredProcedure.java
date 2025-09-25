package com.smarttmt.estado;

import java.io.IOException;
import java.util.List;

public interface EstadoIRepositoryStoredProcedure {
    List getListEstadosDetalle(String cliente);
}
