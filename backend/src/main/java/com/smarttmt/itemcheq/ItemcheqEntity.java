package com.smarttmt.itemcheq;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEMCHEQ")
public class ItemcheqEntity {

    @EmbeddedId
    ItemcheqPK itemcheqPK;
    String itchdesc;
    Integer itchsecu;
    String itchtifi;
    String itchdeta;
    String itchcopa;
    Integer itchvweb;

}
