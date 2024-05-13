package main.massiivioperatsioon.pistemeetod;


import main.massiiviSeis.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.Piste;

public class PistemeetodiPiste extends Piste {
    public PistemeetodiPiste(int algusIndeks, int lõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(algusIndeks, lõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return new PistemeetodiTööalaValimine(0, 1, this.getSeis());
        }
        if(this.getSeis().getMassiiv().length == this.getSeis().getTööalaleJärgnevIndeks()) {
            return new LäbimänguLõpetamine(this.getSeis());
        }
        return new PistemeetodiTööalaValimine(this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks()+1, this.getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return true;
        }
        if(MassiiviTööriistad.esinevadValedElemendidEnneIndeksit(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks());
    }
}

