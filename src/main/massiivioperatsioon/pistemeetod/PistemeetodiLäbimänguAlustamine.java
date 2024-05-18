package main.massiivioperatsioon.pistemeetod;


import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;

public class PistemeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public PistemeetodiLäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return new PistemeetodiTööalaValimine(0, 1, getSeis());
    }

    @Override
    public String toString() {
        return "Pistemeetodi läbimängu alustamine massiivil " + getSeis() + ".";
    }

}

