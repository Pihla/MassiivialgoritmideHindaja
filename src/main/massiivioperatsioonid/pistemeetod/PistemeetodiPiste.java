package main.massiivioperatsioonid.pistemeetod;


import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.Piste;

public class PistemeetodiPiste extends Piste {
    public PistemeetodiPiste(int algusIndeks, int lõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(algusIndeks, lõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeOperatsioon() {
        if(this.getMassiivPealeOperatsiooni().getMassiiv().length == this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()) {
            return new LäbimänguLõpetamine(this.getMassiivPealeOperatsiooni());
        }
        return new PistemeetodiTööalaValimineVõiMuutmine(this.getMassiivPealeOperatsiooni(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()+1);
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(!MassiiviTööriistad.kasOnÕigedElemendidKuniIndeksini(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks());
    }
}

