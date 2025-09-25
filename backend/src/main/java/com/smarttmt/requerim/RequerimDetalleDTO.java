package com.smarttmt.requerim;

import com.smarttmt.anexos.Anexsoli;
import com.smarttmt.mantprod.MantprodEntity;

import java.util.List;

public record RequerimDetalleDTO(
        List<MantprodEntity> listMantprod,
        List<Anexsoli> listAnexos
) {
}
