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
                if(j < massiiviSeis.getTööalaAlgusIndeks()) {//lahkme järgi jaotamine ei muuda massiivi seisu
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
        }
    }

    public static boolean kasVähimadElemendidOnEes(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
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
                return false;
            }
        }

        return true;
    }
    public static boolean kasEnneTööalaOnAinultVähimadElemendid(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        //kontrollib, kas enne tööala asuvad ainult need elemendid, mis jäävad vastusesse peale korrektset valiku kiirmeetodi läbimängu
        int[] elemendidEnneTööala = leiaEsimesedElemendidSorteeritult(massiiviSeis, massiiviSeis.getTööalaAlgusIndeks());

        int[] algneMassiiv = Arrays.copyOf(massiiviSeis.getMassiiv(), massiiviSeis.getMassiiv().length);
        Arrays.sort(algneMassiiv);

        int j = 0;
        for (int element : elemendidEnneTööala) {
            while(algneMassiiv[j] != element && j < massiiviSeis.getVastusePiir()) {
                j++;
            }
            if(j >= massiiviSeis.getVastusePiir()) {
                return false;
            }
            j++;
        }
        return true;
    }

    private static int[] leiaEsimesedElemendidSorteeritult(ValikuKiirmeetodiMassiiviSeis massiiviSeis, int mitmendaElemendini) {
        //leiab elemendid, mis oleksid valiku kiirmeetodi järel esimeses osas ja sorteerib need
        int[] esimesed = new int[mitmendaElemendini];
        for (int i = 0; i < esimesed.length; i++) {
            esimesed[i] = massiiviSeis.getMassiiv()[i];
        }
        Arrays.sort(esimesed);
        return esimesed;
    }

    public static boolean kasKõikPiiristVäiksemadOnTööalasVõiEnneSeda(ValikuKiirmeetodiMassiiviSeis massiiviSeis) {
        //kontrollib, ega mõni n vähima elemendi hulgast pole peale tööala
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
