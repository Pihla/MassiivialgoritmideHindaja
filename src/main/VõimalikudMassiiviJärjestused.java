package main;

import java.util.*;

public class VõimalikudMassiiviJärjestused {

    public int[][] leiaVõimalikudJärjestused2(int[] m) {//seda kasutades ei ole garanteeritud, et lahkme järgi jaotamisel on õige tulemus ka olemas
        int järjestusteArv = 10;
        int[][] järjestused = new int[järjestusteArv][];

        for (int i = 0; i < järjestusteArv; i++) {
            List<Integer> arvudeList = new ArrayList<Integer>(m.length);
            for (int j : m) {
                arvudeList.add(j);
            }
            Collections.shuffle(arvudeList);
            int[] järjestus = new int[m.length];
            for (int j = 0; j < arvudeList.size(); j++) {
                järjestus[j] = arvudeList.get(j);
            }
            järjestused[i] = järjestus;
        }
        return järjestused;
    }



    private List<List<Integer>> järjestused;

    public int[][] leiaVõimalikudJärjestused(int[] m) {//seda kasutades jookseb test üsna kaua. kui massiivi pikkus on üle 7, ei tööta see mõistliku ajaga

        //TODO vb paremini teha
        järjestused = new ArrayList<>();

        //leian kõik permutatsioonid
        List<Integer> olemasolevadNumbrid = new ArrayList<>();
        List<Integer> hiljemLisatavadNumbrid = new ArrayList<>();
        for (int j : m) {
            hiljemLisatavadNumbrid.add(j);
        }
        leiaJärjestusedRek(olemasolevadNumbrid, hiljemLisatavadNumbrid);

        //tagan, et kõik järjestused esineks ainult ühe korra
        Set<List<Integer>> permutatsioonideHulk = new HashSet<>(this.järjestused);
        this.järjestused = new ArrayList<>(permutatsioonideHulk);

        //tõstan kõik järjestused massiividesse
        int[][] järjestused = new int[this.järjestused.size()][];
        for (int i = 0; i < this.järjestused.size(); i++) {
            List<Integer> praeguneJärjestus = this.järjestused.get(i);
            int[] uusMassiiv = new int[m.length];
            for (int j = 0; j < uusMassiiv.length; j++) {
                uusMassiiv[j] = praeguneJärjestus.get(j);
            }
            järjestused[i] = uusMassiiv;
        }
        return järjestused;
    }

    private void leiaJärjestusedRek(List<Integer> olemasolevadNumbrid, List<Integer> hiljemLisatavadNumbrid) {
        if(hiljemLisatavadNumbrid.isEmpty()) {
            this.järjestused.add(olemasolevadNumbrid);
        }
        for (Integer i : hiljemLisatavadNumbrid) {
            List<Integer> koopiaOlemasolevatest = new ArrayList<>(olemasolevadNumbrid);
            List<Integer> koopiaHiljemLisatavatest = new ArrayList<>(hiljemLisatavadNumbrid);
            koopiaOlemasolevatest.add(i);
            koopiaHiljemLisatavatest.remove(i);
            leiaJärjestusedRek(koopiaOlemasolevatest, koopiaHiljemLisatavatest);
        }
    }
}
