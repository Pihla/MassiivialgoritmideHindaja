package main.massiivioperatsioonid.pistemeetod;


import main.MassiiviSeis;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class PistemeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public PistemeetodiLäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return new PistemeetodiTööalaValimineVõiMuutmine( 0, 1, this.getMassiivPealeOperatsiooni());
    }

    @Override
    public String toString() {
        return String.format("Pistemeetodi läbimängu alustamine massiivil %s", this.getMassiivPealeOperatsiooni());
    }

}

