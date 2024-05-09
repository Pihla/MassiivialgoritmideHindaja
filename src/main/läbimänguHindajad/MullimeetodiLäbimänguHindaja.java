package main.läbimänguHindajad;

import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.mullimeetod.MullimeetodiPiste;

import java.util.List;

public class MullimeetodiLäbimänguHindaja extends LäbimänguHindaja{
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        //mullimeetodi raskusparameeter on massiivi läbimiste arv

        int raskusparameeter = 0;
        int viimasePisteLõpp = Integer.MIN_VALUE;

        //TODO kontrollida kas see loogika on õige või +-1 vms
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
