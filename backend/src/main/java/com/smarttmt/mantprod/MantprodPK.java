package com.smarttmt.mantprod;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class MantprodPK implements Serializable {
    @Column(name = "MAPRPROY")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maprproy;
    @Column(name = "MAPRPRSE")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maprprse;
    @Column(name = "MAPRSECU")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maprsecu;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantprodPK that = (MantprodPK) o;
        return maprproy == that.maprproy && maprprse == that.maprprse && maprsecu == that.maprsecu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maprproy, maprprse, maprsecu);
    }
}
