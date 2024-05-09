package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class MullimeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public MullimeetodiLäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return new MullimeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
    }

    @Override
    public String toString() {
        return String.format("Mullimeetodi läbimängu alustamine massiivil %s", this.getSeis());
    }
}
