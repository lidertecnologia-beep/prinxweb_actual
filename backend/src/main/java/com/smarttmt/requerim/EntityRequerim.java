package com.smarttmt.requerim;

import com.smarttmt.anexos.Anexsoli;
import com.smarttmt.mantprod.MantprodEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REQUERIM", schema = "PRINX", catalog = "")
public class EntityRequerim {

    //@OneToMany(mappedBy="maprrequ")
    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "maprrequ")

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "maprrequ")
    private List<MantprodEntity> detalleRequerimiento;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ansosolu")
    private List<Anexsoli> listAnexos;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "REQUCODI")
    private int requcodi;
    @Basic
    @Column(name = "REQUCLIE")
    private String requclie;
    @Basic
    @Column(name = "REQUPROD")
    private String requprod;
    @Basic
    @Column(name = "REQUFECH")
    private Date requfech;
    @Basic
    @Column(name = "REQUCLAS")
    private String requclas;
    @Basic
    @Column(name = "REQUTIPO")
    private String requtipo;
    @Basic
    @Column(name = "REQUDETA")
    private String requdeta;
    @Basic
    @Column(name = "REQUDIEM")
    private String requdiem;
    @Basic
    @Column(name = "REQUDIVI")
    private String requdivi;
    @Basic
    @Column(name = "REQUNUEX")
    private String requnuex;
    @Basic
    @Column(name = "REQUPRIO")
    private byte requprio;
    @Basic
    @Column(name = "REQUPERE")
    private String requpere;
    @Basic
    @Column(name = "REQUPESO")
    private String requpeso;
    @Basic
    @Column(name = "REQUSOLI")
    private String requsoli;
    @Basic
    @Column(name = "REQUMECO")
    private String requmeco;
    @Basic
    @Column(name = "REQUFESI")
    private Date requfesi;
    @Basic
    @Column(name = "REQUTERM")
    private String requterm;
    @Basic
    @Column(name = "REQUFECO")
    private Date requfeco;
    @Basic
    @Column(name = "REQUFECP")
    private Date requfecp;
    @Basic
    @Column(name = "REQUFESO")
    private Date requfeso;
    @Basic
    @Column(name = "REQUESTA")
    private String requesta;
    @Basic
    @Column(name = "REQUPERI")
    private String requperi;
    @Basic
    @Column(name = "REQURESP")
    private String requresp;
    @Basic
    @Column(name = "REQUFEER")
    private Date requfeer;
    @Basic
    @Column(name = "REQUOBPR")
    private String requobpr;
    @Basic
    @Column(name = "REQUREPR")
    private String requrepr;
    @Basic
    @Column(name = "REQUFEIP")
    private Date requfeip;
    @Basic
    @Column(name = "REQUFEFP")
    private Date requfefp;
    @Basic
    @Column(name = "REQUOBSE")
    private String requobse;
    @Basic
    @Column(name = "REQUACTI")
    private String requacti;
    @Basic
    @Column(name = "REQUPROY")
    private Integer requproy;
    @Basic
    @Column(name = "REQUSECU")
    private Integer requsecu;
    @Basic
    @Column(name = "REQUPROG")
    private String requprog;
    @Basic
    @Column(name = "REQUVIBU")
    private String requvibu;
    @Basic
    @Column(name = "REQUFEVB")
    private Date requfevb;
    @Basic
    @Column(name = "REQURECH")
    private String requrech;
    @Basic
    @Column(name = "REQUFERE")
    private Date requfere;
    @Basic
    @Column(name = "REQUCAUS")
    private String requcaus;
    @Basic
    @Column(name = "REQUPRUE")
    private String requprue;
    @Basic
    @Column(name = "REQUCASO")
    private Short requcaso;
    @Basic
    @Column(name = "REQUCRIT")
    private String requcrit;
    @Basic
    @Column(name = "REQUORIG")
    private String requorig;
    @Basic
    @Column(name = "REQUMAPR")
    private Integer requmapr;
    @Basic
    @Column(name = "REQUMAPS")
    private Integer requmaps;
    @Basic
    @Column(name = "REQUMASE")
    private Integer requmase;
    @Basic
    @Column(name = "REQUCONT")
    private String requcont;
    @Basic
    @Column(name = "REQUTERE")
    private Integer requtere;
    @Basic
    @Column(name = "REQUOBCO")
    private String requobco;
    @Basic
    @Column(name = "REQUFACT")
    private String requfact;
    @Basic
    @Column(name = "REQUSOCO")
    private Integer requsoco;
    @Basic
    @Column(name = "REQURELE")
    private String requrele;
    @Basic
    @Column(name = "REQUFELC")
    private Date requfelc;
    @Basic
    @Column(name = "REQUFELP")
    private Date requfelp;
    @Basic
    @Column(name = "REQUDEST")
    private String requdest;
    @Basic
    @Column(name = "REQUCOMP")
    private String requcomp;
    @Basic
    @Column(name = "REQUHOES")
    private BigDecimal requhoes;
    @Basic
    @Column(name = "REQUHORE")
    private BigDecimal requhore;
    @Basic
    @Column(name = "REQUARCH")
    private String requarch;
    @Basic
    @Column(name = "REQUCICL")
    private String requcicl;
    @Basic
    @Column(name = "REQUFECC")
    private Date requfecc;
    @Basic
    @Column(name = "REQUDIGI")
    private String requdigi;
    @Basic
    @Column(name = "REQUPERS")
    private String requpers;
    @Basic
    @Column(name = "REQUDEVO")
    private String requdevo;
    @Basic
    @Column(name = "REQUFEDE")
    private Date requfede;
    @Basic
    @Column(name = "REQUCADE")
    private String requcade;
    @Basic
    @Column(name = "REQUDEDE")
    private String requdede;
    @Basic
    @Column(name = "REQUACPR")
    private Integer requacpr;
    @Basic
    @Column(name = "REQUACNU")
    private Integer requacnu;
    @Basic
    @Column(name = "REQUACSE")
    private Short requacse;
    @Basic
    @Column(name = "REQUPORC")
    private Integer requporc;
    @Basic
    @Column(name = "REQUCALI")
    private Byte requcali;
    @Basic
    @Column(name = "REQUPRES")
    private Integer requpres;
    @Basic
    @Column(name = "REQUPESE")
    private Integer requpese;
    @Basic
    @Column(name = "REQULAPS")
    private String requlaps;
    @Basic
    @Column(name = "REQUCLSO")
    private String requclso;
    @Basic
    @Column(name = "REQUVWEB")
    private Integer requvweb;
    @Basic
    @Column(name = "REQUPACO")
    private String requpaco;
    @Basic
    @Column(name = "REQUANUL")
    private String requanul;
    @Basic
    @Column(name = "REQUFEAN")
    private Date requfean;
    @Basic
    @Column(name = "REQUCOCO")
    private String requcoco;
    @Basic
    @Column(name = "REQUCLAC")
    private String requclac;
    @Basic
    @Column(name = "REQUTIES")
    private BigDecimal requties;
    @Basic
    @Column(name = "REQUMESO")
    private String requmeso;
    @Basic
    @Column(name = "REQUNUPR")
    private Short requnupr;
    @Basic
    @Column(name = "REQURESE")
    private String requrese;
    @Basic
    @Column(name = "REQUOBCA")
    private String requobca;
    @Basic
    @Column(name = "REQUPROC")
    private String requproc;
    @Basic
    @Column(name = "REQUSUPR")
    private String requsupr;
    @Basic
    @Column(name = "REQUCAAN")
    private String requcaan;
    @Basic
    @Column(name = "REQUFUEN")
    private Integer requfuen;
    @Basic
    @Column(name = "REQUMODU")
    private String requmodu;
    @Basic
    @Column(name = "REQUEXTE")
    private String requexte;
    @Basic
    @Column(name = "REQUTIRE")
    private String requtire;
    @Basic
    @Column(name = "REQUNORE")
    private String requnore;
    @Basic
    @Column(name = "REQUPROP")
    private String requprop;
    @Basic
    @Column(name = "REQUBENE")
    private String requbene;
    @Basic
    @Column(name = "REQUTITO")
    private Integer requtito;
    @Basic
    @Column(name = "REQUAPTA")
    private String requapta;
    @Basic
    @Column(name = "REQUFETA")
    private Date requfeta;
    @Basic
    @Column(name = "REQUFERR")
    private Date requferr;
    @Basic
    @Column(name = "REQUROMA")
    private String requroma;
    @Basic
    @Column(name = "REQUACNA")
    private String requacna;
    @Basic
    @Column(name = "REQUTISE")
    private String requtise;
    @Basic
    @Column(name = "REQUCMCO")
    private Integer requcmco;
    @Basic
    @Column(name = "REQUESTI")
    private BigDecimal requesti;
    @Basic
    @Column(name = "REQUORSE")
    private BigDecimal requorse;
    @Basic
    @Column(name = "REQUNUCA")
    private BigDecimal requnuca;
    @Basic
    @Column(name = "REQUNOUF")
    private String requnouf;
    @Basic
    @Column(name = "REQUAREA")
    private String requarea;
    @Basic
    @Column(name = "REQUEXTS")
    private BigDecimal requexts;
    @Basic
    @Column(name = "REQUTINC")
    private String requtinc;
    @Basic
    @Column(name = "REQUCANC")
    private BigDecimal requcanc;

    public int getRequcodi() {
        return requcodi;
    }

    public void setRequcodi(int requcodi) {
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

    public byte getRequprio() {
        return requprio;
    }

    public void setRequprio(byte requprio) {
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

    public Short getRequcaso() {
        return requcaso;
    }

    public void setRequcaso(Short requcaso) {
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

    public Integer getRequtere() {
        return requtere;
    }

    public void setRequtere(Integer requtere) {
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

    @Transient
    public BigDecimal getRequhoes() {
        return requhoes;
    }

    @Transient
    public void setRequhoes(BigDecimal requhoes) {
        this.requhoes = requhoes;
    }

    @Transient
    public BigDecimal getRequhore() {
        return requhore;
    }

    @Transient
    public void setRequhore(BigDecimal requhore) {
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

    public Short getRequacse() {
        return requacse;
    }

    public void setRequacse(Short requacse) {
        this.requacse = requacse;
    }

    public Integer getRequporc() {
        return requporc;
    }

    public void setRequporc(Integer requporc) {
        this.requporc = requporc;
    }

    public Byte getRequcali() {
        return requcali;
    }

    public void setRequcali(Byte requcali) {
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

    @Transient
    public BigDecimal getRequties() {
        return requties;
    }

    @Transient
    public void setRequties(BigDecimal requties) {
        this.requties = requties;
    }

    public String getRequmeso() {
        return requmeso;
    }

    public void setRequmeso(String requmeso) {
        this.requmeso = requmeso;
    }

    public Short getRequnupr() {
        return requnupr;
    }

    public void setRequnupr(Short requnupr) {
        this.requnupr = requnupr;
    }

    public String getRequrese() {
        return requrese;
    }

    public void setRequrese(String requrese) {
        this.requrese = requrese;
    }

    public String getRequobca() {
        return requobca;
    }

    public void setRequobca(String requobca) {
        this.requobca = requobca;
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

    public String getRequcaan() {
        return requcaan;
    }

    public void setRequcaan(String requcaan) {
        this.requcaan = requcaan;
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

    public String getRequtire() {
        return requtire;
    }

    public void setRequtire(String requtire) {
        this.requtire = requtire;
    }

    public String getRequnore() {
        return requnore;
    }

    public void setRequnore(String requnore) {
        this.requnore = requnore;
    }

    public String getRequprop() {
        return requprop;
    }

    public void setRequprop(String requprop) {
        this.requprop = requprop;
    }

    public String getRequbene() {
        return requbene;
    }

    public void setRequbene(String requbene) {
        this.requbene = requbene;
    }

    public Integer getRequtito() {
        return requtito;
    }

    public void setRequtito(Integer requtito) {
        this.requtito = requtito;
    }

    public String getRequapta() {
        return requapta;
    }

    public void setRequapta(String requapta) {
        this.requapta = requapta;
    }

    public Date getRequfeta() {
        return requfeta;
    }

    public void setRequfeta(Date requfeta) {
        this.requfeta = requfeta;
    }

    public Date getRequferr() {
        return requferr;
    }

    public void setRequferr(Date requferr) {
        this.requferr = requferr;
    }

    public String getRequroma() {
        return requroma;
    }

    public void setRequroma(String requroma) {
        this.requroma = requroma;
    }

    public String getRequacna() {
        return requacna;
    }

    public void setRequacna(String requacna) {
        this.requacna = requacna;
    }

    public String getRequtise() {
        return requtise;
    }

    public void setRequtise(String requtise) {
        this.requtise = requtise;
    }

    public Integer getRequcmco() {
        return requcmco;
    }

    public void setRequcmco(Integer requcmco) {
        this.requcmco = requcmco;
    }

    @Transient
    public BigDecimal getRequesti() {
        return requesti;
    }

    @Transient
    public void setRequesti(BigDecimal requesti) {
        this.requesti = requesti;
    }

    @Transient
    public BigDecimal getRequorse() {
        return requorse;
    }

    @Transient
    public void setRequorse(BigDecimal requorse) {
        this.requorse = requorse;
    }

    @Transient
    public BigDecimal getRequnuca() {
        return requnuca;
    }

    @Transient
    public void setRequnuca(BigDecimal requnuca) {
        this.requnuca = requnuca;
    }

    public String getRequnouf() {
        return requnouf;
    }

    public void setRequnouf(String requnouf) {
        this.requnouf = requnouf;
    }

    public String getRequarea() {
        return requarea;
    }

    public void setRequarea(String requarea) {
        this.requarea = requarea;
    }

    @Transient
    public BigDecimal getRequexts() {
        return requexts;
    }

    @Transient
    public void setRequexts(BigDecimal requexts) {
        this.requexts = requexts;
    }

    @Transient
    public String getRequtinc() {
        return requtinc;
    }

    @Transient
    public void setRequtinc(String requtinc) {
        this.requtinc = requtinc;
    }

    @Transient
    public BigDecimal getRequcanc() {
        return requcanc;
    }

    @Transient
    public void setRequcanc(BigDecimal requcanc) {
        this.requcanc = requcanc;
    }

    public List<Anexsoli> getListAnexos() {
        return listAnexos;
    }

    public void setListAnexos(List<Anexsoli> listAnexos) {
        this.listAnexos = listAnexos;
    }

    public List<MantprodEntity> getDetalleRequerimiento() {
        return detalleRequerimiento;
    }

    public void setDetalleRequerimiento(List<MantprodEntity> detalleRequerimiento) {
        this.detalleRequerimiento = detalleRequerimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityRequerim that = (EntityRequerim) o;
        return requcodi == that.requcodi && requprio == that.requprio && Objects.equals(requclie, that.requclie) && Objects.equals(requprod, that.requprod) && Objects.equals(requfech, that.requfech) && Objects.equals(requclas, that.requclas) && Objects.equals(requtipo, that.requtipo) && Objects.equals(requdeta, that.requdeta) && Objects.equals(requdiem, that.requdiem) && Objects.equals(requdivi, that.requdivi) && Objects.equals(requnuex, that.requnuex) && Objects.equals(requpere, that.requpere) && Objects.equals(requpeso, that.requpeso) && Objects.equals(requsoli, that.requsoli) && Objects.equals(requmeco, that.requmeco) && Objects.equals(requfesi, that.requfesi) && Objects.equals(requterm, that.requterm) && Objects.equals(requfeco, that.requfeco) && Objects.equals(requfecp, that.requfecp) && Objects.equals(requfeso, that.requfeso) && Objects.equals(requesta, that.requesta) && Objects.equals(requperi, that.requperi) && Objects.equals(requresp, that.requresp) && Objects.equals(requfeer, that.requfeer) && Objects.equals(requobpr, that.requobpr) && Objects.equals(requrepr, that.requrepr) && Objects.equals(requfeip, that.requfeip) && Objects.equals(requfefp, that.requfefp) && Objects.equals(requobse, that.requobse) && Objects.equals(requacti, that.requacti) && Objects.equals(requproy, that.requproy) && Objects.equals(requsecu, that.requsecu) && Objects.equals(requprog, that.requprog) && Objects.equals(requvibu, that.requvibu) && Objects.equals(requfevb, that.requfevb) && Objects.equals(requrech, that.requrech) && Objects.equals(requfere, that.requfere) && Objects.equals(requcaus, that.requcaus) && Objects.equals(requprue, that.requprue) && Objects.equals(requcaso, that.requcaso) && Objects.equals(requcrit, that.requcrit) && Objects.equals(requorig, that.requorig) && Objects.equals(requmapr, that.requmapr) && Objects.equals(requmaps, that.requmaps) && Objects.equals(requmase, that.requmase) && Objects.equals(requcont, that.requcont) && Objects.equals(requtere, that.requtere) && Objects.equals(requobco, that.requobco) && Objects.equals(requfact, that.requfact) && Objects.equals(requsoco, that.requsoco) && Objects.equals(requrele, that.requrele) && Objects.equals(requfelc, that.requfelc) && Objects.equals(requfelp, that.requfelp) && Objects.equals(requdest, that.requdest) && Objects.equals(requcomp, that.requcomp) && Objects.equals(requhoes, that.requhoes) && Objects.equals(requhore, that.requhore) && Objects.equals(requarch, that.requarch) && Objects.equals(requcicl, that.requcicl) && Objects.equals(requfecc, that.requfecc) && Objects.equals(requdigi, that.requdigi) && Objects.equals(requpers, that.requpers) && Objects.equals(requdevo, that.requdevo) && Objects.equals(requfede, that.requfede) && Objects.equals(requcade, that.requcade) && Objects.equals(requdede, that.requdede) && Objects.equals(requacpr, that.requacpr) && Objects.equals(requacnu, that.requacnu) && Objects.equals(requacse, that.requacse) && Objects.equals(requporc, that.requporc) && Objects.equals(requcali, that.requcali) && Objects.equals(requpres, that.requpres) && Objects.equals(requpese, that.requpese) && Objects.equals(requlaps, that.requlaps) && Objects.equals(requclso, that.requclso) && Objects.equals(requvweb, that.requvweb) && Objects.equals(requpaco, that.requpaco) && Objects.equals(requanul, that.requanul) && Objects.equals(requfean, that.requfean) && Objects.equals(requcoco, that.requcoco) && Objects.equals(requclac, that.requclac) && Objects.equals(requties, that.requties) && Objects.equals(requmeso, that.requmeso) && Objects.equals(requnupr, that.requnupr) && Objects.equals(requrese, that.requrese) && Objects.equals(requobca, that.requobca) && Objects.equals(requproc, that.requproc) && Objects.equals(requsupr, that.requsupr) && Objects.equals(requcaan, that.requcaan) && Objects.equals(requfuen, that.requfuen) && Objects.equals(requmodu, that.requmodu) && Objects.equals(requexte, that.requexte) && Objects.equals(requtire, that.requtire) && Objects.equals(requnore, that.requnore) && Objects.equals(requprop, that.requprop) && Objects.equals(requbene, that.requbene) && Objects.equals(requtito, that.requtito) && Objects.equals(requapta, that.requapta) && Objects.equals(requfeta, that.requfeta) && Objects.equals(requferr, that.requferr) && Objects.equals(requroma, that.requroma) && Objects.equals(requacna, that.requacna) && Objects.equals(requtise, that.requtise) && Objects.equals(requcmco, that.requcmco) && Objects.equals(requesti, that.requesti) && Objects.equals(requorse, that.requorse) && Objects.equals(requnuca, that.requnuca) && Objects.equals(requnouf, that.requnouf) && Objects.equals(requarea, that.requarea) && Objects.equals(requexts, that.requexts) && Objects.equals(requtinc, that.requtinc) && Objects.equals(requcanc, that.requcanc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requcodi, requclie, requprod, requfech, requclas, requtipo, requdeta, requdiem, requdivi, requnuex, requprio, requpere, requpeso, requsoli, requmeco, requfesi, requterm, requfeco, requfecp, requfeso, requesta, requperi, requresp, requfeer, requobpr, requrepr, requfeip, requfefp, requobse, requacti, requproy, requsecu, requprog, requvibu, requfevb, requrech, requfere, requcaus, requprue, requcaso, requcrit, requorig, requmapr, requmaps, requmase, requcont, requtere, requobco, requfact, requsoco, requrele, requfelc, requfelp, requdest, requcomp, requhoes, requhore, requarch, requcicl, requfecc, requdigi, requpers, requdevo, requfede, requcade, requdede, requacpr, requacnu, requacse, requporc, requcali, requpres, requpese, requlaps, requclso, requvweb, requpaco, requanul, requfean, requcoco, requclac, requties, requmeso, requnupr, requrese, requobca, requproc, requsupr, requcaan, requfuen, requmodu, requexte, requtire, requnore, requprop, requbene, requtito, requapta, requfeta, requferr, requroma, requacna, requtise, requcmco, requesti, requorse, requnuca, requnouf, requarea, requexts, requtinc, requcanc);
    }
}
