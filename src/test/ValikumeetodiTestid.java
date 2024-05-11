import main.MassiiviSeis;
import main.läbimänguHindajad.ValikumeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiElementideVahetamine;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.valikumeetod.ValikumeetodiTööalaValimine;
import main.IndeksiteGenereerimine;

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

        //Läbimängu lõpetamine
        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        //Elementide vahetamine
        List<IndeksiteGenereerimine.VahetatavadIndeksid> elementideVahetuseIndeksid = IndeksiteGenereerimine.leiaKõikVõimalikudVahetusteIndeksid(massiiviSeis.getMassiiv().length);
        for (IndeksiteGenereerimine.VahetatavadIndeksid indeksitePaar : elementideVahetuseIndeksid) {
            võimalikudKäigud.add(new ValikumeetodiElementideVahetamine(indeksitePaar.vahetatav1(), indeksitePaar.vahetatav2(), massiiviSeis));
        }

        //Tööala valimine
        List<IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = IndeksiteGenereerimine.leiaKõikvõimalikudTööalaValimiseIndeksid(massiiviSeis.getMassiiv().length);
        for (IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new ValikumeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), massiiviSeis));
        }
        return võimalikudKäigud;
    }
}
