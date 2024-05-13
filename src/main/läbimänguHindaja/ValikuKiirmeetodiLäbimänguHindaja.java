package main.läbimänguHindaja;

import main.massiivioperatsioon.LahkmeJärgiJaotamine;
import main.massiivioperatsioon.Massiivioperatsioon;

import java.util.List;

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
