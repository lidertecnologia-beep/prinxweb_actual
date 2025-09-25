package com.smarttmt.mantprod;

import java.util.List;

public interface MantprodIService {
    List<MantprodEntity> getListMantprod(String cliente, Integer numeroRequerim);
}
