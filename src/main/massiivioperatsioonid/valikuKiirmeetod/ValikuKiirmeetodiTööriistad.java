package main.massiivioperatsioonid.valikuKiirmeetod;

import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.LahkmeJärgiJaotamine;

import java.util.Arrays;

public class ValikuKiirmeetodiTööriistad {

    public static LahkmeJärgiJaotamine leiaLahkmeJärgiJaotamine(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        int lahe = massiiviSeis.getMassiiv()[massiiviSeis.getTööalaAlgusIndeks()];

        int[] uusMassiiv = Arrays.copyOf(massiiviSeis.getMassiiv(), massiiviSeis.getMassiiv().length);

        int i = massiiviSeis.getTööalaAlgusIndeks();
        int j = massiiviSeis.getTööalaleJärgnevIndeks()-1;
        int esimeneLahkmestSuurem;

        while(true) {
            while(uusMassiiv[j] >= lahe) {
                j--;
                if(j < massiiviSeis.getTööalaAlgusIndeks()) {//kui piir on esimene elment
                    esimeneLahkmestSuurem = massiiviSeis.getTööalaAlgusIndeks() + 1;
                    massiiviSeis = new ValikuKiirmeetodiMassiiviSeis(uusMassiiv, massiiviSeis.getTööalaAlgusIndeks(), massiiviSeis.getTööalaleJärgnevIndeks(), massiiviSeis.getVastusePiir());
                    return new ValikuKiirmeetodiLahkmeJärgiJaotamine(massiiviSeis, esimeneLahkmestSuurem);
                }
            }

            while(uusMassiiv[i] < lahe) {
                i++;
            }

            if(i>j) {
                //esimene lahkmsest suurem on i
                esimeneLahkmestSuurem = i;
                massiiviSeis = new ValikuKiirmeetodiMassiiviSeis(uusMassiiv, massiiviSeis.getTööalaAlgusIndeks(), massiiviSeis.getTööalaleJärgnevIndeks(), massiiviSeis.getVastusePiir());
                return new ValikuKiirmeetodiLahkmeJärgiJaotamine(massiiviSeis, esimeneLahkmestSuurem);
            }
            int abi = uusMassiiv[i];
            uusMassiiv[i] = uusMassiiv[j];
            uusMassiiv[j] = abi;
            //System.out.println("vahetuse tulemusena on massiiv " + Arrays.toString(uusMassiiv));
        }
    }

   /* public static Massiivioperatsioon leiaJärgmineÕigeKäik(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int lahe, int otsingualaEsimeneElement, int otsingualaViimaneElement) {
        //esimene ja viimane element kaasa arvatud otsingualasse

        if(MassiiviTööriistad.kasTööalaValimata(massiiviSeis)) {
            return new ValikuKiirmeetodiTööalaValimine(0, massiiviSeis.getMassiiv().length, massiiviSeis);
        }

        //hakkan paremalt otsima lahkmest väiksemat elementi
        for (int i = otsingualaViimaneElement; i >= otsingualaEsimeneElement; i--) {
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

        //kõik otsinguala elemendid on lahkmest suuremad

        //kui eelmine käik oli tööala muutmine
        if(massiiviSeis.getTööalaAlgusIndeks() == otsingualaEsimeneElement && massiiviSeis.getTööalaleJärgnevIndeks() == otsingualaViimaneElement+1) {
            return new ValikuKiirmeetodiTööalaValimine(massiiviSeis.getTööalaAlgusIndeks()+1, massiiviSeis.getTööalaleJärgnevIndeks(), massiiviSeis);
        }
        //kui eelmine käik oli kahe elemendi vahetus
        return leiaTööalaMuutmineVõiLäbimänguLõpetamine(massiiviSeis, otsingualaEsimeneElement);
    }*/

   /* public static Massiivioperatsioon leiaTööalaMuutmineVõiLäbimänguLõpetamine(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int esimeneLahkmestSuuremElement) {
        //System.out.println("lahkmest suurme esimene "+esimeneLahkmestSuuremElement);
        //System.out.println("vastsue piir "+massiiviSeis.getVastusePiir());
        if(esimeneLahkmestSuuremElement == massiiviSeis.getVastusePiir()) {
            return new LäbimänguLõpetamine(massiiviSeis);
        }
        //System.out.println(massiiviSeis.getTööalaleJärgnevIndeks() + " tööala järnev ja algus indes" + massiiviSeis.getTööalaAlgusIndeks());
        if(massiiviSeis.getTööalaleJärgnevIndeks() - massiiviSeis.getTööalaAlgusIndeks() == 1) {
            return new LäbimänguLõpetamine(massiiviSeis);
        }
        if(esimeneLahkmestSuuremElement > massiiviSeis.getVastusePiir()) {
            return new ValikuKiirmeetodiTööalaValimine(massiiviSeis.getTööalaAlgusIndeks(), esimeneLahkmestSuuremElement, massiiviSeis);
        }
        return new ValikuKiirmeetodiTööalaValimine(esimeneLahkmestSuuremElement, massiiviSeis.getTööalaleJärgnevIndeks(), massiiviSeis);
    }
*/

    public static boolean kasVähimadElemendidPoleEes(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        //kontroll kas valiku kiirmeetodi lõpptulemus pole õige

        //panen läbimängu tulemusel saadud vähimad n elementi massiivi ja sorteerin ära
        int[] leitudVähimadElemendid = new int[massiiviSeis.getVastusePiir()];
        for (int i = 0; i < leitudVähimadElemendid.length; i++) {
            leitudVähimadElemendid[i] = massiiviSeis.getMassiiv()[i];
        }
        Arrays.sort(leitudVähimadElemendid);

        //sorteerin algse massiivi ära
        int[] sorteeritudMassiiv = Arrays.copyOf(massiiviSeis.getMassiiv(), massiiviSeis.getMassiiv().length);
        Arrays.sort(sorteeritudMassiiv);

        //kui esimesed n elementi ei ole mõlemas massiivis samad, ei ole vähimad elemendid ees
        for (int i = 0; i < leitudVähimadElemendid.length; i++) {
            if(leitudVähimadElemendid[i] != sorteeritudMassiiv[i]) {
                return true;
            }
        }

        return false;
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

    private static int[] leiaEsimesedElemendidSorteeritult(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int mitmendaElemendini) {
        int[] esimesed = new int[mitmendaElemendini];
        for (int i = 0; i < esimesed.length; i++) {
            esimesed[i] = massiiviSeis.getMassiiv()[i];
        }
        Arrays.sort(esimesed);
        return esimesed;
    }

    public static boolean kasKõikPiiiristVäiksemadOnTööalasVõiEnneSeda(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        if(massiiviSeis.getTööalaleJärgnevIndeks() < massiiviSeis.getVastusePiir()) {
            return false;
        }
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
