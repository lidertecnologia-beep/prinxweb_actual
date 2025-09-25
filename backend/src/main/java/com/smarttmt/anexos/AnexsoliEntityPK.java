package com.smarttmt.anexos;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class AnexsoliEntityPK implements Serializable {
    Integer ansosolu;
    Integer ansosecu;
    String ansoarch;
}
