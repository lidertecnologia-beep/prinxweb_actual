package com.smarttmt.mantprod;

import com.smarttmt.personal.PersonalEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "MANTPROD", schema = "PRINX")
@IdClass(MantprodPK.class)
public class MantprodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MAPRPROY")
    private int maprproy;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MAPRPRSE")
    private int maprprse;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MAPRSECU")
    private int maprsecu;
    @Basic
    @Column(name = "MAPRFECH")
    private Date maprfech;
    @Basic
    @Column(name = "MAPRPROD")
    private String maprprod;
    @Basic
    @Column(name = "MAPROBJE")
    private String maprobje;
    @Basic
    @Column(name = "MAPRDETA")
    private String maprdeta;
    @Basic
    @Column(name = "MAPRFEIN")
    private Date maprfein;
    @Basic
    @Column(name = "MAPRFEFI")
    private Date maprfefi;
    @Basic
    @Column(name = "MAPRVBDE")
    private String maprvbde;
    @Basic
    @Column(name = "MAPRREQU")
    private Integer maprrequ;
    @Basic
    @Column(name = "MAPRACTI")
    private String mapracti;
    @Basic
    @Column(name = "MAPRTIPR")
    private int maprtipr;
    @Basic
    @Column(name = "MAPRTIRE")
    private Integer maprtire;
    @Basic
    @Column(name = "MAPRESTA")
    private String mapresta;
    @Basic
    @Column(name = "MAPRFIRE")
    private Date maprfire;
    @Basic
    @Column(name = "MAPRPORC")
    private int maprporc;
    @Basic
    @Column(name = "MAPRINRE")
    private Date maprinre;
    @Basic
    @Column(name = "MAPRNUVI")
    private Integer maprnuvi;
    @Basic
    @Column(name = "MAPRVEAN")
    private String maprvean;
    @Basic
    @Column(name = "MAPRVENU")
    private String maprvenu;
    @Basic
    @Column(name = "MAPRRELE")
    private String maprrele;
    @Basic
    @Column(name = "MAPROBSE")
    private String maprobse;
    @Basic
    @Column(name = "MAPRREVI")
    private String maprrevi;
    @Basic
    @Column(name = "MAPRPEAS")
    private String maprpeas;
    @Basic
    @Column(name = "MAPRPEEN")
    private String maprpeen;
    @Basic
    @Column(name = "MAPRTERM")
    private String maprterm;
    @Basic
    @Column(name = "MAPRDIGI")
    private String maprdigi;
    @Basic
    @Column(name = "MAPRFESI")
    private Date maprfesi;
    @Basic
    @Column(name = "MAPRPERS")
    private String maprpers;
    @Basic
    @Column(name = "MAPRSEFU")
    private Integer maprsefu;
    @Basic
    @Column(name = "MAPRGEAC")
    private String maprgeac;
    @Basic
    @Column(name = "MAPRSSQL")
    private String maprssql;
    @Basic
    @Column(name = "MAPRCLSO")
    private String maprclso;
    @Basic
    @Column(name = "MAPRCAUS")
    private String maprcaus;
    @Basic
    @Column(name = "MAPRCASU")
    private String maprcasu;
    @Basic
    @Column(name = "MAPROBSU")
    private String maprobsu;
    @Basic
    @Column(name = "MAPRTISU")
    private Integer maprtisu;
    @Basic
    @Column(name = "MAPRFHRE")
    private Date maprfhre;
    @Basic
    @Column(name = "MAPRFHTE")
    private Date maprfhte;
    @Basic
    @Column(name = "MAPRFEEC")
    private Date maprfeec;
    @Basic
    @Column(name = "MAPRVWEB")
    private Integer maprvweb;
    @Basic
    @Column(name = "MAPRTPRE")
    private String maprtpre;
    @Basic
    @Column(name = "MAPRTIPO")
    private String maprtipo;
    @Basic
    @Column(name = "MAPRCLAS")
    private String maprclas;
    @Basic
    @Column(name = "MAPRPPDD")
    private Integer maprppdd;
    @Basic
    @Column(name = "MAPRPPDI")
    private Integer maprppdi;
    @Basic
    @Column(name = "MAPRPNFO")
    private String maprpnfo;
    @Basic
    @Column(name = "MAPRPNCO")
    private String maprpnco;
    @Basic
    @Column(name = "MAPRPNCA")
    private String maprpnca;
    @Basic
    @Column(name = "MAPRPNPR")
    private String maprpnpr;
    @Basic
    @Column(name = "MAPRPSAL")
    private BigDecimal maprpsal;
    @Basic
    @Column(name = "MAPRFECC")
    private Date maprfecc;
    @Basic
    @Column(name = "MAPRTINC")
    private String maprtinc;
    @Basic
    @Column(name = "MAPRCANC")
    private BigInteger maprcanc;

    @JoinColumn(name = "MAPRRESP", referencedColumnName = "PERSCODI")
    @ManyToOne(optional = false)
    private PersonalEntity maprresp;

    public int getMaprproy() {
        return maprproy;
    }

    public void setMaprproy(int maprproy) {
        this.maprproy = maprproy;
    }

    public int getMaprprse() {
        return maprprse;
    }

    public void setMaprprse(int maprprse) {
        this.maprprse = maprprse;
    }

    public int getMaprsecu() {
        return maprsecu;
    }

    public void setMaprsecu(int maprsecu) {
        this.maprsecu = maprsecu;
    }

    public Date getMaprfech() {
        return maprfech;
    }

    public void setMaprfech(Date maprfech) {
        this.maprfech = maprfech;
    }

    public String getMaprprod() {
        return maprprod;
    }

    public void setMaprprod(String maprprod) {
        this.maprprod = maprprod;
    }

    public String getMaprobje() {
        return maprobje;
    }

    public void setMaprobje(String maprobje) {
        this.maprobje = maprobje;
    }

    public String getMaprdeta() {
        return maprdeta;
    }

    public void setMaprdeta(String maprdeta) {
        this.maprdeta = maprdeta;
    }

    public Date getMaprfein() {
        return maprfein;
    }

    public void setMaprfein(Date maprfein) {
        this.maprfein = maprfein;
    }

    public Date getMaprfefi() {
        return maprfefi;
    }

    public void setMaprfefi(Date maprfefi) {
        this.maprfefi = maprfefi;
    }

    public String getMaprvbde() {
        return maprvbde;
    }

    public void setMaprvbde(String maprvbde) {
        this.maprvbde = maprvbde;
    }

    public Integer getMaprrequ() {
        return maprrequ;
    }

    public void setMaprrequ(Integer maprrequ) {
        this.maprrequ = maprrequ;
    }

    public String getMapracti() {
        return mapracti;
    }

    public void setMapracti(String mapracti) {
        this.mapracti = mapracti;
    }

    public int getMaprtipr() {
        return maprtipr;
    }

    public void setMaprtipr(int maprtipr) {
        this.maprtipr = maprtipr;
    }

    public Integer getMaprtire() {
        return maprtire;
    }

    public void setMaprtire(Integer maprtire) {
        this.maprtire = maprtire;
    }

    public String getMapresta() {
        return mapresta;
    }

    public void setMapresta(String mapresta) {
        this.mapresta = mapresta;
    }

    public Date getMaprfire() {
        return maprfire;
    }

    public void setMaprfire(Date maprfire) {
        this.maprfire = maprfire;
    }

    public int getMaprporc() {
        return maprporc;
    }

    public void setMaprporc(int maprporc) {
        this.maprporc = maprporc;
    }

    public Date getMaprinre() {
        return maprinre;
    }

    public void setMaprinre(Date maprinre) {
        this.maprinre = maprinre;
    }

    public Integer getMaprnuvi() {
        return maprnuvi;
    }

    public void setMaprnuvi(Integer maprnuvi) {
        this.maprnuvi = maprnuvi;
    }

    public String getMaprvean() {
        return maprvean;
    }

    public void setMaprvean(String maprvean) {
        this.maprvean = maprvean;
    }

    public String getMaprvenu() {
        return maprvenu;
    }

    public void setMaprvenu(String maprvenu) {
        this.maprvenu = maprvenu;
    }

    public String getMaprrele() {
        return maprrele;
    }

    public void setMaprrele(String maprrele) {
        this.maprrele = maprrele;
    }

    public String getMaprobse() {
        return maprobse;
    }

    public void setMaprobse(String maprobse) {
        this.maprobse = maprobse;
    }

    public String getMaprrevi() {
        return maprrevi;
    }

    public void setMaprrevi(String maprrevi) {
        this.maprrevi = maprrevi;
    }

    public String getMaprpeas() {
        return maprpeas;
    }

    public void setMaprpeas(String maprpeas) {
        this.maprpeas = maprpeas;
    }

    public String getMaprpeen() {
        return maprpeen;
    }

    public void setMaprpeen(String maprpeen) {
        this.maprpeen = maprpeen;
    }

    public String getMaprterm() {
        return maprterm;
    }

    public void setMaprterm(String maprterm) {
        this.maprterm = maprterm;
    }

    public String getMaprdigi() {
        return maprdigi;
    }

    public void setMaprdigi(String maprdigi) {
        this.maprdigi = maprdigi;
    }

    public Date getMaprfesi() {
        return maprfesi;
    }

    public void setMaprfesi(Date maprfesi) {
        this.maprfesi = maprfesi;
    }

    public String getMaprpers() {
        return maprpers;
    }

    public void setMaprpers(String maprpers) {
        this.maprpers = maprpers;
    }

    public Integer getMaprsefu() {
        return maprsefu;
    }

    public void setMaprsefu(Integer maprsefu) {
        this.maprsefu = maprsefu;
    }

    public String getMaprgeac() {
        return maprgeac;
    }

    public void setMaprgeac(String maprgeac) {
        this.maprgeac = maprgeac;
    }

    public String getMaprssql() {
        return maprssql;
    }

    public void setMaprssql(String maprssql) {
        this.maprssql = maprssql;
    }

    public String getMaprclso() {
        return maprclso;
    }

    public void setMaprclso(String maprclso) {
        this.maprclso = maprclso;
    }

    public String getMaprcaus() {
        return maprcaus;
    }

    public void setMaprcaus(String maprcaus) {
        this.maprcaus = maprcaus;
    }

    public String getMaprcasu() {
        return maprcasu;
    }

    public void setMaprcasu(String maprcasu) {
        this.maprcasu = maprcasu;
    }

    public String getMaprobsu() {
        return maprobsu;
    }

    public void setMaprobsu(String maprobsu) {
        this.maprobsu = maprobsu;
    }

    public Integer getMaprtisu() {
        return maprtisu;
    }

    public void setMaprtisu(Integer maprtisu) {
        this.maprtisu = maprtisu;
    }

    public Date getMaprfhre() {
        return maprfhre;
    }

    public void setMaprfhre(Date maprfhre) {
        this.maprfhre = maprfhre;
    }

    public Date getMaprfhte() {
        return maprfhte;
    }

    public void setMaprfhte(Date maprfhte) {
        this.maprfhte = maprfhte;
    }

    public Date getMaprfeec() {
        return maprfeec;
    }

    public void setMaprfeec(Date maprfeec) {
        this.maprfeec = maprfeec;
    }

    public Integer getMaprvweb() {
        return maprvweb;
    }

    public void setMaprvweb(Integer maprvweb) {
        this.maprvweb = maprvweb;
    }

    public String getMaprtpre() {
        return maprtpre;
    }

    public void setMaprtpre(String maprtpre) {
        this.maprtpre = maprtpre;
    }

    public String getMaprtipo() {
        return maprtipo;
    }

    public void setMaprtipo(String maprtipo) {
        this.maprtipo = maprtipo;
    }

    public String getMaprclas() {
        return maprclas;
    }

    public void setMaprclas(String maprclas) {
        this.maprclas = maprclas;
    }

    public Integer getMaprppdd() {
        return maprppdd;
    }

    public void setMaprppdd(Integer maprppdd) {
        this.maprppdd = maprppdd;
    }

    public Integer getMaprppdi() {
        return maprppdi;
    }

    public void setMaprppdi(Integer maprppdi) {
        this.maprppdi = maprppdi;
    }

    public String getMaprpnfo() {
        return maprpnfo;
    }

    public void setMaprpnfo(String maprpnfo) {
        this.maprpnfo = maprpnfo;
    }

    public String getMaprpnco() {
        return maprpnco;
    }

    public void setMaprpnco(String maprpnco) {
        this.maprpnco = maprpnco;
    }

    public String getMaprpnca() {
        return maprpnca;
    }

    public void setMaprpnca(String maprpnca) {
        this.maprpnca = maprpnca;
    }

    public String getMaprpnpr() {
        return maprpnpr;
    }

    public void setMaprpnpr(String maprpnpr) {
        this.maprpnpr = maprpnpr;
    }

    public BigDecimal getMaprpsal() {
        return maprpsal;
    }

    public void setMaprpsal(BigDecimal maprpsal) {
        this.maprpsal = maprpsal;
    }

    public Date getMaprfecc() {
        return maprfecc;
    }

    public void setMaprfecc(Date maprfecc) {
        this.maprfecc = maprfecc;
    }

    public String getMaprtinc() {
        return maprtinc;
    }

    public void setMaprtinc(String maprtinc) {
        this.maprtinc = maprtinc;
    }

    public BigInteger getMaprcanc() {
        return maprcanc;
    }

    public void setMaprcanc(BigInteger maprcanc) {
        this.maprcanc = maprcanc;
    }

    public String getRequResp() {
        return maprresp.getPersnomb() + " " + maprresp.getPersapel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantprodEntity that = (MantprodEntity) o;
        return maprproy == that.maprproy && maprprse == that.maprprse && maprsecu == that.maprsecu && maprtipr == that.maprtipr && maprporc == that.maprporc && Objects.equals(maprfech, that.maprfech) && Objects.equals(maprprod, that.maprprod) && Objects.equals(maprobje, that.maprobje) && Objects.equals(maprdeta, that.maprdeta) && Objects.equals(maprfein, that.maprfein) && Objects.equals(maprfefi, that.maprfefi) && Objects.equals(maprvbde, that.maprvbde) && Objects.equals(maprresp, that.maprresp)  && Objects.equals(mapracti, that.mapracti) && Objects.equals(maprtire, that.maprtire) && Objects.equals(mapresta, that.mapresta) && Objects.equals(maprfire, that.maprfire) && Objects.equals(maprinre, that.maprinre) && Objects.equals(maprnuvi, that.maprnuvi) && Objects.equals(maprvean, that.maprvean) && Objects.equals(maprvenu, that.maprvenu) && Objects.equals(maprrele, that.maprrele) && Objects.equals(maprobse, that.maprobse) && Objects.equals(maprrevi, that.maprrevi) && Objects.equals(maprpeas, that.maprpeas) && Objects.equals(maprpeen, that.maprpeen) && Objects.equals(maprterm, that.maprterm) && Objects.equals(maprdigi, that.maprdigi) && Objects.equals(maprfesi, that.maprfesi) && Objects.equals(maprpers, that.maprpers) && Objects.equals(maprsefu, that.maprsefu) && Objects.equals(maprgeac, that.maprgeac) && Objects.equals(maprssql, that.maprssql) && Objects.equals(maprclso, that.maprclso) && Objects.equals(maprcaus, that.maprcaus) && Objects.equals(maprcasu, that.maprcasu) && Objects.equals(maprobsu, that.maprobsu) && Objects.equals(maprtisu, that.maprtisu) && Objects.equals(maprfhre, that.maprfhre) && Objects.equals(maprfhte, that.maprfhte) && Objects.equals(maprfeec, that.maprfeec) && Objects.equals(maprvweb, that.maprvweb) && Objects.equals(maprtpre, that.maprtpre) && Objects.equals(maprtipo, that.maprtipo) && Objects.equals(maprclas, that.maprclas) && Objects.equals(maprppdd, that.maprppdd) && Objects.equals(maprppdi, that.maprppdi) && Objects.equals(maprpnfo, that.maprpnfo) && Objects.equals(maprpnco, that.maprpnco) && Objects.equals(maprpnca, that.maprpnca) && Objects.equals(maprpnpr, that.maprpnpr) && Objects.equals(maprpsal, that.maprpsal) && Objects.equals(maprfecc, that.maprfecc) && Objects.equals(maprtinc, that.maprtinc) && Objects.equals(maprcanc, that.maprcanc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maprproy, maprprse, maprsecu, maprfech, maprprod, maprobje, maprdeta, maprfein, maprfefi, maprvbde, maprresp, maprrequ, mapracti, maprtipr, maprtire, mapresta, maprfire, maprporc, maprinre, maprnuvi, maprvean, maprvenu, maprrele, maprobse, maprrevi, maprpeas, maprpeen, maprterm, maprdigi, maprfesi, maprpers, maprsefu, maprgeac, maprssql, maprclso, maprcaus, maprcasu, maprobsu, maprtisu, maprfhre, maprfhte, maprfeec, maprvweb, maprtpre, maprtipo, maprclas, maprppdd, maprppdi, maprpnfo, maprpnco, maprpnca, maprpnpr, maprpsal, maprfecc, maprtinc, maprcanc);
    }
}
