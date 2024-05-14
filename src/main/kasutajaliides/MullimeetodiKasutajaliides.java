package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.MullimeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioon.mullimeetod.MullimeetodiTööalaValimine;

import java.util.Arrays;

public class MullimeetodiKasutajaliides extends Kasutajaliides {
    @Override
    public void kuvaMeetodiInfo(int[] massiiv) {
        System.out.println("Alustame mullimeetodi läbimängu massiivil " + Arrays.toString(massiiv));
        System.out.println("Võimalikud käigud: ");
        System.out.println("piste algusindeks lõpuindeks - teeb massiivil piste");
        System.out.println("tööala algusindeks lõpuindeks - muudab tööala");
    }

    @Override
    public LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new MullimeetodiLäbimänguAlustamine(new MassiiviSeis(massiiv, null, null));
    }

    @Override
    public Massiivioperatsioon loeOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        switch (sisend[0]) {
            case "piste":
                int pisteAlgus = Integer.parseInt(sisend[1]);
                int pisteLõpp = Integer.parseInt(sisend[2]);
                return new MullimeetodiPiste(pisteAlgus, pisteLõpp, massiiviSeis);
            case "tööala":
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new MullimeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, massiiviSeis);
            case "lõpeta":
                return new LäbimänguLõpetamine(massiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk");
        }
    }

    @Override
    public LäbimänguHindaja läbimänguHindaja() {
        return new MullimeetodiLäbimänguHindaja();
    }
}
