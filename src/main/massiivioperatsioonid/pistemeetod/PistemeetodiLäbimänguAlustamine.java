package main.massiivioperatsioonid.pistemeetod;


import main.MassiiviSeis;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class PistemeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public PistemeetodiLäbimänguAlustamine(MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeOperatsioon() {
        return new PistemeetodiTööalaValimineVõiMuutmine(this.getMassiivPealeOperatsiooni(), 0, 1);
    }

    @Override
    public String toString() {
        return String.format("Pistemeetodi läbimängu alustamine massiivil %s", this.getMassiivPealeOperatsiooni());
    }

}

