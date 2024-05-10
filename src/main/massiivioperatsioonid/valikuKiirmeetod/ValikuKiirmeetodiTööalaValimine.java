package main.massiivioperatsioonid.valikuKiirmeetod;

import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimine;

public class ValikuKiirmeetodiTööalaValimine extends TööalaValimine {
    public ValikuKiirmeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
        this.seis = massiivEnneOperatsiooni.teeKoopia();//TODO
    }

    ValikuKiirmeetodiMassiiviSeis seis;
    @Override
    public ValikuKiirmeetodiMassiiviSeis getSeis() {
        return seis;
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        int lahe = getSeis().getMassiiv()[getSeis().getTööalaAlgusIndeks()];
        return ValikuKiirmeetodiTööriistad.leiaJärgmineÕigeKäik(getSeis(), lahe, getSeis().getTööalaAlgusIndeks() +1, getSeis().getTööalaleJärgnevIndeks()-2);
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return ValikuKiirmeetodiTööriistad.kasEnneTööalaOnAinultVähimadElemendid(getSeis())
                && ValikuKiirmeetodiTööriistad.kasKõikPiiiristVäiksemadOnTööalasVõiEnneSeda(getSeis());
    }
}
