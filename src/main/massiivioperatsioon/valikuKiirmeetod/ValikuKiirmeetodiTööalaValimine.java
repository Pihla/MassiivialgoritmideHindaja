package main.massiivioperatsioon.valikuKiirmeetod;

import main.massiiviSeis.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.TööalaValimine;

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
        if(getSeis().getVastusePiir() < getSeis().getTööalaAlgusIndeks()
                || getSeis().getVastusePiir() >= getSeis().getTööalaleJärgnevIndeks()) {//see on võimalik ainult vea korral
            return new LäbimänguLõpetamine(getSeis());
        }
        int oodatavPikkus = getSeis().getVastusePiir();
        if(getSeis().getTööalaAlgusIndeks() == oodatavPikkus) {//see on võimalik ainult siis, kui praegune samm oli vale
            return ValikuKiirmeetodiTööriistad.leiaLahkmeJärgiJaotamine(getSeis());
            //throw  new RuntimeException("koht");
            //return new LäbimänguLõpetamine(getSeis());
        }//TODO kontrollida kas ikka pole vaja

        return ValikuKiirmeetodiTööriistad.leiaLahkmeJärgiJaotamine(getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getSeis().getVastusePiir() < getSeis().getTööalaAlgusIndeks()
                || getSeis().getVastusePiir() >= getSeis().getTööalaleJärgnevIndeks()) {
            return MassiiviTööriistad.kasÕigeTulemus(getSeis());
        }
        return ValikuKiirmeetodiTööriistad.kasEnneTööalaOnAinultVähimadElemendid(getSeis())
                && ValikuKiirmeetodiTööriistad.kasKõikPiiristVäiksemadOnTööalasVõiEnneSeda(getSeis());
    }
}
