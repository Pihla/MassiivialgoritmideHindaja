import main.läbimänguHindaja.MullimeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioon.mullimeetod.MullimeetodiTööalaValimine;
import tooriistad.IndeksiteGenereerimine;

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

        // Läbimängu lõpetamine
        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        // Piste tegemine
        List<IndeksiteGenereerimine.PisteIndeksid> pisteteIndeksid = IndeksiteGenereerimine.leiaKõikVõimalikudPisteIndeksid(massiiviSeis.getMassiiv().length);
        for (IndeksiteGenereerimine.PisteIndeksid indeksitePaar : pisteteIndeksid) {
            võimalikudKäigud.add(new MullimeetodiPiste(indeksitePaar.algus(), indeksitePaar.lõpp(), massiiviSeis));
        }

        // Tööala valimine
        List<IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = IndeksiteGenereerimine.leiaKõikvõimalikudTööalaValimiseIndeksid(massiiviSeis.getMassiiv().length);
        for (IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new MullimeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }

        return võimalikudKäigud;
    }
}
