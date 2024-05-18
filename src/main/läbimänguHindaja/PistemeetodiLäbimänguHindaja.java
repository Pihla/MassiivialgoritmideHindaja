package main.läbimänguHindaja;

import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.pistemeetod.PistemeetodiPiste;

import java.util.List;

public class PistemeetodiLäbimänguHindaja extends LäbimänguHindaja {


    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        // pistemeetodi raskusparameeter on tehtavate pistete arv

        int raskusparameeter = 0;
        for (Massiivioperatsioon käik : tehtudKäigud) {
            if(käik instanceof PistemeetodiPiste) {
                raskusparameeter += 1;
            }
        }

        return raskusparameeter;
    }
}
