import main.MassiiviSeis;
import main.läbimänguHindajad.PistemeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiTööalaValimine;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiTööalaValimine;

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

        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        List<main.IndeksiteGenereerimine.PisteIndeksid> pisteteIndeksid = main.IndeksiteGenereerimine.leiaKõikVõimalikudPisteIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.PisteIndeksid indeksitePaar : pisteteIndeksid) {
            võimalikudKäigud.add(new PistemeetodiPiste(indeksitePaar.algus(), indeksitePaar.lõpp(), massiiviSeis));
        }

        List<main.IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = main.IndeksiteGenereerimine.leiaKõikvõimalikudTööalaMuutmiseIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new PistemeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }

}
