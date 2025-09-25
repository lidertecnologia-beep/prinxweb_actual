package com.smarttmt.anexos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ANEXSOLI")
public class Anexsoli {

    @JsonIgnore
    @EmbeddedId
    AnexsoliEntityPK id;
    @Basic
    @Column(name = "ANSOREQU")
    private Integer ansorequ;
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


    public AnexsoliEntityPK getId() {
        return id;
    }

    public void setId(AnexsoliEntityPK id) {
        this.id = id;
    }

    public Integer getAnsorequ() {
        return ansorequ;
    }

    public void setAnsorequ(Integer ansorequ) {
        this.ansorequ = ansorequ;
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

    public Integer getAnsosolu() {
        return id.getAnsosolu();
    }
    public Integer getAnsosecu() {
        return id.getAnsosecu();
    }
    public String getAnsoarch() {
        return id.getAnsoarch();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anexsoli anexsoli)) return false;
        return Objects.equals(getId(), anexsoli.getId()) && Objects.equals(getAnsorequ(), anexsoli.getAnsorequ()) && Objects.equals(getAnsoterm(), anexsoli.getAnsoterm()) && Objects.equals(getAnsodigi(), anexsoli.getAnsodigi()) && Objects.equals(getAnsofesi(), anexsoli.getAnsofesi()) && Objects.equals(getAnsopers(), anexsoli.getAnsopers()) && Objects.equals(getAnsovweb(), anexsoli.getAnsovweb()) && Objects.equals(getAnsoruar(), anexsoli.getAnsoruar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnsorequ(), getAnsoterm(), getAnsodigi(), getAnsofesi(), getAnsopers(), getAnsovweb(), getAnsoruar());
    }
}
