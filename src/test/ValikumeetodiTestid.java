import main.MassiiviSeis;
import main.läbimänguHindajad.ValikumeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiElementideVahetamine;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiTööalaValimine;

import java.util.ArrayList;
import java.util.List;

public class ValikumeetodiTestid extends Testid{
    ValikumeetodiTestid() {
        this.läbimänguHindaja = new ValikumeetodiLäbimänguHindaja();
    }
    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        return new ValikumeetodiLäbimänguAlustamine(looUusMassiiviSeis());
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        //Elementide vahetused eeldusega, et vahetatavad elemendid on erinevad
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = 0; j < massiiviSeis.getMassiiv().length; j++) {
                if(i != j) {
                    võimalikudKäigud.add(new ValikumeetodiElementideVahetamine(i, j, massiiviSeis));
                }
            }
        }

        //Tööala muutmised eeldusega, et lõpuindeks > algusindeks
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = i+1; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new ValikumeetodiTööalaValimine(i, j, massiiviSeis));
            }
        }

        return võimalikudKäigud;
    }
}
