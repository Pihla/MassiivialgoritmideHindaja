package main;


import main.massiivioperatsioonid.LahkmeJärgiJaotamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiLahkmeJärgiJaotamine;
import main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.massiivioperatsioonid.valikuKiirmeetod.ValikuKiirmeetodiTööriistad.leiaLahkmeJärgiJaotamine;

public class Main {
    public static void main(String[] args) {
        //TODO tühjendada

        ValikuKiirmeetodiMassiiviSeis msEnne = new ValikuKiirmeetodiMassiiviSeis(new int[]{12, 10, 7, 8, 9, 6, 0}, 0, 7, 4);
        //ValikuKiirmeetodiLäbimänguAlustamine läbimänguAlustamine = new ValikuKiirmeetodiLäbimänguAlustamine(msEnne);

        ValikuKiirmeetodiLahkmeJärgiJaotamine lahkmeJärgiJaotamine = new ValikuKiirmeetodiLahkmeJärgiJaotamine(msEnne, 4);
        System.out.println(lahkmeJärgiJaotamine);

        Massiivioperatsioon järgmine =  lahkmeJärgiJaotamine.järgmineÕigeKäik();
        System.out.println(järgmine);
        while(!(järgmine instanceof LäbimänguLõpetamine)) {
            järgmine = järgmine.järgmineÕigeKäik();
            System.out.println(järgmine);
        }

    }



}
