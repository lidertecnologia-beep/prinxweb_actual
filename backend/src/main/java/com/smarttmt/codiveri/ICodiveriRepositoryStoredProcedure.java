package com.smarttmt.codiveri;

public interface ICodiveriRepositoryStoredProcedure {

    String crearCodiveri(String param);

    String validarCodiveri(String codigo, String tercero, String us, String email);

}
