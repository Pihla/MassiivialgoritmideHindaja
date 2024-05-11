package main;

import java.util.*;

public class Permutatsioonid {
    private List<List<Integer>> permutatsioonid;

    public int[][] genereeriPermutatsioonid(int[] m) {
        //TODO vb paremini teha
        permutatsioonid = new ArrayList<>();

        List<Integer> olemasolev = new ArrayList<>();
        List<Integer> veelLisada = new ArrayList<>();
        for (int j : m) {
            veelLisada.add(j);
        }

        rekPerm(olemasolev, veelLisada);

        //System.out.println(permutatsioonid.size()+ " nii palju on permutatsioone m korral "+m.length);


        Set<List<Integer>> permutatsioonideHulk = new HashSet<>(this.permutatsioonid);
        this.permutatsioonid = new ArrayList<>(permutatsioonideHulk);

        int[][] permutatsioonid = new int[this.permutatsioonid.size()][];
        for (int i = 0; i < this.permutatsioonid.size(); i++) {
            List<Integer> praegunePermutatsioon = this.permutatsioonid.get(i);
            int[] uusArray = new int[m.length];
            for (int j = 0; j < uusArray.length; j++) {
                uusArray[j] = praegunePermutatsioon.get(j);
            }
            permutatsioonid[i] = uusArray;
        }
        return permutatsioonid;
    }

    private void rekPerm(List<Integer> olemasolev, List<Integer> veelLisada) {
        if(veelLisada.isEmpty()) {
            this.permutatsioonid.add(olemasolev);
        }
        for (Integer i : veelLisada) {
            List<Integer> koopiaOlemasolevast = new ArrayList<>(olemasolev);
            List<Integer> koopiaVeelLisatavatest = new ArrayList<>(veelLisada);
            koopiaOlemasolevast.add(i);
            koopiaVeelLisatavatest.remove(i);
            rekPerm(koopiaOlemasolevast, koopiaVeelLisatavatest);
        }
    }
}
