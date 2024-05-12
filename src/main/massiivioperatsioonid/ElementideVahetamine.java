package main.massiivioperatsioonid;

import main.MassiiviSeis;

public abstract class ElementideVahetamine extends Massiivioperatsioon {
    int vasakpoolseElemendiIndeks;
    int parempoolseElemendiIndeks;

    public ElementideVahetamine(int üheVahetatavaIndeks, int teiseVahetatavaIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
        if (üheVahetatavaIndeks == teiseVahetatavaIndeks) {
            throw new RuntimeException("Vahetatavad elemendid peavad olema erinevad");
        }
        this.vasakpoolseElemendiIndeks = Math.min(üheVahetatavaIndeks, teiseVahetatavaIndeks);
        this.parempoolseElemendiIndeks = Math.max(üheVahetatavaIndeks, teiseVahetatavaIndeks);
        teeVahetus();
    }

    private void teeVahetus() {
        int abi = getSeis().getMassiiv()[vasakpoolseElemendiIndeks];
        getSeis().getMassiiv()[vasakpoolseElemendiIndeks] = getSeis().getMassiiv()[parempoolseElemendiIndeks];
        getSeis().getMassiiv()[parempoolseElemendiIndeks] = abi;
    }

    @Override
    public String toString() {
        return String.format("Elementide vahetus indeksitel %d ja %d. Uus massiiv: %s", vasakpoolseElemendiIndeks, parempoolseElemendiIndeks, getSeis());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ElementideVahetamine elementideVahetamine)) {
            return false;
        }

        return this.vasakpoolseElemendiIndeks == elementideVahetamine.vasakpoolseElemendiIndeks
                && this.parempoolseElemendiIndeks == elementideVahetamine.parempoolseElemendiIndeks;
    }
}
