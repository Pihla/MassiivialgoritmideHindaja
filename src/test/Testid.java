import main.Hindamistulemus;
import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.läbimänguHindajad.LäbimänguHindaja;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööriistad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Testid {
    //kommi
    Random random = new Random(2);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 1;
    int massiivisElemente = 3;
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

            if (kasValeTulemus(algoritmiTulemus)) {
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

    List<Massiivioperatsioon> jätkaLäbimängu(List<Massiivioperatsioon> praeguseniTehtudkäigud, Massiivioperatsioon järgmineKäik) {
        //lisab argumendiks antud järgmise käigu ja teeb läbimängu edasi õigesti, kui see on võimalik
        List<Massiivioperatsioon> tehtudKäigud = new ArrayList<>(praeguseniTehtudkäigud);
        tehtudKäigud.add(järgmineKäik);

        Massiivioperatsioon viimaneKäik = järgmineKäik;
        if (!viimaneKäik.kasOnVõimalikLäbimänguJätkata()) {
            if (!(viimaneKäik instanceof LäbimänguLõpetamine)) {
                tehtudKäigud.add(new LäbimänguLõpetamine(viimaneKäik.getSeis()));
            }
            return tehtudKäigud;
        }
        while (!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            viimaneKäik = viimaneKäik.järgmineÕigeKäik();
            if (!(viimaneKäik instanceof LäbimänguLõpetamine)
                    && !viimaneKäik.kasOnVõimalikLäbimänguJätkata()) {
                throw new RuntimeException("läbimängu jätkamine peaks peale õiget käiku olema võimalik. viimane käik oli" + viimaneKäik);
            }
            tehtudKäigud.add(viimaneKäik);
        }
        return tehtudKäigud;
    }

    boolean kasValeTulemus(MassiiviSeis massiiviSeis) {
        //valiku kiirmeetodi puhul hindab kas on vähimad elemendid ees. muul juhul kas on sorteeritud.
        if (massiiviSeis instanceof ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis) {
            return ValikuKiirmeetodiTööriistad.kasVähimadElemendidPoleEes(valikuKiirmeetodiMassiiviSeis);
        }

        return !MassiiviTööriistad.kasVahemikOnSorteeritud(massiiviSeis.getMassiiv(), 0, massiiviSeis.getMassiiv().length);
    }


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
                        if (kasValeTulemus(algoritmiTulemus1Veaga)) {
                            throw new RuntimeException(String.format("Viga massiivi %s ümber järjestamisel: %s on vale tulemus", lõpuniJärjestamine1Veaga.get(0).getSeis(), Arrays.toString(algoritmiTulemus1Veaga.getMassiiv())));
                        }
                    } else {
                        esinebOlulineViga = true;
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
