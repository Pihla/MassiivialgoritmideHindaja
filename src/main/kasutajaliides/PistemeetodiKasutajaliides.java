package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.PistemeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.pistemeetod.PistemeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.pistemeetod.PistemeetodiPiste;
import main.massiivioperatsioon.pistemeetod.PistemeetodiTööalaValimine;

import java.util.Arrays;

public class PistemeetodiKasutajaliides extends Kasutajaliides{
    @Override
    public void kuvaMeetodiInfo(int[] massiiv) {
        System.out.println("Alustame pistemeetodi läbimängu massiivil " + Arrays.toString(massiiv));
        System.out.println("Võimalikud käigud: ");
        System.out.println("piste algusindeks lõpuindeks - teeb massiivil piste");
        System.out.println("tööala algusindeks lõpuindeks - muudab tööala");
        System.out.println("lõpetamine - lõpetab läbimängu");
    }

    @Override
    public LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new PistemeetodiLäbimänguAlustamine(new MassiiviSeis(massiiv, null, null));
    }

    @Override
    public Massiivioperatsioon loeOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        switch (sisend[0]) {
            case "piste":
                int pisteAlgus = Integer.parseInt(sisend[1]);
                int pisteLõpp = Integer.parseInt(sisend[2]);
                return new PistemeetodiPiste(pisteAlgus, pisteLõpp, massiiviSeis);
            case "tööala":
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new PistemeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, massiiviSeis);
            case "lõpetamine":
                return new LäbimänguLõpetamine(massiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk");
        }
    }

    @Override
    public LäbimänguHindaja läbimänguHindaja() {
        return new PistemeetodiLäbimänguHindaja();
    }
}
