package com.smarttmt.usuabada;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "USUABADA")

public class UsuabadaEntity {

    @Id
    String usbdcodi;
    String usbddesc;
    Integer usbdvweb;
    String usbdpass;
    String usbdesta;
    Integer usbdcaif;
    Date usbdfeui;
    Date usbdfucc;
    String usbdterm;
    String usbddigi;
    Date usbdfesi;

}
