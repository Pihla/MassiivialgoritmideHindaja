package main;

import main.massiiviSeis.MassiiviSeis;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.läbimänguHindaja.*;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiLäbimänguAlustamine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KorrektseteLäbimängudeKuvamine {

    public static void kuvaKõigiMeetoditeKorrektsedLäbimängud() {
        System.out.println("Tööala algus ja lõpp on märgitud märkidega |");
        System.out.println("Valiku kiirmeetodi puhul märgib _ piiri, millest eespool olevad elemendid peaksid läbimängu tulemusena olema väiksemad, kui tagapool olevad");
        System.out.println("------");

        Random random = new Random(1);
        int[] massiiv = new int[7];
        for (int i = 0; i < massiiv.length; i++) {
            massiiv[i] = random.nextInt(20);
        }

        kuvaMullimeetodiLäbimäng(massiiv);
        kuvaPistemeetodiLäbimäng(massiiv);
        kuvaValikumeetodiLäbimäng(massiiv);
        kuvaValikuKiirmeetodiLäbimäng(massiiv);
    }

    public static void kuvaValikuKiirmeetodiLäbimäng(int[] massiiv) {
        ValikuKiirmeetodiMassiiviSeis massiiviSeis = new ValikuKiirmeetodiMassiiviSeis(massiiv, null, null, 3);
        LäbimänguAlustamine läbimänguAlustamine = new ValikuKiirmeetodiLäbimänguAlustamine(massiiviSeis);
        kuvaAlgoritmiKorrektneLäbimängJaTulemus(läbimänguAlustamine, new ValikuKiirmeetodiLäbimänguHindaja());
    }
    public static void kuvaValikumeetodiLäbimäng(int[] massiiv) {
        MassiiviSeis massiiviSeis = new MassiiviSeis(massiiv, null, null);
        LäbimänguAlustamine läbimänguAlustamine = new ValikumeetodiLäbimänguAlustamine(massiiviSeis);
        kuvaAlgoritmiKorrektneLäbimängJaTulemus(läbimänguAlustamine, new ValikumeetodiLäbimänguHindaja());
    }
    public static void kuvaPistemeetodiLäbimäng(int[] massiiv) {
        MassiiviSeis massiiviSeis = new MassiiviSeis(massiiv, null, null);
        LäbimänguAlustamine läbimänguAlustamine = new PistemeetodiLäbimänguAlustamine(massiiviSeis);
        kuvaAlgoritmiKorrektneLäbimängJaTulemus(läbimänguAlustamine, new PistemeetodiLäbimänguHindaja());
    }
    public static void kuvaMullimeetodiLäbimäng(int[] massiiv) {
        MassiiviSeis massiiviSeis = new MassiiviSeis(massiiv, null, null);
        LäbimänguAlustamine läbimänguAlustamine = new MullimeetodiLäbimänguAlustamine(massiiviSeis);
        kuvaAlgoritmiKorrektneLäbimängJaTulemus(läbimänguAlustamine, new MullimeetodiLäbimänguHindaja());
    }
    public static void kuvaAlgoritmiKorrektneLäbimängJaTulemus(Massiivioperatsioon läbimänguAlustamine, LäbimänguHindaja läbimänguHindaja) {
        List<Massiivioperatsioon> käigud =  MassiiviTööriistad.jätkaLäbimängu(new ArrayList<>(), läbimänguAlustamine);
        for (Massiivioperatsioon käik : käigud) {
            System.out.println(käik);
        }
        System.out.println(läbimänguHindaja.hinda(käigud));
        System.out.println("--------");
    }
}
