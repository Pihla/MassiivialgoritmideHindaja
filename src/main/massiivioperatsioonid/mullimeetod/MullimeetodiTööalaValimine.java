package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimine;

public class MullimeetodiTööalaValimine extends TööalaValimine {
    public MullimeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaLõpp, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaLõpp, massiivEnneOperatsiooni);
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
        return MullimeetodiTööristad.kasTööalaÜmbrusOnSorteeritud(getSeis());
    }
}
