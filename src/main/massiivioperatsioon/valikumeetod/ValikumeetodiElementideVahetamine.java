package main.massiivioperatsioon.valikumeetod;

import main.massiiviSeis.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioon.ElementideVahetamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;

public class ValikumeetodiElementideVahetamine extends ElementideVahetamine {
    public ValikumeetodiElementideVahetamine(int esimeseElemendiIndeks, int teiseElemendiIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(esimeseElemendiIndeks, teiseElemendiIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return new ValikumeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        if(getSeis().getTööalaleJärgnevIndeks()- getSeis().getTööalaAlgusIndeks() == 1) {
            return new LäbimänguLõpetamine(getSeis());
        }
        return new ValikumeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return true;
        }
        if (!MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis())) {
            return false;
        }
        return MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis()) == getSeis().getTööalaAlgusIndeks();
    }
}