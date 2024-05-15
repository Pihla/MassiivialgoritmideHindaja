package main;


import main.massiiviSeis.MassiiviSeis;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiTööriistad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MassiiviTööriistad {
    public static boolean kasTööalaValimata(MassiiviSeis massiiviSeis) {
        return massiiviSeis.getTööalaAlgusIndeks() == null || massiiviSeis.getTööalaleJärgnevIndeks() == null;
    }
    public static boolean kasTööalaÜmbrusOnSorteeritud(MassiiviSeis massiiviSeis) {
        //kas enne ja pärast tööala on täpselt need elemendid nagu sorteeritud massiivis oleks
        int[] sorteeritudMassiiv = kopeeriJaSorteeriMassiiv(massiiviSeis.getMassiiv());
        for (int i = 0; i < massiiviSeis.getTööalaAlgusIndeks(); i++) {
            if(massiiviSeis.getMassiiv()[i] != sorteeritudMassiiv[i]) return false;
        }
        for (int i = massiiviSeis.getTööalaleJärgnevIndeks(); i < massiiviSeis.getMassiiv().length; i++) {
            if(massiiviSeis.getMassiiv()[i] != sorteeritudMassiiv[i]) return false;
        }
        return true;
    }
    public static int tööalaAlgusestVähimaElemendiIndeks(MassiiviSeis massiiviSeis) {
        int vähimaIndeks = massiiviSeis.getTööalaAlgusIndeks();
        int vähimaVäärtus = massiiviSeis.getMassiiv()[vähimaIndeks];
        for (Integer i = massiiviSeis.getTööalaAlgusIndeks(); i < massiiviSeis.getTööalaleJärgnevIndeks(); i++) {
            if(massiiviSeis.getMassiiv()[i] < vähimaVäärtus) {
                vähimaIndeks = i;
                vähimaVäärtus = massiiviSeis.getMassiiv()[vähimaIndeks];
            }
        }
        return vähimaIndeks;
    }
    public static int[] kopeeriJaSorteeriMassiiv(int[] massiiv) {
        int[] koopiaMassiiv = Arrays.copyOf(massiiv, massiiv.length);
        Arrays.sort(koopiaMassiiv);
        return koopiaMassiiv;
    }
    public static boolean kasÕigeTulemus(MassiiviSeis massiiviSeis) {
        //valiku kiirmeetodi puhul hindab kas on vähimad elemendid ees. muul juhul kas on sorteeritud.
        if (massiiviSeis instanceof ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis) {
            return ValikuKiirmeetodiTööriistad.kasVähimadElemendidOnEes(valikuKiirmeetodiMassiiviSeis);
        }

        return MassiiviTööriistad.kasVahemikOnSorteeritud(massiiviSeis.getMassiiv(), 0, massiiviSeis.getMassiiv().length);
    }

    //TODO tõsta see mujale
    public static List<Massiivioperatsioon> jätkaLäbimängu(List<Massiivioperatsioon> praeguseniTehtudkäigud, Massiivioperatsioon järgmineKäik) {
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
            //System.out.println(viimaneKäik);
            viimaneKäik = viimaneKäik.järgmineÕigeKäik();
            if (!(viimaneKäik instanceof LäbimänguLõpetamine)
                    && !viimaneKäik.kasOnVõimalikLäbimänguJätkata()) {
                System.out.println(tehtudKäigud.get(2).kasOnVõimalikLäbimänguJätkata());
                throw new RuntimeException("läbimängu jätkamine peaks peale õiget käiku olema võimalik. viimane käik oli" + viimaneKäik);
            }
            tehtudKäigud.add(viimaneKäik);
        }
        return tehtudKäigud;
    }

    public static boolean kasVahemikOnSorteeritud(int[] massiiv, int algusIndeks, int lõpuIndeks) {
        //kas antud vahemik on sorteeritud
        //lõpuindeks välja arvatud
        for (int i = algusIndeks+1; i < lõpuIndeks; i++) {
            if(massiiv[i] < massiiv[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean esinevadValedElemendidEnneIndeksit(int[] massiiv, int indeks) {
        //indeks on välja arvatud
        //kas lõplikult sorteeritud massiivis oleks samades kohtades samad elemendid
        int[] sorteeritudMassiiv = kopeeriJaSorteeriMassiiv(massiiv);
        for (int i = 0; i < indeks; i++) {
            if(sorteeritudMassiiv[i] != massiiv[i]) return true;
        }
        return false;
    }

    public record TööalaIndeksid(int algus, int lõpustJärgmine) {}
    public record PisteIndeksid(int algus, int lõpp) {}
    public record VahetatavadIndeksid(int vahetatav1, int vahetatav2) {}
}

