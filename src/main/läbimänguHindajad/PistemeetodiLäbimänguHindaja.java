package main.läbimänguHindajad;

import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.pistemeetod.PistemeetodiPiste;

import java.util.List;

public class PistemeetodiLäbimänguHindaja extends LäbimänguHindaja {


    @Override
    public int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudOperatsioonid) {
        //raskusparameeter on tehtavate pistete arv
        int raskusparameeter = 0;
        for (Massiivioperatsioon massiivioperatsioon : tehtudOperatsioonid) {
            if(massiivioperatsioon instanceof PistemeetodiPiste) {
                raskusparameeter += 1;
            }
        }
        return raskusparameeter;
    }
}
