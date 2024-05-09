import main.MassiiviSeis;
import main.läbimänguHindajad.PistemeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiTööalaValimineVõiMuutmine;

import java.util.ArrayList;
import java.util.List;

public class PistemeetodiTestid extends Testid {
    PistemeetodiTestid() {
        this.läbimänguHindaja = new PistemeetodiLäbimänguHindaja();
    }

    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        return new PistemeetodiLäbimänguAlustamine(looUusMassiiviSeis());
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        //Pisted
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = 0; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new PistemeetodiPiste(i, j, massiiviSeis));
            }
        }

        //Tööala muutmised eeldusega, et lõpuindeks >= algusindeks
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = i; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new PistemeetodiTööalaValimineVõiMuutmine(i, j, massiiviSeis));
            }
        }

        return võimalikudKäigud;
    }

}
