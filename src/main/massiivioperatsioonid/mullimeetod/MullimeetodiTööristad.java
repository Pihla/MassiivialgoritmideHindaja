package main.massiivioperatsioonid.mullimeetod;

import main.MassiiviSeis;

public class MullimeetodiTööristad {
    public static MullimeetodiPiste leiaJärgminePiste(MassiiviSeis massiiviSeis, int vasakpoolneOts, int parempoolneOts) {
        //indeksid on nii, et suurim piste on see, kui vasakpoolse otsa element liigub parempoolse otsa ette
        for (int pisteAlgus = parempoolneOts; pisteAlgus > vasakpoolneOts; pisteAlgus--) {
            int uuePisteLõpp = pisteAlgus;
            while (uuePisteLõpp > vasakpoolneOts
                    && massiiviSeis.getMassiiv()[pisteAlgus] < massiiviSeis.getMassiiv()[uuePisteLõpp - 1]) {
                uuePisteLõpp--;
            }
            if (pisteAlgus != uuePisteLõpp) {
                return new MullimeetodiPiste(pisteAlgus, uuePisteLõpp, massiiviSeis);
            }
        }
        return null;
    }


}
