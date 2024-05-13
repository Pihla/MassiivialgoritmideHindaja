package main.läbimänguHindaja;

import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiPiste;

import java.util.List;

public class MullimeetodiLäbimänguHindaja extends LäbimänguHindaja{
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        //mullimeetodi raskusparameeter on massiivi läbimiste arv

        //loen kokku, mitu korda piste alguskoht on eelmise piste lõpust paremal
        //TODO kontrollida kas see loogika on õige või +-1 vms
        int raskusparameeter = 0;
        int viimasePisteLõpp = Integer.MIN_VALUE;

        for (Massiivioperatsioon käik : tehtudKäigud) {
            if(käik instanceof MullimeetodiPiste piste) {
                //kui praeguse piste alguskoht on eelmise piste lõpust paremal, siis eeldame, et alustati uut ringi
                if(piste.getPisteAlgusIndeks() > viimasePisteLõpp) {
                    viimasePisteLõpp = piste.getPisteLõpuIndeks();
                    raskusparameeter++;
                }
            }
        }
        return raskusparameeter;
    }
}
