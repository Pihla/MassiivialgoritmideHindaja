import main.läbimänguHindaja.Hindamistulemus;
import main.massiiviSeis.MassiiviSeis;
import main.läbimänguHindaja.LäbimänguHindaja;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.MassiiviTööriistad.jätkaLäbimängu;
import static main.MassiiviTööriistad.kasÕigeTulemus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Testid {
    //kommentaar
    Random random = new Random(3);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 100;
    int massiivisElemente = 6;
    int maxJuhuslikVäärtus = 20;


    @org.junit.jupiter.api.Test
    void korrektneLäbimängTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            käigud = jätkaLäbimängu(käigud, viimaneKäik);

            Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(käigud);
            assertEquals(0, hindamistulemus.getValedeKäikudeArv());
            assertEquals(1, hindamistulemus.arvutaPunktid());

            MassiiviSeis algoritmiTulemus = käigud.get(käigud.size() - 1).getSeis();

            if (!kasÕigeTulemus(algoritmiTulemus)) {
                throw new RuntimeException(String.format("Viga massiivi %s ümber järjestamisel", käigud.get(0).getSeis()));
            }
        }
    }


    MassiiviSeis looUusMassiiviSeis() {
        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxJuhuslikVäärtus);
        }
        return new MassiiviSeis(massiiv, null, null);
    }

    abstract LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon();

    abstract List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis);


    @org.junit.jupiter.api.Test
    void läbimängPealeValetKäikuTest() {
        for (int i = 0; i < katseteKordusi; i++) {
            boolean esinebOlulineViga = false;
            boolean esinebMitteolulineViga = false;
            boolean esinebLahendusOodatudRaskusparameetriga = false;
            boolean esinebLahendusMitteOodatudRaskusparameetriga = false;
            boolean esinebLahendusMisSaiPunkte = false;

            List<Massiivioperatsioon> õigedKäigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();
            õigedKäigud.add(viimaneKäik);
            Massiivioperatsioon järgmineÕigeKäik;
            //teen järjest õigeid käike
            while (!(viimaneKäik instanceof LäbimänguLõpetamine)) {
                järgmineÕigeKäik = viimaneKäik.järgmineÕigeKäik();
                List<Massiivioperatsioon> võimalikudKäigud = kõikvõimalikudKäigud(viimaneKäik.getSeis());

                //peale iga õige käigu tegemist vaatan läbi kõik variandid, kus teen nüüd vale käigu ja jälle edasi õigesti
                int õigeidKäikeVõimalikeKäikudeSeas = 0;
                for (Massiivioperatsioon võimalikKäik : võimalikudKäigud) {
                    if (võimalikKäik.equals(järgmineÕigeKäik)) {
                        õigeidKäikeVõimalikeKäikudeSeas++;
                        continue;
                    }

                    List<Massiivioperatsioon> lõpuniJärjestamine1Veaga = jätkaLäbimängu(õigedKäigud, võimalikKäik);
                    Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(lõpuniJärjestamine1Veaga);

                    assertEquals(1, hindamistulemus.getValedeKäikudeArv());
                    assertTrue(hindamistulemus.arvutaPunktid() < 1);

                    if (hindamistulemus.getOluliseVeaIndeks() == null) {
                        esinebMitteolulineViga = true;
                        MassiiviSeis algoritmiTulemus1Veaga = lõpuniJärjestamine1Veaga.get(lõpuniJärjestamine1Veaga.size() - 1).getSeis();
                        if (!kasÕigeTulemus(algoritmiTulemus1Veaga)) {
                            throw new RuntimeException(String.format("Viga massiivi %s ümber järjestamisel: %s on vale tulemus", lõpuniJärjestamine1Veaga.get(0).getSeis(), Arrays.toString(algoritmiTulemus1Veaga.getMassiiv())));
                        }
                    } else {
                        esinebOlulineViga = true;
                        MassiiviSeis algoritmiTulemus1Veaga = lõpuniJärjestamine1Veaga.get(lõpuniJärjestamine1Veaga.size() - 1).getSeis();
                        if (kasÕigeTulemus(algoritmiTulemus1Veaga)) {
                            throw new RuntimeException(String.format("Massiivi %s ümber järjestamisel saadi õige tulemus, kuigi kirjas o oluline viga", lõpuniJärjestamine1Veaga.get(0).getSeis()));
                        }
                    }
                    if (hindamistulemus.getRaskusparameeter() == hindamistulemus.getOodatudRaskusparameeter()) {
                        esinebLahendusOodatudRaskusparameetriga = true;
                    } else {
                        esinebLahendusMitteOodatudRaskusparameetriga = true;
                    }
                    esinebLahendusMisSaiPunkte = esinebLahendusMisSaiPunkte || hindamistulemus.arvutaPunktid() != 0;
                }
                assertEquals(1, õigeidKäikeVõimalikeKäikudeSeas);

                //jätkan algse käikude listi õigesti täitmist
                viimaneKäik = järgmineÕigeKäik;
                õigedKäigud.add(viimaneKäik);
            }

            //kontroll, kas ikka on võimalik erinevaid vastuseid saada
            assertTrue(esinebOlulineViga);
            assertTrue(esinebMitteolulineViga);
            assertTrue(esinebLahendusOodatudRaskusparameetriga);
            assertTrue(esinebLahendusMitteOodatudRaskusparameetriga);
            assertTrue(esinebLahendusMisSaiPunkte);
        }
    }


}
