package main.massiivioperatsioon.mullimeetod;

import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;

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
        return String.format("Mullimeetodi läbimängu alustamine massiivil %s.", this.getSeis());
    }
}
