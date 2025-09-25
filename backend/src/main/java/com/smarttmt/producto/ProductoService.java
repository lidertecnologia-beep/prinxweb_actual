package com.smarttmt.producto;


import com.smarttmt.modelos.ListaValoresModel;
import com.smarttmt.tiporequ.TiporequEntity;
import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.TokenAutenticacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class ProductoService {

    private final  ProductoIRepository productoIRepository;

    public List<ListaValoresModel> getListaValores(String cliente) {
        log.info("init getListaValores cliente:{}",cliente);
        List<ListaValoresModel> listProductos = new ArrayList<>();
        List<Object[]> lisProducto = productoIRepository.findProductoByCliente(cliente,Constantes.ESTAACTI);
        log.info("findAllProducto valida entrar al forEach");
        lisProducto.forEach(product -> {
            log.info("forEach" + product[0] + " " + product[1]);
            listProductos.add(new ListaValoresModel(String.valueOf(product[0]), String.valueOf(product[1])));
        });
        return listProductos;
    }
}
