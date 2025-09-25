package com.smarttmt.paraoper;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "PARAOPER", schema = "PRINX", catalog = "")

public class Paraoper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String paracodi;
    private String paradesc;
    private String parativa;
    private Short paralova;
    private String paracova;
    private BigInteger paravweb;

    public Paraoper(String paracodi) {
        this.paracodi = paracodi;
    }

    public Paraoper() {
    }

    public String getParacodi() {
        return paracodi;
    }

    public void setParacodi(String paracodi) {
        this.paracodi = paracodi;
    }

    public String getParadesc() {
        return paradesc;
    }

    public void setParadesc(String paradesc) {
        this.paradesc = paradesc;
    }

    public String getParativa() {
        return parativa;
    }

    public void setParativa(String parativa) {
        this.parativa = parativa;
    }

    public Short getParalova() {
        return paralova;
    }

    public void setParalova(Short paralova) {
        this.paralova = paralova;
    }

    public String getParacova() {
        return paracova;
    }

    public void setParacova(String paracova) {
        this.paracova = paracova;
    }

    public BigInteger getParavweb() {
        return paravweb;
    }

    public void setParavweb(BigInteger paravweb) {
        this.paravweb = paravweb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paraoper paraoper = (Paraoper) o;
        return Objects.equals(paracodi, paraoper.paracodi) && Objects.equals(paradesc, paraoper.paradesc) && Objects.equals(parativa, paraoper.parativa) && Objects.equals(paralova, paraoper.paralova) && Objects.equals(paracova, paraoper.paracova) && Objects.equals(paravweb, paraoper.paravweb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paracodi, paradesc, parativa, paralova, paracova, paravweb);
    }

    @Override
    public String toString() {
        return "Paraoper{" +
                "paracodi='" + paracodi + '\'' +
                ", paradesc='" + paradesc + '\'' +
                ", parativa='" + parativa + '\'' +
                ", paralova=" + paralova +
                ", paracova='" + paracova + '\'' +
                ", paravweb=" + paravweb +
                '}';
    }
}

