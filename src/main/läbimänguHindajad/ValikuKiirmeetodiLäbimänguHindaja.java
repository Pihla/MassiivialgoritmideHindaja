package main.läbimänguHindajad;

import main.massiivioperatsioonid.LahkmeJärgiJaotamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;

import java.util.List;
import java.util.Random;

public class ValikuKiirmeetodiLäbimänguHindaja extends LäbimänguHindaja{
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        //lahkme järgi jagamiste arv


        int raskusparameeter = 0;
        for (Massiivioperatsioon käik : tehtudKäigud) {
            if(käik instanceof LahkmeJärgiJaotamine) {
                raskusparameeter += 1;
            }
        }

        return raskusparameeter;
    }
}
