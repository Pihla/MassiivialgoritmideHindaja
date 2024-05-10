package main.massiivioperatsioonid;

import main.MassiiviSeis;

public abstract class ElementideVahetamine extends Massiivioperatsioon{
    int esimeseElemendiIndeks;
    int teiseElemendiIndeks;
    public ElementideVahetamine(int esimeseElemendiIndeks, int teiseElemendiIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
        if(esimeseElemendiIndeks == teiseElemendiIndeks) {
            throw new RuntimeException("Vahetatavad elemendid peavad olema erinevad");
        }
        this.esimeseElemendiIndeks = esimeseElemendiIndeks;
        this.teiseElemendiIndeks = teiseElemendiIndeks;
        //TODO vb muuta järjekord alati samaks
        teeVahetus();
    }

    private void teeVahetus() {
        int abi = getSeis().getMassiiv()[esimeseElemendiIndeks];
        getSeis().getMassiiv()[esimeseElemendiIndeks] = getSeis().getMassiiv()[teiseElemendiIndeks];
        getSeis().getMassiiv()[teiseElemendiIndeks] = abi;
    }

    @Override
    public String toString() {
        return String.format("Elementide vahetus indeksitel %d ja %d. Uus massiiv: %s", esimeseElemendiIndeks, teiseElemendiIndeks, getSeis());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ElementideVahetamine elementideVahetamine)) {
            return false;
        }

        return this.esimeseElemendiIndeks == elementideVahetamine.esimeseElemendiIndeks
                && this.teiseElemendiIndeks == elementideVahetamine.teiseElemendiIndeks;
    }
}
