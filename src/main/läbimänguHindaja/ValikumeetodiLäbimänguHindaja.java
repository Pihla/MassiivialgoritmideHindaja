package main.läbimänguHindaja;

import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiElementideVahetamine;

import java.util.List;

public class ValikumeetodiLäbimänguHindaja extends LäbimänguHindaja {
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        // valikumeetodi raskusparameeter on tehtavate vahetuste arv

        int raskusparameeter = 0;
        for (Massiivioperatsioon käik : tehtudKäigud) {
            if (käik instanceof ValikumeetodiElementideVahetamine) {
                raskusparameeter += 1;
            }
        }

        return raskusparameeter;
    }
}
