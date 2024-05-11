package main.massiivioperatsioonid.valikuKiirmeetod;

import main.MassiiviSeis;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimine;

public class ValikuKiirmeetodiTööalaValimine extends TööalaValimine {
    public ValikuKiirmeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
    }

    ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis;
    @Override
    public ValikuKiirmeetodiMassiiviSeis getSeis() {
        return valikuKiirmeetodiMassiiviSeis;
    }

    @Override
    public void setSeis(MassiiviSeis seis) {
        if(seis instanceof ValikuKiirmeetodiMassiiviSeis uusSeis) {
            this.valikuKiirmeetodiMassiiviSeis = uusSeis;
        }
        else {
            throw new RuntimeException("valikukiirmeetodi seis peab olema valikukiirmeetodiseisu isend");
        }
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        int oodatavPikkus = getSeis().getVastusePiir();
        if(getSeis().getTööalaAlgusIndeks() == oodatavPikkus) {//TODO konstrollida kas selline ais on loogiline
            return new LäbimänguLõpetamine(getSeis());
        }


        return ValikuKiirmeetodiTööriistad.leiaLahkmeJärgiJaotamine(getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return ValikuKiirmeetodiTööriistad.kasEnneTööalaOnAinultVähimadElemendid(getSeis())
                && ValikuKiirmeetodiTööriistad.kasKõikPiiiristVäiksemadOnTööalasVõiEnneSeda(getSeis());
    }
}
