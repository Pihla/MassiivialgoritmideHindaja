package main.massiivioperatsioon.mullimeetod;

import main.massiiviSeis.MassiiviSeis;

public class MullimeetodiTööristad {
    public static MullimeetodiPiste leiaJärgminePiste(MassiiviSeis massiiviSeis, int vasakpoolneOts, int parempoolneOts) {
        //indeksid on nii, et suurim piste on see, kui parempoolse otsa element liigub vasakpoolse otsa ette
        //leiab alates paremalt esimese sobiva elemendi ja pistab ta endast vasakule esimesse võimalikku kohta
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
        throw new RuntimeException("Pistet ei ole võimalik teha");
    }


}
