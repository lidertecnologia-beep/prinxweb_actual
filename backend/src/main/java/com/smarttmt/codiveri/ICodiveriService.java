package com.smarttmt.codiveri;

public interface ICodiveriService<T> {

    String crearCodiveri(String us, String email);
    String validarCodiveri(String codigo,String tercero,String us, String email);

}
