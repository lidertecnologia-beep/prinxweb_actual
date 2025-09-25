package com.smarttmt.objeprod;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OBJEPROD")

public class ObjeprodEntity {

    @EmbeddedId
    ObjeprodPK objeprodPK;
    String obprdesc;
    String obprdeta;
    String obpresta;
    String obprauto;
    Date obprfech;
    Date obprfeuc;
    String obprclas;
    String obprcali;
    String obprconc;
    String obprmodu;
    Integer obprvweb;


}
