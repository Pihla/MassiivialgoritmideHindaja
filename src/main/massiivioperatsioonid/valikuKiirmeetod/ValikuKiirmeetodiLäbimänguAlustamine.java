package main.massiivioperatsioonid.valikuKiirmeetod;

import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiTööalaValimine;

public class ValikuKiirmeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public ValikuKiirmeetodiLäbimänguAlustamine(ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
        this.seis = massiivEnneOperatsiooni.teeKoopia();//TODO vb võtta ära
    }

    ValikuKiirmeetodiMassiiviSeis seis;
    @Override
    public ValikuKiirmeetodiMassiiviSeis getSeis() {
        return seis;
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return new ValikumeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
    }

    @Override
    public String toString() {
        return String.format("Valiku kiirmeetodi läbimängu alustamine massiivil %s", this.getSeis());
    }
}
