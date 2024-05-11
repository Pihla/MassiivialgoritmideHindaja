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

import static org.junit.jupiter.api.Assertions.*;

public abstract class Testid {
    //komme
    Random random = new Random(2);
    LäbimänguHindaja läbimänguHindaja;
    int katseteKordusi = 1;
    int massiivisElemente = 7;
    int maxJuhuslikVäärtus = 20;


    @org.junit.jupiter.api.Test
    void korrektneLäbimängTest() {
        System.out.println("===========\ntesti algus");
        for (int i = 0; i < katseteKordusi; i++) {
            List<Massiivioperatsioon> käigud = new ArrayList<>();

            Massiivioperatsioon viimaneKäik = uueLäbimänguAlustamiseOperatsioon();

            käigud = jätkaLäbimängu(käigud, viimaneKäik);

            for (Massiivioperatsioon massiivioperatsioon : käigud) {
                System.out.println(massiivioperatsioon);
            }

            Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(käigud);
            assertEquals(0, hindamistulemus.getValedeKäikudeArv());
            assertEquals(1, hindamistulemus.arvutaPunktid());

            System.out.println(hindamistulemus);
            MassiiviSeis algoritmiTulemus = käigud.get(käigud.size()-1).getSeis();

            if(kasValeTulemus(algoritmiTulemus)) {
                throw new RuntimeException(String.format("Viga massiivi %s ümber järjestamisel", käigud.get(0).getSeis()));
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

    abstract LäbimänguAlustamine uueLäbimänguAlustamiseOperatsioon();

    abstract List<Massiivioperatsioon> kõikvõimalikudKäigud(MassiiviSeis massiiviSeis);

    List<Massiivioperatsioon> jätkaLäbimängu(List<Massiivioperatsioon> praeguseniTehtudkäigud, Massiivioperatsioon järgmineKäik) {
        //lisab argumendiks antud järgmise käigu ja teeb läbimängu edasi
        List<Massiivioperatsioon> tehtudKäigud = new ArrayList<>(praeguseniTehtudkäigud);
        tehtudKäigud.add(järgmineKäik);
        //System.out.println(järgmineKäik);

        Massiivioperatsioon viimaneKäik = järgmineKäik;
        if(!viimaneKäik.kasOnVõimalikLäbimänguJätkata()) { //TODO kontrollida kas seda on ikka vaja. vb while-ga jõuaks ka läbimängu lõpetamiseni
            if(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
                tehtudKäigud.add(new LäbimänguLõpetamine(viimaneKäik.getSeis()));
            }
            //System.out.println("ei ole võimalik läbimängu järkata");
            return tehtudKäigud;
        }
        //System.out.println("on võimalik läbimängu jätkata");
        while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            viimaneKäik = viimaneKäik.järgmineÕigeKäik();
            if(!(viimaneKäik instanceof LäbimänguLõpetamine)
            && !viimaneKäik.kasOnVõimalikLäbimänguJätkata()) {
                throw new RuntimeException("läbimängu jätkamine peaks peale õiget käiku olema võimalik. viimane käik oli" + viimaneKäik);
            }
            //System.out.println(viimaneKäik);
            tehtudKäigud.add(viimaneKäik);
        }
        return tehtudKäigud;
    }

    boolean kasValeTulemus(MassiiviSeis massiiviSeis) {
        //valiku kiirmeetodi puhul hindab kas on vähimad elemendid ees. muul juhul kas on sorteeritud.
        if(massiiviSeis instanceof ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis) {
            return ValikuKiirmeetodiTööriistad.kasVähimadElemendidPoleEes(valikuKiirmeetodiMassiiviSeis);
        }

        return MassiiviTööriistad.kasSorteerimata(massiiviSeis.getMassiiv());
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


           while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {//teen järjest õigeid käike
                //System.out.println("----");
                //System.out.println("----");
                järgmineÕigeKäik = viimaneKäik.järgmineÕigeKäik();
                //System.out.println("õige käik oleks " + järgmineÕigeKäik);
                List<Massiivioperatsioon> võimalikudKäigud = kõikvõimalikudKäigud(viimaneKäik.getSeis());

                //peale iga õige käigu tegemist vaatan läbi kõik variandid, kus teen nüüd vale käigu ja jälle edasi õigesti
                int õigeidKäikeVõimalikeKäikudeSeas = 0;
                for (Massiivioperatsioon võimalikKäik : võimalikudKäigud) {
                    //System.out.println("proovin käiku: "+võimalikKäik);
                    if(võimalikKäik.equals(järgmineÕigeKäik)) {
                        //System.out.println("käik " + võimalikKäik + " on sama kui õige käik " + järgmineÕigeKäik);
                        õigeidKäikeVõimalikeKäikudeSeas++;
                        continue;
                    }

                    //System.out.println("teen vea " + võimalikKäik);

                    List<Massiivioperatsioon> lõpuniJärjestamine1Veaga = jätkaLäbimängu(õigedKäigud, võimalikKäik);
                    Hindamistulemus hindamistulemus = läbimänguHindaja.hinda(lõpuniJärjestamine1Veaga);

                    //System.out.println(hindamistulemus);
                    assertEquals(1, hindamistulemus.getValedeKäikudeArv());
                    assertTrue(hindamistulemus.arvutaPunktid() < 1);
/*
                    System.out.println("mina teen hoopis käigud: \n---algus");
                    for (Massiivioperatsioon massiivioperatsioon : lõpuniJärjestamine1Veaga) {
                        System.out.println(massiivioperatsioon);
                    }
                    System.out.println(hindamistulemus);
                    System.out.println("---lõpp");*/

                    if(hindamistulemus.getOluliseVeaIndeks() == null) {
                        esinebOlulineViga = true;
                        MassiiviSeis algoritmiTulemus1Veaga = lõpuniJärjestamine1Veaga.get(lõpuniJärjestamine1Veaga.size()-1).getSeis();
                        if(kasValeTulemus(algoritmiTulemus1Veaga)) {
                            //System.out.println("viga mis tehti oli " + võimalikKäik + " kuigi õige oleks olnud " + järgmineÕigeKäik);
                            Massiivioperatsioon viganeKäik = lõpuniJärjestamine1Veaga.get(lõpuniJärjestamine1Veaga.size() - 3).järgmineÕigeKäik();
                            //System.out.println("vigane käik on " + viganeKäik);
                            throw new RuntimeException(String.format("Viga massiivi %s ümber järjestamisel: %s on vale tulemus", lõpuniJärjestamine1Veaga.get(0).getSeis(), Arrays.toString(algoritmiTulemus1Veaga.getMassiiv())));
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
                    if(hindamistulemus.arvutaPunktid() != 0) {
                        esinebLahendusMisSaiPunkte = true;
                    }
                }
                //assertEquals(1, õigeidKäikeVõimalikeKäikudeSeas);//TODO sisse panna
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
