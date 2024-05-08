package main.massiivioperatsioonid;


import main.MassiiviSeis;

public abstract class LäbimänguAlustamine extends Massiivioperatsioon {

    public LäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof LäbimänguAlustamine;
    }
}
