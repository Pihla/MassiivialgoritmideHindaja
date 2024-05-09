package main.massiivioperatsioonid;

import main.MassiiviSeis;

public abstract class Massiivioperatsioon {
    MassiiviSeis seis; // massiivi seis peale operatsiooni

    public MassiiviSeis getSeis() {
        return seis;
    }
    public abstract Massiivioperatsioon järgmineÕigeKäik();
    public abstract boolean kasOnVõimalikLäbimänguJätkata();

    public Massiivioperatsioon(MassiiviSeis massiivEnneOperatsiooni) {
        this.seis = massiivEnneOperatsiooni.teeKoopia();
    }
    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);
}
