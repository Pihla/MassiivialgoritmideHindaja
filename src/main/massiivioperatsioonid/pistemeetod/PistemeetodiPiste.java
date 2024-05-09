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
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(getMassiivPealeOperatsiooni().getTööalaAlgusIndeks() == null || getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks() == null) {
            return new PistemeetodiTööalaValimineVõiMuutmine(0, 1, this.getMassiivPealeOperatsiooni());
        }
        if(this.getMassiivPealeOperatsiooni().getMassiiv().length == this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()) {
            return new LäbimänguLõpetamine(this.getMassiivPealeOperatsiooni());
        }
        return new PistemeetodiTööalaValimineVõiMuutmine(this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()+1, this.getMassiivPealeOperatsiooni());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getMassiivPealeOperatsiooni().getTööalaAlgusIndeks() == null || getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks() == null) {
            return true;
        }
        if(!MassiiviTööriistad.kasOnÕigedElemendidKuniIndeksini(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks());
    }
}

