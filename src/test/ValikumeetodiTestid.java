import main.MassiiviSeis;
import main.läbimänguHindajad.ValikumeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
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

        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        List<main.IndeksiteGenereerimine.VahetatavadIndeksid> elementideVahetuseIndeksid = main.IndeksiteGenereerimine.leiaKõikVõimalikudVahetusteIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.VahetatavadIndeksid indeksitePaar : elementideVahetuseIndeksid) {
            võimalikudKäigud.add(new ValikumeetodiElementideVahetamine(indeksitePaar.vahetatav1(), indeksitePaar.vahetatav2(), massiiviSeis));
        }

        List<main.IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = main.IndeksiteGenereerimine.leiaKõikvõimalikudTööalaMuutmiseIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new ValikumeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }
}
