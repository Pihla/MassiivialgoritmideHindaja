package main.massiivioperatsioonid.valikuKiirmeetod;

import main.MassiiviSeis;
import main.MassiiviTööriistad;
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
        if(getSeis().getTööalaAlgusIndeks() == oodatavPikkus) {//see on võimalik ainult siis, kui praegune samm oli vale
            return new LäbimänguLõpetamine(getSeis());
        }

        return ValikuKiirmeetodiTööriistad.leiaLahkmeJärgiJaotamine(getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(getSeis().getVastusePiir() <= getSeis().getTööalaAlgusIndeks()
                || getSeis().getVastusePiir() >= getSeis().getTööalaleJärgnevIndeks()) {//TODO kontrollida if
            return MassiiviTööriistad.kasÕigeTulemus(getSeis());
        }
        return ValikuKiirmeetodiTööriistad.kasEnneTööalaOnAinultVähimadElemendid(getSeis())
                && ValikuKiirmeetodiTööriistad.kasKõikPiiristVäiksemadOnTööalasVõiEnneSeda(getSeis());
    }
}
