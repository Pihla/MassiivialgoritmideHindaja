package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.MullimeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.mullimeetod.MullimeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.mullimeetod.MullimeetodiPiste;
import main.massiivioperatsioon.mullimeetod.MullimeetodiTööalaValimine;

import java.util.Arrays;

public class MullimeetodiKasutajaliides extends Kasutajaliides {
    @Override
    protected void kuvaVõimalikudOperatsioonid() {
        System.out.println("piste <algusindeks> <lõpuindeks> - teeb massiivil piste");
        super.kuvaVõimalikudOperatsioonid();
    }

    @Override
    protected LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new MullimeetodiLäbimänguAlustamine(new MassiiviSeis(massiiv, null, null));
    }

    @Override
    protected void läbimänguAlustamiseSõnum(int[] massiiv) {
        System.out.println("Alustame mullimeetodi läbimängu massiivil " + Arrays.toString(massiiv)
                + ". Kasutame mullimeetodi versiooni, kus massiiv loetakse sorteerituks, kui selle läbimisel ei tehta ühtegi vahetust.");
    }

    @Override
    protected Massiivioperatsioon leiaOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        switch (sisend[0]) {
            case "piste":
                if(sisend.length != 3) {
                    throw new ViganeSisendException("Piste vajab kahte argumenti (piste algus ja lõpp).");
                }
                int pisteAlgus = Integer.parseInt(sisend[1]);
                int pisteLõpp = Integer.parseInt(sisend[2]);
                return new MullimeetodiPiste(pisteAlgus, pisteLõpp, massiiviSeis);
            case "tööala":
                if(sisend.length != 3) {
                    throw new ViganeSisendException("Tööala muutmine vajab kahte argumenti (tööala algus ja lõpp).");
                }
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new MullimeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, massiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk.");
        }
    }

    @Override
    protected LäbimänguHindaja läbimänguHindaja() {
        return new MullimeetodiLäbimänguHindaja();
    }
}