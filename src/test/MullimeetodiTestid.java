import main.MassiiviTööriistad;
import tööriistad.IndeksiteGenereerimine;
import main.massiiviSeis.MassiiviSeis;
import main.läbimänguHindaja.MullimeetodiLäbimänguHindaja;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioon.mullimeetod.MullimeetodiTööalaValimine;


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

        //Läbimängu lõpetamine
        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        //Piste tegemine
        List<MassiiviTööriistad.PisteIndeksid> pisteteIndeksid = IndeksiteGenereerimine.leiaKõikVõimalikudPisteIndeksid(massiiviSeis.getMassiiv().length);
        for (MassiiviTööriistad.PisteIndeksid indeksitePaar : pisteteIndeksid) {
            võimalikudKäigud.add(new MullimeetodiPiste(indeksitePaar.algus(), indeksitePaar.lõpp(), massiiviSeis));
        }

        //Tööala valimine
        List<MassiiviTööriistad.TööalaIndeksid> tööalaMuutmiseIndeksid = IndeksiteGenereerimine.leiaKõikvõimalikudTööalaValimiseIndeksid(massiiviSeis.getMassiiv().length);
        for (MassiiviTööriistad.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new MullimeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }
}
