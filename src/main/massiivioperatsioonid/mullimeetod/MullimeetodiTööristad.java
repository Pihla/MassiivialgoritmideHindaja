package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;

import static main.MassiiviTööriistad.kopeeriJaSorteeriMassiiv;

public class MullimeetodiTööristad {
    public static MullimeetodiPiste leiaJärgminePiste(MassiiviSeis massiiviSeis, int vasakpoolneOts, int parempoolneOts) {
        //vasakpoolse otsa element võib liikuda parempoolse otsa ette
        for (int pisteAlgus = parempoolneOts; pisteAlgus > vasakpoolneOts ; pisteAlgus--) {
            int uuePisteLõpp = pisteAlgus;
            while(uuePisteLõpp > vasakpoolneOts
                    && massiiviSeis.getMassiiv()[pisteAlgus] < massiiviSeis.getMassiiv()[uuePisteLõpp-1]) {
                uuePisteLõpp--;
            }
            if(pisteAlgus != uuePisteLõpp) {
                return new MullimeetodiPiste(pisteAlgus, uuePisteLõpp, massiiviSeis);
            }
        }
        return null;
    }

    static boolean kasTööalaÜmbrusOnSorteeritud(MassiiviSeis massiiviSeis) {
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
}
