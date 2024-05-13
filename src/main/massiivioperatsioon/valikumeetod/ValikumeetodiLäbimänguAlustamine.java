package main.massiivioperatsioon.valikumeetod;

import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;

public class ValikumeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public ValikumeetodiLäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return new ValikumeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
    }

    @Override
    public String toString() {
        return String.format("Valikumeetodi läbimängu alustamine massiivil %s", this.getSeis());
    }
}
