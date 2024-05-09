package main.massiivioperatsioonid;

import main.MassiiviSeis;

public abstract class Massiivioperatsioon {
    MassiiviSeis massiivPealeOperatsiooni;

    public MassiiviSeis getMassiivPealeOperatsiooni() {
        return massiivPealeOperatsiooni;
    }
    public abstract Massiivioperatsioon järgmineÕigeKäik();
    public abstract boolean kasOnVõimalikLäbimänguJätkata();

    public Massiivioperatsioon(MassiiviSeis massiivEnneOperatsiooni) {
        this.massiivPealeOperatsiooni = massiivEnneOperatsiooni.teeKoopia();
    }
    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);
}
