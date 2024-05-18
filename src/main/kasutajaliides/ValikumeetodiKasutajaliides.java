package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.ValikumeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiElementideVahetamine;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikumeetod.ValikumeetodiTööalaValimine;

import java.util.Arrays;

public class ValikumeetodiKasutajaliides extends Kasutajaliides {
    @Override
    protected void kuvaVõimalikudOperatsioonid() {
        System.out.println("vaheta <indeks1> <indeks2> - vahetab 2 elementi");
        super.kuvaVõimalikudOperatsioonid();
    }

    @Override
    protected LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new ValikumeetodiLäbimänguAlustamine(new MassiiviSeis(massiiv, null, null));
    }

    @Override
    protected void läbimänguAlustamiseSõnum(int[] massiiv) {
        System.out.println("Alustame valikumeetodi läbimängu massiivil " + Arrays.toString(massiiv) + ".");
    }

    @Override
    protected Massiivioperatsioon leiaOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        switch (sisend[0]) {
            case "vaheta":
                if(sisend.length != 3) {
                    throw new ViganeSisendException("Elementide vahetamine vajab kahte argumenti.");
                }
                int indeks1 = Integer.parseInt(sisend[1]);
                int indeks2 = Integer.parseInt(sisend[2]);
                return new ValikumeetodiElementideVahetamine(indeks1, indeks2, massiiviSeis);
            case "tööala":
                if(sisend.length != 3) {
                    throw new ViganeSisendException("Tööala muutmine vajab kahte argumenti (tööala algus ja lõpp).");
                }
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new ValikumeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, massiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk.");
        }
    }

    @Override
    protected LäbimänguHindaja läbimänguHindaja() {
        return new ValikumeetodiLäbimänguHindaja();
    }
}
