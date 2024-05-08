import main.Hindamistulemus;
import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.läbimänguHindajad.LäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Testid {
    Random random = new Random(2);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 1;
    int massiivisElemente = 7;
    int maxVäärtus = 20;

    MassiiviSeis looUusMassiiviSeis() {
        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxVäärtus);
        }
        return new MassiiviSeis(massiiv, null, null);
    }

    //TODO eemaldada siit sõna sorteerimine kuna valiku kiirmeetod ei sorteeri
    abstract LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon();

    abstract List<Massiivioperatsioon> kõikvõimalikudValedKäigud(MassiiviSeis massiiviSeis);

    List<Massiivioperatsioon> sorteeriEdasi(List<Massiivioperatsioon> praeguseniTehtudOperatsioonid, Massiivioperatsioon järgmineKäik) {
        //TODO kontrollida kas ikka tehakse eraldi
        List<Massiivioperatsioon> tehtudOperatsioonid = new ArrayList<>(praeguseniTehtudOperatsioonid);
        tehtudOperatsioonid.add(järgmineKäik);

        Massiivioperatsioon viimaneKäik = järgmineKäik;
        while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            viimaneKäik = viimaneKäik.järgmineÕigeOperatsioon();
            tehtudOperatsioonid.add(viimaneKäik);
        }
        return tehtudOperatsioonid;
    }

    @org.junit.jupiter.api.Test
    void valeKäiguTegemineTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud.add(viimaneKäik);
            viimaneKäik = viimaneKäik.järgmineÕigeOperatsioon();
            käigud.add(viimaneKäik);

            //TODO praegu ma testin ainult nii et esimese käigu järel teen jama. tegelikult teise käigu kuna muidu tuleb null
            List<Massiivioperatsioon> võimalikudValedKäigud = kõikvõimalikudValedKäigud(viimaneKäik.getMassiivPealeOperatsiooni());
            for (Massiivioperatsioon võimalikValeKäik : võimalikudValedKäigud) {

                List<Massiivioperatsioon> täielikSorteerimine = sorteeriEdasi(käigud, võimalikValeKäik);
                Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(täielikSorteerimine);
                System.out.println(hindamistulemus);


                System.out.println("------");
            }

        }
    }

    @org.junit.jupiter.api.Test
    void sorteerimineTöötabTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud = sorteeriEdasi(käigud, viimaneKäik);

            /*for (Massiivioperatsioon massiivioperatsioon : käigud) {
                System.out.println(massiivioperatsioon);
            }
            System.out.println("--");*/

            int[] eeldatavastiSorteeritudMassiiv = käigud.get(käigud.size()-1).getMassiivPealeOperatsiooni().getMassiiv();

            if(MassiiviTööriistad.kasSorteerimata(eeldatavastiSorteeritudMassiiv)) {
                throw new RuntimeException(String.format("Viga massiivi %s sorteerimisel", käigud.get(0).getMassiivPealeOperatsiooni()));
            }

        }
    }


}
