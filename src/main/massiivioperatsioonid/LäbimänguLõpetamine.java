package main.massiivioperatsioonid;

import main.MassiiviSeis;

public class LäbimänguLõpetamine extends Massiivioperatsioon{
    public LäbimänguLõpetamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeOperatsioon() {
        throw new RuntimeException("Peale läbimängu lõpetamist ei saa rohkem operatsioone teha");
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return false;
    }

    @Override
    public String toString() {
        return "Läbimängu lõpetamine";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof LäbimänguLõpetamine;
    }
}
