package main;


import java.util.Arrays;

public class MassiiviTööriistad {
    public static int tööalaVähimaElemendiVäärtus(MassiiviSeis massiiviSeis) {
        int vähimaVäärtus = massiiviSeis.getMassiiv()[massiiviSeis.getTööalaAlgusIndeks()];
        for (Integer i = massiiviSeis.tööalaAlgusIndeks; i < massiiviSeis.getTööalaleJärgnevIndeks(); i++) {
            if(massiiviSeis.getMassiiv()[i] < vähimaVäärtus) {
                vähimaVäärtus = massiiviSeis.getMassiiv()[i];
            }
        }
        return vähimaVäärtus;
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

