package main;


import java.util.Arrays;

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
        int vähimaIndeks = massiiviSeis.tööalaAlgusIndeks;
        int vähimaVäärtus = massiiviSeis.getMassiiv()[vähimaIndeks];
        for (Integer i = massiiviSeis.tööalaAlgusIndeks; i < massiiviSeis.getTööalaleJärgnevIndeks(); i++) {
            if(massiiviSeis.getMassiiv()[i] < vähimaVäärtus) {
                vähimaIndeks = i;
                vähimaVäärtus = massiiviSeis.getMassiiv()[vähimaIndeks];
            }
        }
        System.out.println("leidsin et vähim on indeksil " + vähimaIndeks);
        return vähimaIndeks;
    }
    public static int[] kopeeriJaSorteeriMassiiv(int[] massiiv) {
        int[] koopiaMassiiv = Arrays.copyOf(massiiv, massiiv.length);
        Arrays.sort(koopiaMassiiv);
        return koopiaMassiiv;
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

    public static boolean kasSorteerimata(int[] massiiv) {
        for (int i = 1; i < massiiv.length; i++) {
            if(massiiv[i] < massiiv[i-1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean kasOnÕigedElemendidKuniIndeksini(int[] massiiv, int indeks) {
        //indeks on välja arvatud
        //kas lõplikult sorteeritud massiivis oleks samades kohtades samad elemendid
        int[] sorteeritudMassiiv = kopeeriJaSorteeriMassiiv(massiiv);
        for (int i = 0; i < indeks; i++) {
            if(sorteeritudMassiiv[i] != massiiv[i]) return false;
        }
        return true;
    }
}

