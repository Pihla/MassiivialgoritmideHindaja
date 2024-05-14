package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.ValikumeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiElementideVahetamine;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiTööalaValimine;

import java.util.Arrays;

public class ValikumeetodiKasutajaliides extends Kasutajaliides{
    @Override
    public void kuvaMeetodiInfo(int[] massiiv) {
        System.out.println("Alustame valikumeetodi läbimängu massiivil " + Arrays.toString(massiiv));
        System.out.println("Võimalikud käigud: ");
        System.out.println("vahetus indeks1 indeks2 - vahetab 2 elementi");
        System.out.println("tööala algusindeks lõpuindeks - muudab tööala");
        System.out.println("lõpetamine - lõpetab läbimängu");
    }

    @Override
    public LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new ValikumeetodiLäbimänguAlustamine(new MassiiviSeis(massiiv, null, null));
    }

    @Override
    public Massiivioperatsioon loeOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        switch (sisend[0]) {
            case "vahetus":
                int indeks1 = Integer.parseInt(sisend[1]);
                int indeks2 = Integer.parseInt(sisend[2]);
                return new ValikumeetodiElementideVahetamine(indeks1, indeks2, massiiviSeis);
            case "tööala":
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new ValikumeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, massiiviSeis);
            case "lõpetamine":
                return new LäbimänguLõpetamine(massiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk");
        }
    }

    @Override
    public LäbimänguHindaja läbimänguHindaja() {
        return new ValikumeetodiLäbimänguHindaja();
    }
}