package com.smarttmt.requerim;

import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.Utilidades;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Usuario
 */
@Entity
@Table(name = "VW_REQUERIM")

@NamedStoredProcedureQuery(name = "getVwRequerim",
        procedureName = "pkgrequerim.pro_requerim_fechas_refcursor",
        resultClasses = EntityVwRequerim.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbFechInic", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbFechFina", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "rgRegistro", type = Void.class)
        })

@NamedStoredProcedureQuery(name = "getEstadisticasDetalle",
        procedureName = "pkgrequerim.pro_estados_refcursor",
        resultClasses = EntityVwRequerim.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "rgRegistro", type = Void.class)
        })

@NamedStoredProcedureQuery(name = "getTotalEstadisticasDetalle",
        procedureName = "pkgrequerim.pro_estados_paginas_totalregi",
        resultClasses = EntityVwRequerim.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbCliente", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbEstado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inRequCodi", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFech", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbRequFeco", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "sbSistema", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "inTotalRegi", type = Integer.class)
        })

public class EntityVwRequerim implements Serializable {

    @Id
    Integer requcodi;
    String requclie;
    String requprod;
    @Temporal(TemporalType.DATE)
    Date requfech;
    String requclas;
    String requtipo;
    String requdeta;
    String requdiem;
    String requdivi;
    String requnuex;
    Integer requprio;
    String requpere;
    String requpeso;
    String requsoli;
    String requmeco;
    Date requfesi;
    String requterm;
    Date requfeco;
    Date requfecp;
    Date requfeso;
    String requesta;
    String requperi;
    String requresp;
    Date requfeer;
    String requobpr;
    String requrepr;
    Date requfeip;
    Date requfefp;
    String requobse;
    String requacti;
    Integer requproy;
    Integer requsecu;
    String requprog;
    String requvibu;
    Date requfevb;
    String requrech;
    Date requfere;
    String requcaus;
    String requprue;
    Integer requcaso;
    String requcrit;
    String requorig;
    Integer requmapr;
    Integer requmaps;
    Integer requmase;
    String requcont;
    String requtere;
    String requobco;
    String requfact;
    Integer requsoco;
    String requrele;
    Date requfelc;
    Date requfelp;
    String requdest;
    String requcomp;
    Integer requhoes;
    Integer requhore;
    String requarch;
    String requcicl;
    Date requfecc;
    String requdigi;
    String requpers;
    String requdevo;
    Date requfede;
    String requcade;
    String requdede;
    Integer requacpr;
    Integer requacnu;
    Integer requacse;
    Integer requporc;
    String requcali;
    Integer requpres;
    Integer requpese;
    String requlaps;
    String requclso;
    Integer requvweb;
    String requpaco;
    String requanul;
    Integer requnupr;
    String requrese;
    String requproc;
    String requsupr;
    String requobca;
    String requcaan;
    Date requfean;
    String requcoco;
    String requclac;
    Integer requties;
    String requmeso;
    Integer requfuen;
    String requmodu;
    String requexte;
    String requnore;
    String requtire;
    String requtise;
    String fechrequ;
    String requferr;
    String fechlipr;
    String fechcomp;
    String fechcopr;
    String fechsolu;
    String fechanul;
    String proddesc;
    String tercdesc;
    String tiredesc;
    String clredesc;
    String nombresp;
    String nombprog;
    String nombserv;
    String prprdepr;
    String prprdesp;
    String estadesc;
    String modudesc;
    String numesema;
    String nombmes;

    public Integer getRequcodi() {
        return requcodi;
    }

    public void setRequcodi(Integer requcodi) {
        this.requcodi = requcodi;
    }

    public String getRequclie() {
        return requclie;
    }

    public void setRequclie(String requclie) {
        this.requclie = requclie;
    }

    public String getRequprod() {
        return requprod;
    }

    public void setRequprod(String requprod) {
        this.requprod = requprod;
    }

    public Date getRequfech() {
        return requfech;
    }

    public void setRequfech(Date requfech) {
        this.requfech = requfech;
    }

    public String getRequclas() {
        return requclas;
    }

    public void setRequclas(String requclas) {
        this.requclas = requclas;
    }

    public String getRequtipo() {
        return requtipo;
    }

    public void setRequtipo(String requtipo) {
        this.requtipo = requtipo;
    }

    public String getRequdeta() {
        return requdeta;
    }

    public void setRequdeta(String requdeta) {
        this.requdeta = requdeta;
    }

    public String getRequdiem() {
        return requdiem;
    }

    public void setRequdiem(String requdiem) {
        this.requdiem = requdiem;
    }

    public String getRequdivi() {
        return requdivi;
    }

    public void setRequdivi(String requdivi) {
        this.requdivi = requdivi;
    }

    public String getRequnuex() {
        return requnuex;
    }

    public void setRequnuex(String requnuex) {
        this.requnuex = requnuex;
    }

    public Integer getRequprio() {
        return requprio;
    }

    public void setRequprio(Integer requprio) {
        this.requprio = requprio;
    }

    public String getRequpere() {
        return requpere;
    }

    public void setRequpere(String requpere) {
        this.requpere = requpere;
    }

    public String getRequpeso() {
        return requpeso;
    }

    public void setRequpeso(String requpeso) {
        this.requpeso = requpeso;
    }

    public String getRequsoli() {
        return requsoli;
    }

    public void setRequsoli(String requsoli) {
        this.requsoli = requsoli;
    }

    public String getRequmeco() {
        return requmeco;
    }

    public void setRequmeco(String requmeco) {
        this.requmeco = requmeco;
    }

    public Date getRequfesi() {
        return requfesi;
    }

    public void setRequfesi(Date requfesi) {
        this.requfesi = requfesi;
    }

    public String getRequterm() {
        return requterm;
    }

    public void setRequterm(String requterm) {
        this.requterm = requterm;
    }

    public Date getRequfeco() {
        return requfeco;
    }

    public void setRequfeco(Date requfeco) {
        this.requfeco = requfeco;
    }

    public Date getRequfecp() {
        return requfecp;
    }

    public void setRequfecp(Date requfecp) {
        this.requfecp = requfecp;
    }

    public Date getRequfeso() {
        return requfeso;
    }

    public void setRequfeso(Date requfeso) {
        this.requfeso = requfeso;
    }

    public String getRequesta() {
        return requesta;
    }

    public void setRequesta(String requesta) {
        this.requesta = requesta;
    }

    public String getRequperi() {
        return requperi;
    }

    public void setRequperi(String requperi) {
        this.requperi = requperi;
    }

    public String getRequresp() {
        return requresp;
    }

    public void setRequresp(String requresp) {
        this.requresp = requresp;
    }

    public Date getRequfeer() {
        return requfeer;
    }

    public void setRequfeer(Date requfeer) {
        this.requfeer = requfeer;
    }

    public String getRequobpr() {
        return requobpr;
    }

    public void setRequobpr(String requobpr) {
        this.requobpr = requobpr;
    }

    public String getRequrepr() {
        return requrepr;
    }

    public void setRequrepr(String requrepr) {
        this.requrepr = requrepr;
    }

    public Date getRequfeip() {
        return requfeip;
    }

    public void setRequfeip(Date requfeip) {
        this.requfeip = requfeip;
    }

    public Date getRequfefp() {
        return requfefp;
    }

    public void setRequfefp(Date requfefp) {
        this.requfefp = requfefp;
    }

    public String getRequobse() {
        return requobse;
    }

    public void setRequobse(String requobse) {
        this.requobse = requobse;
    }

    public String getRequacti() {
        return requacti;
    }

    public void setRequacti(String requacti) {
        this.requacti = requacti;
    }

    public Integer getRequproy() {
        return requproy;
    }

    public void setRequproy(Integer requproy) {
        this.requproy = requproy;
    }

    public Integer getRequsecu() {
        return requsecu;
    }

    public void setRequsecu(Integer requsecu) {
        this.requsecu = requsecu;
    }

    public String getRequprog() {
        return requprog;
    }

    public void setRequprog(String requprog) {
        this.requprog = requprog;
    }

    public String getRequvibu() {
        return requvibu;
    }

    public void setRequvibu(String requvibu) {
        this.requvibu = requvibu;
    }

    public Date getRequfevb() {
        return requfevb;
    }

    public void setRequfevb(Date requfevb) {
        this.requfevb = requfevb;
    }

    public String getRequrech() {
        return requrech;
    }

    public void setRequrech(String requrech) {
        this.requrech = requrech;
    }

    public Date getRequfere() {
        return requfere;
    }

    public void setRequfere(Date requfere) {
        this.requfere = requfere;
    }

    public String getRequcaus() {
        return requcaus;
    }

    public void setRequcaus(String requcaus) {
        this.requcaus = requcaus;
    }

    public String getRequprue() {
        return requprue;
    }

    public void setRequprue(String requprue) {
        this.requprue = requprue;
    }

    public Integer getRequcaso() {
        return requcaso;
    }

    public void setRequcaso(Integer requcaso) {
        this.requcaso = requcaso;
    }

    public String getRequcrit() {
        return requcrit;
    }

    public void setRequcrit(String requcrit) {
        this.requcrit = requcrit;
    }

    public String getRequorig() {
        return requorig;
    }

    public void setRequorig(String requorig) {
        this.requorig = requorig;
    }

    public Integer getRequmapr() {
        return requmapr;
    }

    public void setRequmapr(Integer requmapr) {
        this.requmapr = requmapr;
    }

    public Integer getRequmaps() {
        return requmaps;
    }

    public void setRequmaps(Integer requmaps) {
        this.requmaps = requmaps;
    }

    public Integer getRequmase() {
        return requmase;
    }

    public void setRequmase(Integer requmase) {
        this.requmase = requmase;
    }

    public String getRequcont() {
        return requcont;
    }

    public void setRequcont(String requcont) {
        this.requcont = requcont;
    }

    public String getRequtere() {
        return requtere;
    }

    public void setRequtere(String requtere) {
        this.requtere = requtere;
    }

    public String getRequobco() {
        return requobco;
    }

    public void setRequobco(String requobco) {
        this.requobco = requobco;
    }

    public String getRequfact() {
        return requfact;
    }

    public void setRequfact(String requfact) {
        this.requfact = requfact;
    }

    public Integer getRequsoco() {
        return requsoco;
    }

    public void setRequsoco(Integer requsoco) {
        this.requsoco = requsoco;
    }

    public String getRequrele() {
        return requrele;
    }

    public void setRequrele(String requrele) {
        this.requrele = requrele;
    }

    public Date getRequfelc() {
        return requfelc;
    }

    public void setRequfelc(Date requfelc) {
        this.requfelc = requfelc;
    }

    public Date getRequfelp() {
        return requfelp;
    }

    public void setRequfelp(Date requfelp) {
        this.requfelp = requfelp;
    }

    public String getRequdest() {
        return requdest;
    }

    public void setRequdest(String requdest) {
        this.requdest = requdest;
    }

    public String getRequcomp() {
        return requcomp;
    }

    public void setRequcomp(String requcomp) {
        this.requcomp = requcomp;
    }

    public Integer getRequhoes() {
        return requhoes;
    }

    public void setRequhoes(Integer requhoes) {
        this.requhoes = requhoes;
    }

    public Integer getRequhore() {
        return requhore;
    }

    public void setRequhore(Integer requhore) {
        this.requhore = requhore;
    }

    public String getRequarch() {
        return requarch;
    }

    public void setRequarch(String requarch) {
        this.requarch = requarch;
    }

    public String getRequcicl() {
        return requcicl;
    }

    public void setRequcicl(String requcicl) {
        this.requcicl = requcicl;
    }

    public Date getRequfecc() {
        return requfecc;
    }

    public void setRequfecc(Date requfecc) {
        this.requfecc = requfecc;
    }

    public String getRequdigi() {
        return requdigi;
    }

    public void setRequdigi(String requdigi) {
        this.requdigi = requdigi;
    }

    public String getRequpers() {
        return requpers;
    }

    public void setRequpers(String requpers) {
        this.requpers = requpers;
    }

    public String getRequdevo() {
        return requdevo;
    }

    public void setRequdevo(String requdevo) {
        this.requdevo = requdevo;
    }

    public Date getRequfede() {
        return requfede;
    }

    public void setRequfede(Date requfede) {
        this.requfede = requfede;
    }

    public String getRequcade() {
        return requcade;
    }

    public void setRequcade(String requcade) {
        this.requcade = requcade;
    }

    public String getRequdede() {
        return requdede;
    }

    public void setRequdede(String requdede) {
        this.requdede = requdede;
    }

    public Integer getRequacpr() {
        return requacpr;
    }

    public void setRequacpr(Integer requacpr) {
        this.requacpr = requacpr;
    }

    public Integer getRequacnu() {
        return requacnu;
    }

    public void setRequacnu(Integer requacnu) {
        this.requacnu = requacnu;
    }

    public Integer getRequacse() {
        return requacse;
    }

    public void setRequacse(Integer requacse) {
        this.requacse = requacse;
    }

    public Integer getRequporc() {
        return requporc;
    }

    public void setRequporc(Integer requporc) {
        this.requporc = requporc;
    }

    public String getRequcali() {
        return requcali;
    }

    public void setRequcali(String requcali) {
        this.requcali = requcali;
    }

    public Integer getRequpres() {
        return requpres;
    }

    public void setRequpres(Integer requpres) {
        this.requpres = requpres;
    }

    public Integer getRequpese() {
        return requpese;
    }

    public void setRequpese(Integer requpese) {
        this.requpese = requpese;
    }

    public String getRequlaps() {
        return requlaps;
    }

    public void setRequlaps(String requlaps) {
        this.requlaps = requlaps;
    }

    public String getRequclso() {
        return requclso;
    }

    public void setRequclso(String requclso) {
        this.requclso = requclso;
    }

    public Integer getRequvweb() {
        return requvweb;
    }

    public void setRequvweb(Integer requvweb) {
        this.requvweb = requvweb;
    }

    public String getRequpaco() {
        return requpaco;
    }

    public void setRequpaco(String requpaco) {
        this.requpaco = requpaco;
    }

    public String getRequanul() {
        return requanul;
    }

    public void setRequanul(String requanul) {
        this.requanul = requanul;
    }

    public Integer getRequnupr() {
        return requnupr;
    }

    public void setRequnupr(Integer requnupr) {
        this.requnupr = requnupr;
    }

    public String getRequrese() {
        return requrese;
    }

    public void setRequrese(String requrese) {
        this.requrese = requrese;
    }

    public String getRequproc() {
        return requproc;
    }

    public void setRequproc(String requproc) {
        this.requproc = requproc;
    }

    public String getRequsupr() {
        return requsupr;
    }

    public void setRequsupr(String requsupr) {
        this.requsupr = requsupr;
    }

    public String getRequobca() {
        return requobca;
    }

    public void setRequobca(String requobca) {
        this.requobca = requobca;
    }

    public String getRequcaan() {
        return requcaan;
    }

    public void setRequcaan(String requcaan) {
        this.requcaan = requcaan;
    }

    public Date getRequfean() {
        return requfean;
    }

    public void setRequfean(Date requfean) {
        this.requfean = requfean;
    }

    public String getRequcoco() {
        return requcoco;
    }

    public void setRequcoco(String requcoco) {
        this.requcoco = requcoco;
    }

    public String getRequclac() {
        return requclac;
    }

    public void setRequclac(String requclac) {
        this.requclac = requclac;
    }

    public Integer getRequties() {
        return requties;
    }

    public void setRequties(Integer requties) {
        this.requties = requties;
    }

    public String getRequmeso() {
        return requmeso;
    }

    public void setRequmeso(String requmeso) {
        this.requmeso = requmeso;
    }

    public Integer getRequfuen() {
        return requfuen;
    }

    public void setRequfuen(Integer requfuen) {
        this.requfuen = requfuen;
    }

    public String getRequmodu() {
        return requmodu;
    }

    public void setRequmodu(String requmodu) {
        this.requmodu = requmodu;
    }

    public String getRequexte() {
        return requexte;
    }

    public void setRequexte(String requexte) {
        this.requexte = requexte;
    }

    public String getRequnore() {
        return requnore;
    }

    public void setRequnore(String requnore) {
        this.requnore = requnore;
    }

    public String getRequtire() {
        return requtire;
    }

    public void setRequtire(String requtire) {
        this.requtire = requtire;
    }

    public String getRequtise() {
        return requtise;
    }

    public void setRequtise(String requtise) {
        this.requtise = requtise;
    }

    public String getFechrequ() {
        return fechrequ;
    }

    public void setFechrequ(String fechrequ) {
        this.fechrequ = fechrequ;
    }

    public String getRequferr() {
        return requferr;
    }

    public void setRequferr(String requferr) {
        this.requferr = requferr;
    }

    public String getFechlipr() {
        return fechlipr;
    }

    public void setFechlipr(String fechlipr) {
        this.fechlipr = fechlipr;
    }

    public String getFechcomp() {
        return fechcomp;
    }

    public void setFechcomp(String fechcomp) {
        this.fechcomp = fechcomp;
    }

    public String getFechcopr() {
        return fechcopr;
    }

    public void setFechcopr(String fechcopr) {
        this.fechcopr = fechcopr;
    }

    public String getFechsolu() {
        return fechsolu;
    }

    public void setFechsolu(String fechsolu) {
        this.fechsolu = fechsolu;
    }

    public String getFechanul() {
        return fechanul;
    }

    public void setFechanul(String fechanul) {
        this.fechanul = fechanul;
    }

    public String getProddesc() {
        return proddesc;
    }

    public void setProddesc(String proddesc) {
        this.proddesc = proddesc;
    }

    public String getTercdesc() {
        return tercdesc;
    }

    public void setTercdesc(String tercdesc) {
        this.tercdesc = tercdesc;
    }

    public String getTiredesc() {
        return tiredesc;
    }

    public void setTiredesc(String tiredesc) {
        this.tiredesc = tiredesc;
    }

    public String getClredesc() {
        return clredesc;
    }

    public void setClredesc(String clredesc) {
        this.clredesc = clredesc;
    }

    public String getNombresp() {
        return nombresp;
    }

    public void setNombresp(String nombresp) {
        this.nombresp = nombresp;
    }

    public String getNombprog() {
        return nombprog;
    }

    public void setNombprog(String nombprog) {
        this.nombprog = nombprog;
    }

    public String getNombserv() {
        return nombserv;
    }

    public void setNombserv(String nombserv) {
        this.nombserv = nombserv;
    }

    public String getPrprdepr() {
        return prprdepr;
    }

    public void setPrprdepr(String prprdepr) {
        this.prprdepr = prprdepr;
    }

    public String getPrprdesp() {
        return prprdesp;
    }

    public void setPrprdesp(String prprdesp) {
        this.prprdesp = prprdesp;
    }

    public String getEstadesc() {
        return Utilidades.getEstadoPendientes().contains(requesta) ? Constantes.DESC_ESTADO_PENDIENTE : estadesc;
    }

    public void setEstadesc(String estadesc) {
        this.estadesc = estadesc;
    }

    public String getModudesc() {
        return modudesc;
    }

    public void setModudesc(String modudesc) {
        this.modudesc = modudesc;
    }

    public String getNumesema() {
        return numesema;
    }

    public void setNumesema(String numesema) {
        this.numesema = numesema;
    }

    public String getNombmes() {
        return nombmes;
    }

    public void setNombmes(String nombmes) {
        this.nombmes = nombmes;
    }

    public String getFechsoli() {
        return Utilidades.getSysdate("dd-MM-yyyy", requfech);
    }

    public String getFechfeco() {
        return Utilidades.getSysdate("dd-MM-yyyy", requfeco);
    }

}

