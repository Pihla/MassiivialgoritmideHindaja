package main.kasutajaliides;

import main.läbimänguHindaja.LäbimänguHindaja;
import main.läbimänguHindaja.ValikuKiirmeetodiLäbimänguHindaja;
import main.massiiviSeis.MassiiviSeis;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiLahkmeJärgiJaotamine;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiLäbimänguAlustamine;
import main.massiivioperatsioon.valikuKiirmeetod.ValikuKiirmeetodiTööalaValimine;

import java.util.Arrays;

public class ValikuKiirmeetodiKasutajaliides extends Kasutajaliides{
    @Override
    public void kuvaMeetodiInfo(int[] massiiv) {
        System.out.printf("Alustame valiku kiirmeetodi läbimängu massiivil %s, tuua esimesed %d elementi massiivi algusesse.%n", Arrays.toString(massiiv), vastusePiir);
        System.out.println("Võimalikud käigud: ");
        System.out.println("jaota x y _ z - muudab massiivi seisu, alakriips märgib lahkmekohta");
        System.out.println("tööala algusindeks lõpuindeks - muudab tööala");
    }

    public int vastusePiir = 3;//TODO see tuleks kuidagi mujale tõsta
    @Override
    public LäbimänguAlustamine läbimänguAlustamiseOperatsioon(int[] massiiv) {
        return new ValikuKiirmeetodiLäbimänguAlustamine(new ValikuKiirmeetodiMassiiviSeis(massiiv, null, null, vastusePiir));
    }

    @Override
    public Massiivioperatsioon loeOperatsioon(String[] sisend, MassiiviSeis massiiviSeis) throws ViganeSisendException {
        if(! (massiiviSeis instanceof ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis)) {
            throw new ViganeSisendException("Valiku kiirmeetodi massiivi seis peab olema ValikuKiirmeetodiMassiiviSeis-tüüpi");
        }
        switch (sisend[0]) {
            case "jaota":
                if(sisend.length != valikuKiirmeetodiMassiiviSeis.getMassiiv().length + 2) {
                    throw new ViganeSisendException("Uue massiivi pikkus peab olema sama, kui vana massiivi pikkus. Lisaks peab olema märgitud lahkmekoht.");
                }
                int[] uusMassiiv = new int[valikuKiirmeetodiMassiiviSeis.getMassiiv().length];
                int lahkmeIndeks = -1;
                int parandus = 0;
                for (int i = 1; i < sisend.length; i++) {
                    if(sisend[i].equals("_")) {
                        parandus = -1;
                        lahkmeIndeks = i-1;
                    }
                    else {
                        uusMassiiv[i-1+parandus] = Integer.parseInt(sisend[i]);
                    }
                }
                if(lahkmeIndeks == -1) {
                    throw new ViganeSisendException("Lahe peab olema märgitud.");
                }
                return new ValikuKiirmeetodiLahkmeJärgiJaotamine(new ValikuKiirmeetodiMassiiviSeis(uusMassiiv,
                        valikuKiirmeetodiMassiiviSeis.getTööalaAlgusIndeks(),
                        valikuKiirmeetodiMassiiviSeis.getTööalaleJärgnevIndeks(),
                        valikuKiirmeetodiMassiiviSeis.getVastusePiir()), lahkmeIndeks);
            case "tööala":
                int tööalaAlgus = Integer.parseInt(sisend[1]);
                int tööalaLõpp = Integer.parseInt(sisend[2]);
                return new ValikuKiirmeetodiTööalaValimine(tööalaAlgus, tööalaLõpp, valikuKiirmeetodiMassiiviSeis);
            case "lõpeta":
                return new LäbimänguLõpetamine(valikuKiirmeetodiMassiiviSeis);
            default:
                throw new ViganeSisendException("Vigane käsk.");
        }
    }

    @Override
    public LäbimänguHindaja läbimänguHindaja() {
        return new ValikuKiirmeetodiLäbimänguHindaja();
    }
}
