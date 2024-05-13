package main.massiivioperatsioon.mullimeetod;

import main.massiiviSeis.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.TööalaValimine;

public class MullimeetodiTööalaValimine extends TööalaValimine {
    public MullimeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(!MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getSeis().getTööalaleJärgnevIndeks())) {
            return MullimeetodiTööristad.leiaJärgminePiste(getSeis(), getSeis().getTööalaAlgusIndeks(), getSeis().getTööalaleJärgnevIndeks()-1);
        }
        return new LäbimänguLõpetamine(getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis());
    }
}
