package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.Piste;

import java.util.Arrays;

public class MullimeetodiPiste extends Piste {
    public MullimeetodiPiste(int pisteAlgusIndeks, int pisteLõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(pisteAlgusIndeks, pisteLõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) {
            return new MullimeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        if(!MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks())) {
            return MullimeetodiTööristad.leiaJärgminePiste(getSeis(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks()-1);
        }
        return new MullimeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) return true;
        if(!MullimeetodiTööristad.kasTööalaÜmbrusOnSorteeritud(getSeis())) {
            return false;
        }
        int vähim = MassiiviTööriistad.tööalaVähimaElemendiVäärtus(getSeis());
        for (int i = getPisteLõpuIndeks()-1; i >= getSeis().getTööalaAlgusIndeks(); i--) {
            if(vähim == getSeis().getMassiiv()[i]) {
                return true;
            }
        }
        return getSeis().getMassiiv()[getSeis().getTööalaAlgusIndeks()] == vähim;
    }
}
