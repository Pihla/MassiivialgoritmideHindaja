package main.kasutajaliides;

import main.MassiiviTööriistad;
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
    private final Scanner skänner = new Scanner(System.in);
    private final Random random = new Random();

    private final int massiivisElemente = 5;
    private final int maxJuhuslikVäärtus = 20;
    protected int valikuKiirmeetodiVastusePiir = 3;

    public static void alusta() {
        Scanner skänner = new Scanner(System.in);

        Kasutajaliides kasutajaliides;

        meetodiKüsimine:
        while (true) {
            System.out.print("Vali algoritm. [mullimeetod/pistemeetod/valikumeetod/valiku kiirmeetod]: ");
            String sisend = skänner.nextLine();
            switch (sisend) {
                case "mullimeetod":
                    kasutajaliides = new MullimeetodiKasutajaliides();
                    break meetodiKüsimine;
                case "pistemeetod":
                    kasutajaliides = new PistemeetodiKasutajaliides();
                    break meetodiKüsimine;
                case "valikumeetod":
                    kasutajaliides = new ValikumeetodiKasutajaliides();
                    break meetodiKüsimine;
                case "valiku kiirmeetod":
                    kasutajaliides = new ValikuKiirmeetodiKasutajaliides();
                    break meetodiKüsimine;
            }
            System.out.println("Vigane sisend.");
        }


        lahendusviisiKüsimine:
        while (true) {
            System.out.print("Millist lahendusviisi soovid? [harjutamine/kontrolltöö/näide]: ");
            String sisend = skänner.nextLine();
            System.out.println();
            switch (sisend) {
                case "harjutamine":
                    kasutajaliides.algoritmiLäbimänguHarjutamine();
                    break lahendusviisiKüsimine;
                case "kontrolltöö":
                    kasutajaliides.algoritmiLäbimängKontrolltöö();
                    break lahendusviisiKüsimine;
                case "näide":
                    kasutajaliides.kuvaAlgoritmiKorrektneLäbimäng();
                    break lahendusviisiKüsimine;
            }
            System.out.println("Vigane sisend.");
        }


        while (true) {
            System.out.print("\nKas soovid veel läbimänge? [jah/ei]: ");
            String sisend = skänner.nextLine();
            switch (sisend) {
                case "jah":
                    alusta();
                    return;
                case "ei":
                    System.out.println("Aitäh programmi kasutamast. Kohtumiseni!");
                    return;
            }
            System.out.println("Vigane sisend. Sisesta vastus, kas soovid veel läbimänge teha:");
        }
    }

    protected void kuvaVõimalikudOperatsioonid() {
        System.out.println("tööala <algusindeks> <lõpuindeks> - muudab tööala, lõpuindeks on tööalast välja arvatud");
        System.out.println("lõpeta - lõpetab läbimängu");
        System.out.println("tagasi - võtab viimase käigu tagasi");
        System.out.println("abi - kuvab võimalikud käigud");
    }

    protected abstract LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv);

    protected abstract void läbimänguAlustamiseSõnum(int[] massiiv);

    protected abstract Massiivioperatsioon leiaOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException;

    protected abstract LäbimänguHindaja läbimänguHindaja();

    private Massiivioperatsioon loeKasutajaltKäik(List<Massiivioperatsioon> käigud) {
        System.out.print("Sisesta järgmine käik: ");
        String sisend = skänner.nextLine();
        Massiivioperatsioon viimatineKäik = käigud.get(käigud.size() - 1);

        String[] sisendiJupid = sisend.split(" ");
        switch (sisendiJupid[0]) {
            case "abi":
                System.out.println("--------");
                System.out.println("Massiiv algab indeksilt 0. Võimalikud käigud: ");
                kuvaVõimalikudOperatsioonid();
                System.out.println("--------");
                return null;
            case "tagasi":
                if (käigud.size() > 1) {
                    käigud.remove(viimatineKäik);
                    viimatineKäik = käigud.get(käigud.size() - 1);
                    System.out.println("Käik võeti tagasi, viimane käik oli " + viimatineKäik);
                } else {
                    System.out.println("Rohkem käike ei saa tagasi võtta.");
                }
                return null;
            case "lõpeta":
                return new LäbimänguLõpetamine(viimatineKäik.getSeis());
            default:
                try {
                    return leiaOperatsioon(sisendiJupid, viimatineKäik.getSeis());
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " Pead sisestama käigu uuesti.");
                    return null;
                }

        }
    }

    private void algoritmiLäbimänguHarjutamine() {
        List<Massiivioperatsioon> käigud = new ArrayList<>();
        int[] massiiv = looUusMassiiv();
        läbimänguAlustamiseSõnum(massiiv);
        käigud.add(läbimänguAlustamiseOperatsioon(massiiv));
        System.out.println("Massiiv algab indeksilt 0. Võimalikud käigud: ");
        kuvaVõimalikudOperatsioonid();
        System.out.println();

        Massiivioperatsioon eelmineKäik = käigud.get(0);
        while (!(eelmineKäik instanceof LäbimänguLõpetamine)) {
            Massiivioperatsioon praeguneKäik = loeKasutajaltKäik(käigud);
            if (praeguneKäik != null) {
                System.out.println(praeguneKäik);
                if (!praeguneKäik.equals(eelmineKäik.järgmineÕigeKäik())) {
                    System.out.println("Vale käik. Proovi uuesti. Massiivi seis on: " + eelmineKäik.getSeis());
                } else {
                    käigud.add(praeguneKäik);
                    eelmineKäik = praeguneKäik;
                }
            }

        }
        System.out.println("Palju õnne! Oled läbimängu harjutamise edukalt läbinud.");
        System.out.println("--------");
        System.out.println("Tehtud käigud: ");
        for (Massiivioperatsioon käik : käigud) {
            System.out.println(käik);
        }
    }

    private void algoritmiLäbimängKontrolltöö() {
        List<Massiivioperatsioon> käigud = new ArrayList<>();
        int[] massiiv = looUusMassiiv();
        läbimänguAlustamiseSõnum(massiiv);
        käigud.add(läbimänguAlustamiseOperatsioon(massiiv));
        System.out.println("Massiiv algab indeksilt 0. Võimalikud käigud: ");
        kuvaVõimalikudOperatsioonid();
        System.out.println("-----------");

        Massiivioperatsioon viimatineKäik = käigud.get(0);
        while (!(viimatineKäik instanceof LäbimänguLõpetamine)) {
            viimatineKäik = loeKasutajaltKäik(käigud);
            if (viimatineKäik != null) {
                System.out.println(viimatineKäik);
                käigud.add(viimatineKäik);
            }
        }

        System.out.println("-----------");
        System.out.println("Tehtud käigud: ");
        Massiivioperatsioon praeguneKäik = käigud.get(0);
        System.out.println(praeguneKäik);
        boolean leitudOlulineViga = false;
        for (int i = 1; i < käigud.size(); i++) {
            Massiivioperatsioon õigeKäik = praeguneKäik.järgmineÕigeKäik();
            praeguneKäik = käigud.get(i);

            System.out.print(i + ". " + praeguneKäik);
            if (!praeguneKäik.equals(õigeKäik) && !leitudOlulineViga) {
                System.out.print(" Vale käik. Õige oleks olnud " + õigeKäik);
                if (!praeguneKäik.läbimänguOnVõimalikJätkata()) {
                    leitudOlulineViga = true;
                    if (i < käigud.size() - 1) {
                        System.out.print("\nSee on oluline viga, edasist lahenduskäiku ei hinnata. Järgmised käigud olid: ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("-----------");
        System.out.println("Tulemus: " + läbimänguHindaja().hinda(käigud));

    }

    private int[] looUusMassiiv() {
        int[] massiiv = new int[massiivisElemente];
        for (int j = 0; j < massiiv.length; j++) {
            massiiv[j] = random.nextInt(maxJuhuslikVäärtus);
        }
        return massiiv;
    }

    private void kuvaAlgoritmiKorrektneLäbimäng() {
        Massiivioperatsioon läbimänguAlustamine = läbimänguAlustamiseOperatsioon(looUusMassiiv());
        List<Massiivioperatsioon> käigud = MassiiviTööriistad.kopeeriKäigudJajätkaLäbimängu(new ArrayList<>(), läbimänguAlustamine);
        for (Massiivioperatsioon käik : käigud) {
            System.out.println(käik);
        }
    }

}
