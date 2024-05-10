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


}
