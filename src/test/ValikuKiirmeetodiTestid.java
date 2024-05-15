import main.MassiiviTööriistad;
import main.massiiviSeis.MassiiviSeis;
import tööriistad.IndeksiteGenereerimine;
import tööriistad.VõimalikudMassiiviJärjestused;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.läbimänguHindaja.ValikuKiirmeetodiLäbimänguHindaja;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiLahkmeJärgiJaotamine;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiTööalaValimine;

import java.util.ArrayList;
import java.util.List;

public class ValikuKiirmeetodiTestid extends Testid{

    int otsitavateElementideArv = 2;
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

        //Läbimängu lõpetamine
        võimalikudKäigud.add(new LäbimänguLõpetamine(massiiviSeis));

        //Lahkme järgi jaotamine
        int[][] massiiviJärjestused = new VõimalikudMassiiviJärjestused().leiaVõimalikudJärjestused(valikuKiirmeetodiMassiiviSeis.getMassiiv());
        for (int[] järjestus : massiiviJärjestused) {
            ValikuKiirmeetodiMassiiviSeis uusMassiiviSeis = new ValikuKiirmeetodiMassiiviSeis(järjestus, massiiviSeis.getTööalaAlgusIndeks(), massiiviSeis.getTööalaleJärgnevIndeks(), ((ValikuKiirmeetodiMassiiviSeis) massiiviSeis).getVastusePiir());
            for (int i = 0; i <= massiiviSeis.getMassiiv().length; i++) {
                võimalikudKäigud.add(new ValikuKiirmeetodiLahkmeJärgiJaotamine(uusMassiiviSeis, i));
            }
        }

        //Tööala valimine
        List<MassiiviTööriistad.TööalaIndeksid> tööalaMuutmiseIndeksid = IndeksiteGenereerimine.leiaKõikvõimalikudTööalaValimiseIndeksid(massiiviSeis.getMassiiv().length);
        for (MassiiviTööriistad.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new ValikuKiirmeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), valikuKiirmeetodiMassiiviSeis));
        }

        return võimalikudKäigud;
    }

}
