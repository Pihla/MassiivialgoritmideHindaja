import main.IndeksiteGenereerimine;
import main.MassiiviSeis;
import main.läbimänguHindajad.MullimeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiTööalaValimine;


import java.util.ArrayList;
import java.util.List;

public class MullimeetodiTestid extends Testid {
    MullimeetodiTestid() {
        this.läbimänguHindaja = new MullimeetodiLäbimänguHindaja();
    }

    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        return new MullimeetodiLäbimänguAlustamine(looUusMassiiviSeis());
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));


        List<main.IndeksiteGenereerimine.PisteIndeksid> pisteteIndeksid = main.IndeksiteGenereerimine.leiaKõikVõimalikudPisteIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.PisteIndeksid indeksitePaar : pisteteIndeksid) {
            võimalikudKäigud.add(new MullimeetodiPiste(indeksitePaar.algus(), indeksitePaar.lõpp(), massiiviSeis));
        }

        List<main.IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = main.IndeksiteGenereerimine.leiaKõikvõimalikudTööalaMuutmiseIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new MullimeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }
}
