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
        int lahe = getSeis().getMassiiv()[getSeis().getTööalaAlgusIndeks()];
        //System.out.println(getSeis().getTööalaAlgusIndeks() + " on algus ja vatsuse piir on " + getSeis().getVastusePiir());
        if(getSeis().getTööalaAlgusIndeks() == getSeis().getVastusePiir()
        || getSeis().getTööalaleJärgnevIndeks() == getSeis().getVastusePiir()) {
            return new LäbimänguLõpetamine(getSeis());
        }
        return ValikuKiirmeetodiTööriistad.leiaJärgmineÕigeKäik(getSeis(), lahe, getSeis().getTööalaAlgusIndeks() , getSeis().getTööalaleJärgnevIndeks()-1);
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        return ValikuKiirmeetodiTööriistad.kasEnneTööalaOnAinultVähimadElemendid(getSeis())
                && ValikuKiirmeetodiTööriistad.kasKõikPiiiristVäiksemadOnTööalasVõiEnneSeda(getSeis());
    }
}
