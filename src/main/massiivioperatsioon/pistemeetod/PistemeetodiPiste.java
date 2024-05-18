package main.massiivioperatsioon.pistemeetod;


import main.MassiiviTööriistad;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.Piste;

public class PistemeetodiPiste extends Piste {
    public PistemeetodiPiste(int pisteAlgusIndeks, int pisteLõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(pisteAlgusIndeks, pisteLõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if (getSeis().kasTööalaValimata()) { // see on võimalik ainult vea korral
            return new PistemeetodiTööalaValimine(0, 1, getSeis());
        }
        if (getSeis().getMassiiv().length == getSeis().getTööalaleJärgnevIndeks()) {
            return new LäbimänguLõpetamine(getSeis());
        }
        return new PistemeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks(), getSeis().getTööalaleJärgnevIndeks() + 1, getSeis());
    }

    @Override
    public boolean läbimänguOnVõimalikJätkata() {
        if (getSeis().kasTööalaValimata()) {
            return true;
        }
        if (PistemeetodiTööriistad.valedElemendidEnneIndeksit(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getSeis().getTööalaleJärgnevIndeks());
    }
}

