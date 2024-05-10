package main.massiivioperatsioonid.valikuKiirmeetod;

import main.MassiiviTööriistad;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.Arrays;

public class ValikuKiirmeetodiTööriistad {

    public static Massiivioperatsioon leiaJärgmineÕigeKäik(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int lahe, int otsingualaEsimeneElement, int otsingualaViimaneElement) {
        //esimene ja viimane element kaasa arvatud otsingualasse

        //hakkan paremalt otsima lahkmest väiksemat elementi
        for (int i = otsingualaViimaneElement; i > otsingualaEsimeneElement; i--) {
            //kui otsingualas leidub lahkmest väiksem element
            if(massiiviSeis.getMassiiv()[i] < lahe) {
                //hakkan vasakult otsima lahkmest suuremat elementi (kuni praeguse elemendini)
                for (int j = otsingualaEsimeneElement; j < i; j++) {
                    //kui leian siis järgmine käik on vahetus
                    if(massiiviSeis.getMassiiv()[j] >= lahe) {
                        return new ValikuKiirmeetodiElementideVahetamine(i, j, massiiviSeis, lahe);
                    }
                }
                //kui indeksil i ning sellest vasakul on kõik lahkmest väiksemad (ja paremal kõik suuremad)
                return leiaTööalaMuutmineVõiLäbimänguLõpetamine(massiiviSeis, i+1);
            }
        }
        //kõik tööala elemendid on lahkmest suuremad
        return leiaTööalaMuutmineVõiLäbimänguLõpetamine(massiiviSeis, otsingualaEsimeneElement);
    }

    public static Massiivioperatsioon leiaTööalaMuutmineVõiLäbimänguLõpetamine(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int esimeneLahkmestSuuremElement) {
        if(esimeneLahkmestSuuremElement == massiiviSeis.getVastusePiir()) {
            return new LäbimänguLõpetamine(massiiviSeis);
        }
        //TODO otsustada kas järgmist lõiku on vaja
        if(MassiiviTööriistad.kasTööalaValimata(massiiviSeis)) {
            return new ValikuKiirmeetodiTööalaValimine(0, massiiviSeis.getMassiiv().length, massiiviSeis);
        }
        if(esimeneLahkmestSuuremElement > massiiviSeis.getVastusePiir()) {
            return new ValikuKiirmeetodiTööalaValimine(massiiviSeis.getTööalaAlgusIndeks(), esimeneLahkmestSuuremElement, massiiviSeis);
        }
        return new ValikuKiirmeetodiTööalaValimine(esimeneLahkmestSuuremElement, massiiviSeis.getTööalaleJärgnevIndeks(), massiiviSeis);
    }

    public static boolean kasEnneTööalaOnAinultVähimadElemendid(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        int[] elemendidEnneTööala = leiaEsimesedElemendidSorteeritult(massiiviSeis, massiiviSeis.getTööalaAlgusIndeks());

        int[] algneMassiiv = Arrays.copyOf(massiiviSeis.getMassiiv(), massiiviSeis.getMassiiv().length);
        Arrays.sort(algneMassiiv);

        int j = 0;
        for (int element : elemendidEnneTööala) {
            while(algneMassiiv[j] != element) {
                j++;
                if(j >= massiiviSeis.getVastusePiir()) {//TODO kas saaks ka ==
                    //kui oletatava vastuse mõni tööalast eespool olev element ei ole tegelikult vastuses
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] leiaEsimesedElemendidSorteeritult(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int mitmendaElemendini) {
        int[] esimesed = new int[mitmendaElemendini];
        for (int i = 0; i < esimesed.length; i++) {
            esimesed[i] = massiiviSeis.getMassiiv()[i];
        }
        Arrays.sort(esimesed);
        return esimesed;
    }

    public static boolean kasKõikPiiiristVäiksemadOnTööalasVõiEnneSeda(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        int[] tööalaJaEelnevad = leiaEsimesedElemendidSorteeritult(massiiviSeis, massiiviSeis.getTööalaleJärgnevIndeks());

        int[] algneMassiiv = Arrays.copyOf(massiiviSeis.getMassiiv(), massiiviSeis.getMassiiv().length);
        Arrays.sort(algneMassiiv);

        for (int i = 0; i < massiiviSeis.getVastusePiir(); i++) {
            if(tööalaJaEelnevad[i] != algneMassiiv[i]) {
                return false;
            }
        }
        return true;
    }

}
