import main.MassiiviSeis;
import main.läbimänguHindajad.MullimeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiTööalaValimine;

import java.util.ArrayList;
import java.util.List;

public class MullimeetodiTestid extends Testid {

    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        this.läbimänguHindaja = new MullimeetodiLäbimänguHindaja();
        return new MullimeetodiLäbimänguAlustamine(looUusMassiiviSeis());
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        this.läbimänguHindaja = new MullimeetodiLäbimänguHindaja();
        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        //Pisted
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = 0; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new MullimeetodiPiste(i, j, massiiviSeis));
            }
        }

        //Tööala muutmised eeldusega, et lõpuindeks > algusindeks
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = i+1; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new MullimeetodiTööalaValimine(i, j, massiiviSeis));
            }
        }

        return võimalikudKäigud;
    }
}
