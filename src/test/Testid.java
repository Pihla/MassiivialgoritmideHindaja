import main.Hindamistulemus;
import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.läbimänguHindajad.LäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public abstract class Testid {
    //kommentaar
    Random random = new Random(2);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 1;
    int massiivisElemente = 7;
    int maxJuhuslikVäärtus = 20;


    @org.junit.jupiter.api.Test
    void sorteerimineTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud = sorteeriEdasi(käigud, viimaneKäik);

            Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(käigud);
            assertEquals(0, hindamistulemus.getValedeKäikudeArv());
            assertEquals(1, hindamistulemus.arvutaPunktid());

            int[] eeldatavastiSorteeritudMassiiv = käigud.get(käigud.size()-1).getSeis().getMassiiv();

            if(MassiiviTööriistad.kasSorteerimata(eeldatavastiSorteeritudMassiiv)) {
                throw new RuntimeException(String.format("Viga massiivi %s sorteerimisel", käigud.get(0).getSeis()));
            }

            /*for (Massiivioperatsioon massiivioperatsioon : käigud) {
                System.out.println(massiivioperatsioon);
            }*/

        }
    }


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
            tehtudKäigud.add(new LäbimänguLõpetamine(viimaneKäik.getSeis()));
            //System.out.println("ei ole võimalik läbimängu järkata");
            return tehtudKäigud;
        }
        //System.out.println("on võimalik läbimängu jätkata");
        while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            viimaneKäik = viimaneKäik.järgmineÕigeKäik();
            //System.out.println(viimaneKäik);
            tehtudKäigud.add(viimaneKäik);
        }
        //System.out.println("--");
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
                List<Massiivioperatsioon> võimalikudKäigud = kõikvõimalikudKäigud(viimaneKäik.getSeis());

                //peale iga õige käigu tegemist vaatan läbi kõik variandid, kus teen nüüd vale käigu ja jälle edasi õigesti
                for (Massiivioperatsioon võimalikKäik : võimalikudKäigud) {
                    if(võimalikKäik.equals(järgmineÕigeKäik)) {
                        continue;
                    }

                    //System.out.println("teen vea " + võimalikKäik);

                    List<Massiivioperatsioon> täielikSorteerimine1Veaga = sorteeriEdasi(õigedKäigud, võimalikKäik);
                    Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(täielikSorteerimine1Veaga);

                    assertEquals(1, hindamistulemus.getValedeKäikudeArv());
                    assertTrue(hindamistulemus.arvutaPunktid() < 1);

                    /*System.out.println("---algus");
                    for (Massiivioperatsioon massiivioperatsioon : täielikSorteerimine1Veaga) {
                        System.out.println(massiivioperatsioon);
                    }
                    System.out.println("---lõpp");*/

                    if(hindamistulemus.getOluliseVeaIndeks() == null) {
                        esinebOlulineViga = true;
                        int[] eeldatavastiSorteeritudMassiiv = täielikSorteerimine1Veaga.get(täielikSorteerimine1Veaga.size()-1).getSeis().getMassiiv();
                        if(MassiiviTööriistad.kasSorteerimata(eeldatavastiSorteeritudMassiiv)) {
                            throw new RuntimeException(String.format("Viga massiivi %s sorteerimisel: %s pole sorteeritud", täielikSorteerimine1Veaga.get(0).getSeis(), Arrays.toString(eeldatavastiSorteeritudMassiiv)));
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



}
