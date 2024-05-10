package main.massiivioperatsioonid.valikumeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.ElementideVahetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class ValikumeetodiElementideVahetamine extends ElementideVahetamine {
    public ValikumeetodiElementideVahetamine(int esimeseElemendiIndeks, int teiseElemendiIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(esimeseElemendiIndeks, teiseElemendiIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) {
            return new ValikumeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        return new ValikumeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getSeis().getTööalaAlgusIndeks() == null || getSeis().getTööalaleJärgnevIndeks() == null) {
            return true;
        }
        if (!MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis())) {
            return false;
        }
        return MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis()) == getSeis().getTööalaAlgusIndeks();
    }
}
