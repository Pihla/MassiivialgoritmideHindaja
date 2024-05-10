package main.massiivioperatsioonid.valikumeetod;

import main.MassiiviSeis;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

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
