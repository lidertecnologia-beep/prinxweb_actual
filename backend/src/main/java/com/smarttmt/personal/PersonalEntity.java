package com.smarttmt.personal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PERSONAL", schema = "PRINX")
public class PersonalEntity {
    
    @Id
    String perscodi;
    String persnomb;
    String persapel;
    String persarea;
    String perscarg;
    String persesta;
    Integer perssala;
    Integer perssaco;
    String persdiem;
    String persdivi;
    String persdoid;
    String perstipe;
    String persusua;
    String persclac;
    String perstele;
    String persciud;
    String persdire;
    String persprac;
    String persdest;
    String perssepe;
    String perstelm;
    String perscaca;
    String persproy;
    String persgebi;
    Integer persvweb;
    String persemai;
    Integer perspodd;
    Integer perspodi;
    String persnifo;
    String persnico;
    String persnica;
    String persnipr;
    String persterc;



}
