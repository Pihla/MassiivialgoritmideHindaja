package main.massiivioperatsioon;

import main.kasutajaliides.ViganeSisendException;
import main.massiiviSeis.MassiiviSeis;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;

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
        if(!(this.getSeis() instanceof ValikuKiirmeetodiMassiiviSeis massiiviSeis)) {
            throw new RuntimeException("Massiivi seis peab olema ValikuKiirmeetodiMassiiviSeis-tüüpi");
        }
        int vastusePiir = massiiviSeis.getVastusePiir();
        return String.format("Lahkme järgi jaotamine. Massiivi seis peale lahkme järgi jaotamist: %s, lahe on indeksil %d, vastuse piir on indeksil %d.", this.getSeis(), this.lahkmeJärgiJaotamisePiiristJärgnevIndeks,vastusePiir);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof LahkmeJärgiJaotamine lahkmeJärgiJaotamine)) {
            return false;
        }

        return this.lahkmeJärgiJaotamisePiiristJärgnevIndeks == lahkmeJärgiJaotamine.getLahkmeJärgiJaotamisePiiristJärgnevIndeks()
                && Arrays.equals(this.getSeis().getMassiiv(), lahkmeJärgiJaotamine.getSeis().getMassiiv());
    }
}
