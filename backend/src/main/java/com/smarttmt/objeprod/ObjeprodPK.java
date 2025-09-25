package com.smarttmt.objeprod;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class ObjeprodPK implements Serializable {
    String obprprod;
    String obprobje;
}
