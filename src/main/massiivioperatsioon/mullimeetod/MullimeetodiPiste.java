package main.massiivioperatsioon.mullimeetod;

import main.MassiiviTööriistad;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.Piste;

public class MullimeetodiPiste extends Piste {
    public MullimeetodiPiste(int pisteAlgusIndeks, int pisteLõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(pisteAlgusIndeks, pisteLõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if (getSeis().kasTööalaValimata()) { // see on võimalik ainult vea korral
            return new MullimeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        if (!MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks())) {
            return MullimeetodiTööriistad.leiaJärgminePiste(getSeis(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks() - 1);
        }
        if (getSeis().getTööalaleJärgnevIndeks() - getSeis().getTööalaAlgusIndeks() == 1) { // see on võimalik ainult vea korral
            return new LäbimänguLõpetamine(getSeis());
        }
        return new MullimeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks() + 1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean läbimänguOnVõimalikJätkata() {
        if (getSeis().kasTööalaValimata()) return true;
        if (!MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis())) {
            return false;
        }
        int vähim = getSeis().getMassiiv()[MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis())];
        for (int i = getPisteLõpuIndeks() - 1; i >= getSeis().getTööalaAlgusIndeks(); i--) {
            if (vähim == getSeis().getMassiiv()[i]) {
                return true;
            }
        }
        return getSeis().getMassiiv()[getSeis().getTööalaAlgusIndeks()] == vähim;
    }
}
