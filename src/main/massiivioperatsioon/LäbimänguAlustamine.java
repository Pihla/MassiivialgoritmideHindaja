package main.massiivioperatsioon;


import main.massiiviSeis.MassiiviSeis;

public abstract class LäbimänguAlustamine extends Massiivioperatsioon {

    public LäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public boolean läbimänguOnVõimalikJätkata() {
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
