package com.smarttmt.anexos;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ANEXSOLI", schema = "PRINX")
public class Anexsoli2 {

    @Id
    @Basic
    @Column(name = "ANSOSOLU")
    private Integer ansosolu;
    @Basic
    @Column(name = "ANSOREQU")
    private Integer ansorequ;
    @Basic
    @Column(name = "ANSOSECU")
    private Short ansosecu;
    @Basic
    @Column(name = "ANSOARCH")
    private String ansoarch;
    @Basic
    @Column(name = "ANSOTERM")
    private String ansoterm;
    @Basic
    @Column(name = "ANSODIGI")
    private String ansodigi;
    @Basic
    @Column(name = "ANSOFESI")
    private Date ansofesi;
    @Basic
    @Column(name = "ANSOPERS")
    private String ansopers;
    @Basic
    @Column(name = "ANSOVWEB")
    private Integer ansovweb;
    @Basic
    @Column(name = "ANSORUAR")
    private String ansoruar;

    public Integer getAnsosolu() {
        return ansosolu;
    }

    public void setAnsosolu(Integer ansosolu) {
        this.ansosolu = ansosolu;
    }

    public Integer getAnsorequ() {
        return ansorequ;
    }

    public void setAnsorequ(Integer ansorequ) {
        this.ansorequ = ansorequ;
    }

    public Short getAnsosecu() {
        return ansosecu;
    }

    public void setAnsosecu(Short ansosecu) {
        this.ansosecu = ansosecu;
    }

    public String getAnsoarch() {
        return ansoarch;
    }

    public void setAnsoarch(String ansoarch) {
        this.ansoarch = ansoarch;
    }

    public String getAnsoterm() {
        return ansoterm;
    }

    public void setAnsoterm(String ansoterm) {
        this.ansoterm = ansoterm;
    }

    public String getAnsodigi() {
        return ansodigi;
    }

    public void setAnsodigi(String ansodigi) {
        this.ansodigi = ansodigi;
    }

    public Date getAnsofesi() {
        return ansofesi;
    }

    public void setAnsofesi(Date ansofesi) {
        this.ansofesi = ansofesi;
    }

    public String getAnsopers() {
        return ansopers;
    }

    public void setAnsopers(String ansopers) {
        this.ansopers = ansopers;
    }

    public Integer getAnsovweb() {
        return ansovweb;
    }

    public void setAnsovweb(Integer ansovweb) {
        this.ansovweb = ansovweb;
    }

    public String getAnsoruar() {
        return ansoruar;
    }

    public void setAnsoruar(String ansoruar) {
        this.ansoruar = ansoruar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anexsoli2 that = (Anexsoli2) o;
        return Objects.equals(ansosolu, that.ansosolu) && Objects.equals(ansorequ, that.ansorequ) && Objects.equals(ansosecu, that.ansosecu) && Objects.equals(ansoarch, that.ansoarch) && Objects.equals(ansoterm, that.ansoterm) && Objects.equals(ansodigi, that.ansodigi) && Objects.equals(ansofesi, that.ansofesi) && Objects.equals(ansopers, that.ansopers) && Objects.equals(ansovweb, that.ansovweb) && Objects.equals(ansoruar, that.ansoruar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ansosolu, ansorequ, ansosecu, ansoarch, ansoterm, ansodigi, ansofesi, ansopers, ansovweb, ansoruar);
    }
}
