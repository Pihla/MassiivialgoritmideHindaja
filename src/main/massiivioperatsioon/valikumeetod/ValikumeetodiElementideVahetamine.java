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
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {//see on võimalik ainult vea korral
            return new ValikumeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }
        if(getSeis().getTööalaleJärgnevIndeks() - getSeis().getTööalaAlgusIndeks() == 1) {//see on võimalik ainult vea korral
            return new LäbimänguLõpetamine(getSeis());
        }
        return new ValikumeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    //TODO terve valiku kiirmeetod üle kontrolldia
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
