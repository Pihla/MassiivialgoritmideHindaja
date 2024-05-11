package tööriistad;

import java.util.*;

public class VõimalikudMassiiviJärjestused {
    private List<List<Integer>> järjestused;

    public int[][] leiaVõimalikudJärjestused(int[] m) {
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
