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
    int katseteKordusi = 100;
    int massiivisElemente = 7;
    int maxVäärtus = 20;

    MassiiviSeis looUusMassiiviSeis() {
        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxVäärtus);
        }
        return new MassiiviSeis(massiiv, null, null);
    }

    abstract LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon();

    @org.junit.jupiter.api.Test
    void sorteerimineTöötabTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud.add(viimaneKäik);

            while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
                viimaneKäik = viimaneKäik.järgmineÕigeOperatsioon();
                käigud.add(viimaneKäik);
            }

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
