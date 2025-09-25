package com.smarttmt.tiporequ;

import com.smarttmt.modelos.ListaValoresModel;

import java.util.List;

public interface TiporequIService {
    TiporequEntity getClasificacionTiporequ(String tiretire, String tirecodi);
    List<ListaValoresModel> getListaValores();
}
