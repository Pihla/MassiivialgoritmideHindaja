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

import static org.junit.jupiter.api.Assertions.*;

public abstract class Testid {
    Random random = new Random(2);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 100;
    int massiivisElemente = 7;
    int maxJuhuslikVäärtus = 20;

    MassiiviSeis looUusMassiiviSeis() {
        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxJuhuslikVäärtus);
        }
        return new MassiiviSeis(massiiv, null, null);
    }

    //TODO eemaldada siit failist sõna sorteerimine kuna valiku kiirmeetod ei sorteeri.
    abstract LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon();

    abstract List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis);

    List<Massiivioperatsioon> sorteeriEdasi(List<Massiivioperatsioon> praeguseniTehtudkäigud, Massiivioperatsioon järgmineKäik) {
        //lisab argumendiks antud järgmise käigu ja sorteerib edasi
        List<Massiivioperatsioon> tehtudKäigud = new ArrayList<>(praeguseniTehtudkäigud);
        tehtudKäigud.add(järgmineKäik);

        Massiivioperatsioon viimaneKäik = järgmineKäik;
        if(!viimaneKäik.kasOnVõimalikLäbimänguJätkata()) { //TODO kontrollida kas seda on ikka vaja. vb while-ga jõuaks ka läbimängu lõpetamiseni
            tehtudKäigud.add(new LäbimänguLõpetamine(viimaneKäik.getMassiivPealeOperatsiooni()));
            return tehtudKäigud;
        }
        while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            viimaneKäik = viimaneKäik.järgmineÕigeKäik();
            tehtudKäigud.add(viimaneKäik);
        }
        return tehtudKäigud;
    }

    @org.junit.jupiter.api.Test
    void sorteeriminePealeValetKäikuTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            boolean esinebOlulineViga = false;
            boolean esinebMitteolulineViga = false;
            boolean esinebLahendusOodatudRaskusparameetriga = false;
            boolean esinebLahendusMitteOodatudRaskusparameetriga = false;

            List<Massiivioperatsioon> õigedKäigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            õigedKäigud.add(viimaneKäik);
            Massiivioperatsioon järgmineÕigeKäik;

            while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {//teen järjest õigeid käike
                järgmineÕigeKäik = viimaneKäik.järgmineÕigeKäik();
                List<Massiivioperatsioon> võimalikudKäigud = kõikvõimalikudKäigud(viimaneKäik.getMassiivPealeOperatsiooni());

                //peale iga õige käigu tegemist vaatan läbi kõik variandid, kus teen nüüd vale käigu ja jälle edasi õigesti
                for (Massiivioperatsioon võimalikKäik : võimalikudKäigud) {
                    if(võimalikKäik.equals(järgmineÕigeKäik)) {
                        continue;
                    }

                    List<Massiivioperatsioon> täielikSorteerimine1Veaga = sorteeriEdasi(õigedKäigud, võimalikKäik);
                    Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(täielikSorteerimine1Veaga);

                    assertEquals(1, hindamistulemus.getValedeKäikudeArv());
                    assertTrue(hindamistulemus.arvutaPunktid() < 1);

                    if(hindamistulemus.getOluliseVeaIndeks() == null) {
                        esinebOlulineViga = true;
                        int[] eeldatavastiSorteeritudMassiiv = täielikSorteerimine1Veaga.get(täielikSorteerimine1Veaga.size()-1).getMassiivPealeOperatsiooni().getMassiiv();
                        if(MassiiviTööriistad.kasSorteerimata(eeldatavastiSorteeritudMassiiv)) {
                            throw new RuntimeException(String.format("Viga massiivi %s sorteerimisel", täielikSorteerimine1Veaga.get(0).getMassiivPealeOperatsiooni()));
                        }
                    }
                    else {
                        esinebMitteolulineViga = true;
                    }
                    if(hindamistulemus.getRaskusparameeter() == hindamistulemus.getOodatudRaskusparameeter()) {
                        esinebLahendusOodatudRaskusparameetriga = true;
                    }
                    else {
                        esinebLahendusMitteOodatudRaskusparameetriga = true;
                    }
                }
                //jätkan algse käikude listi õigesti täitmist
                viimaneKäik = järgmineÕigeKäik;
                õigedKäigud.add(viimaneKäik);
            }

            //kontroll, kas ikka on võimalik erinevaid vastuseid saada
            assertTrue(esinebOlulineViga);
            assertTrue(esinebMitteolulineViga);
            assertTrue(esinebLahendusOodatudRaskusparameetriga);
            assertTrue(esinebLahendusMitteOodatudRaskusparameetriga);
        }
    }

    @org.junit.jupiter.api.Test
    void sorteerimineTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud = sorteeriEdasi(käigud, viimaneKäik);

            Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(käigud);
            assertEquals(0, hindamistulemus.getValedeKäikudeArv());
            assertEquals(1, hindamistulemus.arvutaPunktid());

            int[] eeldatavastiSorteeritudMassiiv = käigud.get(käigud.size()-1).getMassiivPealeOperatsiooni().getMassiiv();

            if(MassiiviTööriistad.kasSorteerimata(eeldatavastiSorteeritudMassiiv)) {
                throw new RuntimeException(String.format("Viga massiivi %s sorteerimisel", käigud.get(0).getMassiivPealeOperatsiooni()));
            }

        }
    }


}
