import main.MassiiviSeis;
import main.läbimänguHindajad.PistemeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiTööalaValimine;

import java.util.ArrayList;
import java.util.List;

public class PistemeetodiTestid extends Testid {

    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        this.läbimänguHindaja = new PistemeetodiLäbimänguHindaja();
        //TODO vaadata kas saab selle läbimänguhindaja ära võtta siit ja teistest klassidest ja teistest meetoditest
        return new PistemeetodiLäbimänguAlustamine(looUusMassiiviSeis());
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        this.läbimänguHindaja = new PistemeetodiLäbimänguHindaja();
        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        //Pisted
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = 0; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new PistemeetodiPiste(i, j, massiiviSeis));
            }
        }

        //Tööala muutmised eeldusega, et lõpuindeks > algusindeks
        for (int i = 0; i < massiiviSeis.getMassiiv().length; i++) {
            for (int j = i+1; j < massiiviSeis.getMassiiv().length; j++) {
                võimalikudKäigud.add(new PistemeetodiTööalaValimine(i, j, massiiviSeis));
            }
        }

        return võimalikudKäigud;
    }

}
