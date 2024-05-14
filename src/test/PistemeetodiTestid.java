import main.MassiiviTööriistad;
import main.massiiviSeis.MassiiviSeis;
import main.läbimänguHindaja.PistemeetodiLäbimänguHindaja;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioon.pistemeetod.PistemeetodiTööalaValimine;
import tööriistad.IndeksiteGenereerimine;

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

        //Läbimängu lõpetamine
        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        //Piste tegemine
        List<MassiiviTööriistad.PisteIndeksid> pisteteIndeksid = IndeksiteGenereerimine.leiaKõikVõimalikudPisteIndeksid(massiiviSeis.getMassiiv().length);
        for (MassiiviTööriistad.PisteIndeksid indeksitePaar : pisteteIndeksid) {
            võimalikudKäigud.add(new PistemeetodiPiste(indeksitePaar.algus(), indeksitePaar.lõpp(), massiiviSeis));
        }

        //Tööala valimine
        List<MassiiviTööriistad.TööalaIndeksid> tööalaMuutmiseIndeksid = IndeksiteGenereerimine.leiaKõikvõimalikudTööalaValimiseIndeksid(massiiviSeis.getMassiiv().length);
        for (MassiiviTööriistad.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new PistemeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }

}
