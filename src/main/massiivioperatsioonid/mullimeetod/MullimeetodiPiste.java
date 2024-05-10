package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.Piste;

public class MullimeetodiPiste extends Piste {
    public MullimeetodiPiste(int pisteAlgusIndeks, int pisteLõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(pisteAlgusIndeks, pisteLõpuIndeks, massiivEnnePistet);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return new MullimeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        if(!MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks())) {
            return MullimeetodiTööristad.leiaJärgminePiste(getSeis(), getSeis().getTööalaAlgusIndeks(), getPisteLõpuIndeks()-1);
        }
        return new MullimeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) return true;
        if(!MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis())) {
            return false;
        }
        int vähim = getSeis().getMassiiv()[MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis())];
        for (int i = getPisteLõpuIndeks()-1; i >= getSeis().getTööalaAlgusIndeks(); i--) {
            if(vähim == getSeis().getMassiiv()[i]) {
                return true;
            }
        }
        return getSeis().getMassiiv()[getSeis().getTööalaAlgusIndeks()] == vähim;
    }
}
