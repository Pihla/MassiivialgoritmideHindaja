import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.Permutatsioonid;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.läbimänguHindajad.ValikuKiirmeetodiLäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiLahkmeJärgiJaotamine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööalaValimine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööriistad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValikuKiirmeetodiTestid extends Testid{

    int otsitavateElementideArv = 5;
    int permutatsioonideArv = 10;
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
        int[][] massiiviPermutatsioonid = new Permutatsioonid().genereeriPermutatsioonid(valikuKiirmeetodiMassiiviSeis.getMassiiv());
        for (int[] perm : massiiviPermutatsioonid) {
            ValikuKiirmeetodiMassiiviSeis uusMassiiviSeis = new ValikuKiirmeetodiMassiiviSeis(perm, massiiviSeis.getTööalaAlgusIndeks(), massiiviSeis.getTööalaleJärgnevIndeks(), ((ValikuKiirmeetodiMassiiviSeis) massiiviSeis).getVastusePiir());
            for (int i = 0; i <= massiiviSeis.getMassiiv().length; i++) {
                võimalikudKäigud.add(new ValikuKiirmeetodiLahkmeJärgiJaotamine(uusMassiiviSeis, i));
            }
        }

        /*for (int i = 0; i < permutatsioonideArv; i++) {
            List<Integer> arvudeList = new ArrayList<Integer>(massiiviSeis.getMassiiv().length);
            for (int j : massiiviSeis.getMassiiv()) {
                arvudeList.add(j);
            }
            Collections.shuffle(arvudeList);
            int[] permutatsioon = new int[massiiviSeis.getMassiiv().length];
            for (int j = 0; j < arvudeList.size(); j++) {
                permutatsioon[j] = arvudeList.get(j);
            }
            ValikuKiirmeetodiMassiiviSeis uusMassiiviSeis = new ValikuKiirmeetodiMassiiviSeis(permutatsioon, massiiviSeis.getTööalaAlgusIndeks(), massiiviSeis.getTööalaleJärgnevIndeks(), ((ValikuKiirmeetodiMassiiviSeis) massiiviSeis).getVastusePiir());
            for (int k = 0; k <= massiiviSeis.getMassiiv().length; k++) {
                võimalikudKäigud.add(new ValikuKiirmeetodiLahkmeJärgiJaotamine(uusMassiiviSeis, k));
            }

        }*/


        List<main.IndeksiteGenereerimine.TööalaIndeksid> tööalaMuutmiseIndeksid = main.IndeksiteGenereerimine.leiaKõikvõimalikudTööalaMuutmiseIndeksid(massiiviSeis.getMassiiv().length);
        for (main.IndeksiteGenereerimine.TööalaIndeksid indeksitePaar : tööalaMuutmiseIndeksid) {
            võimalikudKäigud.add(new ValikuKiirmeetodiTööalaValimine(indeksitePaar.algus(), indeksitePaar.lõpustJärgmine(), valikuKiirmeetodiMassiiviSeis));
        }

        return võimalikudKäigud;
    }

}
