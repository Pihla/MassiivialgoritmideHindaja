package main.massiivioperatsioonid;

import main.MassiiviSeis;

import java.util.Arrays;

public abstract class LahkmeJärgiJaotamine extends Massiivioperatsioon{
    int lahkmeJärgiJaotamisePiiristJärgnevIndeks;
    public LahkmeJärgiJaotamine(MassiiviSeis massiivEnneOperatsiooni, int lahkmeJärgiJaotamisePiiristJärgnevIndeks) {
        super(massiivEnneOperatsiooni);
        this.lahkmeJärgiJaotamisePiiristJärgnevIndeks = lahkmeJärgiJaotamisePiiristJärgnevIndeks;
    }

    public int getLahkmeJärgiJaotamisePiiristJärgnevIndeks() {
        return lahkmeJärgiJaotamisePiiristJärgnevIndeks;
    }

    @Override
    public String toString() {
        return String.format("Lahkme järgi jaotamine. Massiivi seis peale lahkme järgi jaotamist: %s, praegune piir on %d", this.getSeis(), this.lahkmeJärgiJaotamisePiiristJärgnevIndeks);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof LahkmeJärgiJaotamine lahkmeJärgiJaotamine)) {
            return false;
        }

        //TODO vaadata üle kas see on ikka hea alus võrdlemiseks
        return this.lahkmeJärgiJaotamisePiiristJärgnevIndeks == lahkmeJärgiJaotamine.getLahkmeJärgiJaotamisePiiristJärgnevIndeks()
                &&
                Arrays.equals(this.getSeis().getMassiiv(), lahkmeJärgiJaotamine.getSeis().getMassiiv());
    }
}
