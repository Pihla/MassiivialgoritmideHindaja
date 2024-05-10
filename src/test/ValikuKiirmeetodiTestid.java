import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.läbimänguHindajad.ValikuKiirmeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiElementideVahetamine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööalaValimine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööriistad;

import java.util.ArrayList;
import java.util.List;

public class ValikuKiirmeetodiTestid extends Testid{

    int otsitavateElementideArv = 4;
    ValikuKiirmeetodiTestid() {
        this.läbimänguHindaja = new ValikuKiirmeetodiLäbimänguHindaja();
    }
    @Override
    LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon() {
        ValikuKiirmeetodiMassiiviSeis massiiviSeis = new ValikuKiirmeetodiMassiiviSeis(looUusMassiiviSeis().getMassiiv(), null, null, otsitavateElementideArv);
        return new ValikuKiirmeetodiLäbimänguAlustamine(massiiviSeis);
    }

    @Override
    List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis) {
        if(!(massiiviSeis instanceof ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis)) {
            throw new RuntimeException("Antud massiiviseis peab olema valiku kiirmeetodi oma");
        }

        List<Massiivioperatsioon> võimalikudKäigud = new ArrayList<>();

        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        List<main.IndeksiteGenereerimine.VahetatavadIndeksid> elementideVahetuseIndeksid = main.IndeksiteGenereerimine.leiaKõikVõimalikudVahetusteIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.VahetatavadIndeksid indeksitePaar : elementideVahetuseIndeksid) {
            võimalikudKäigud.add(new ValikuKiirmeetodiElementideVahetamine(indeksitePaar.vahetatav1(), indeksitePaar.vahetatav2(), valikuKiirmeetodiMassiiviSeis, this.otsitavateElementideArv));
        }

        List<main.IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = main.IndeksiteGenereerimine.leiaKõikvõimalikudTööalaMuutmiseIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new ValikuKiirmeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), valikuKiirmeetodiMassiiviSeis));
        }

        return võimalikudKäigud;
    }

}
