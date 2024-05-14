package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Kasutajaliides {

    public abstract void kuvaMeetodiInfo(int[] massiiv);
    public abstract LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv);
    public abstract Massiivioperatsioon loeOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException;
    public abstract LäbimänguHindaja läbimänguHindaja();

    public void meetodiLäbimäng() {
        List<Massiivioperatsioon> käigud = new ArrayList<>();
        int[] massiiv = looUusMassiiv();
        Massiivioperatsioon viimaneKäik = läbimänguAlustamiseOperatsioon(massiiv);
        käigud.add(viimaneKäik);
        kuvaMeetodiInfo(massiiv);
        System.out.println("------");


        while(!(viimaneKäik instanceof LäbimänguLõpetamine)) {
            Scanner skänner = new Scanner(System.in);
            System.out.println("Sisesta järgmine käik: ");
            String sisend = skänner.nextLine();

            String[] sisendiJupid = sisend.split(" ");
            try {
                viimaneKäik = loeOperatsioon(sisendiJupid, viimaneKäik.getSeis());
                System.out.println(viimaneKäik);
                käigud.add(viimaneKäik);
            }catch (ViganeSisendException e) {
                System.out.printf("%s, pead sisestama käigu uuesti%n", e.getMessage());
            }
        }

        System.out.println("-----------");
        System.out.println("Tehti järgmised käigud: ");
        for (Massiivioperatsioon käik : käigud) {
            System.out.println(käik);
        }
        System.out.println("\nTulemus: "+läbimänguHindaja().hinda(käigud));

    }


    public static int[] looUusMassiiv() {
        Random random = new Random(1);
        int massiivisElemente = 5;
        int maxJuhuslikVäärtus = 20;

        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxJuhuslikVäärtus);
        }
        return massiiv;
    }

}
