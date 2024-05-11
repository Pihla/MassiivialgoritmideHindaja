package main.massiivioperatsioonid.valikumeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimine;

public class ValikumeetodiTööalaValimine extends TööalaValimine {
    public ValikumeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        System.out.println("otsin järgmist õiget käiku peala tööala valimist ");
        if(getSeis().getTööalaleJärgnevIndeks() - getSeis().getTööalaAlgusIndeks() == 1) {
            return new LäbimänguLõpetamine(getSeis());
        }
        if(MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis())==getSeis().getTööalaAlgusIndeks()) {
            return new ValikumeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks()+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
        }
        return new ValikumeetodiElementideVahetamine(getSeis().getTööalaAlgusIndeks(), MassiiviTööriistad.tööalaAlgusestVähimaElemendiIndeks(getSeis()), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return MassiiviTööriistad.kasTööalaÜmbrusOnSorteeritud(getSeis());
    }
}
