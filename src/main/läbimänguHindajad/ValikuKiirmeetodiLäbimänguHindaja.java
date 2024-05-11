package main.läbimänguHindajad;

import main.MassiiviTööriistad;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiElementideVahetamine;

import java.util.List;

public class ValikuKiirmeetodiLäbimänguHindaja extends LäbimänguHindaja{
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        //lahkme järgi jagamiste arv
        //TODO kas kuidagi muudmoodi mõõta ja kas on õige?

        int raskusparameeter = 0;
        for (Massiivioperatsioon käik : tehtudKäigud) {
            if(käik instanceof ValikuKiirmeetodiElementideVahetamine valikuKiirmeetodiElementideVahetamine) {
                if(!MassiiviTööriistad.kasTööalaValimata(valikuKiirmeetodiElementideVahetamine.getSeis())
                        && valikuKiirmeetodiElementideVahetamine.getSeis().getMassiiv()[valikuKiirmeetodiElementideVahetamine.getParempoolseElemendiIndeks()]
                        == valikuKiirmeetodiElementideVahetamine.getLahe()) {
                    raskusparameeter += 1;
                }
            }
        }

        return raskusparameeter;
    }
}
