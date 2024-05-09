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
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) {
            return new PistemeetodiTööalaValimine(0, 1, this.getSeis());
        }
        if(this.getSeis().getMassiiv().length == this.getSeis().getTööalaleJärgnevIndeks()) {
            return new LäbimänguLõpetamine(this.getSeis());
        }
        return new PistemeetodiTööalaValimine(this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks()+1, this.getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) {
            return true;
        }
        if(!MassiiviTööriistad.kasOnÕigedElemendidKuniIndeksini(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks());
    }
}

