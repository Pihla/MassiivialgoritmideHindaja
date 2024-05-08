package main.läbimänguHindajad;

import main.Hindamistulemus;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.List;

public abstract class LäbimänguHindaja {

    public Hindamistulemus hinda(List<Massiivioperatsioon> tehtudOperatsioonid) {
        if(!(tehtudOperatsioonid.get(0) instanceof LäbimänguAlustamine)) {
            throw new RuntimeException("Esimene operatsioon peab olema läbimängu alustamine");
        }
        if(!(tehtudOperatsioonid.get(tehtudOperatsioonid.size()-1) instanceof LäbimänguLõpetamine)) {
            throw new RuntimeException("Viimane operatsioon peab olema läbimängu lõpetamine");
        }

        Hindamistulemus hindamistulemus = new Hindamistulemus(tehtudOperatsioonid.size()-1); // -1 kuna läbimängu alustamise operatsioon ei ole kasutaja enda otsustatud

        Massiivioperatsioon viimaneOperatsioon = tehtudOperatsioonid.get(0);
        for (int i = 1; i < tehtudOperatsioonid.size(); i++) {
            Massiivioperatsioon praeguneOperatsioon = tehtudOperatsioonid.get(i);

            if(!praeguneOperatsioon.equals(viimaneOperatsioon.järgmineÕigeOperatsioon())) {
                //System.out.println("VIGA oleks pidanud tegema " + viimaneOperatsioon.järgmineÕigeOperatsioon());
                System.out.println("VIGA, olekust " + viimaneOperatsioon + " tehti " + praeguneOperatsioon + " aga oleks pidanud " + viimaneOperatsioon.järgmineÕigeOperatsioon());

                if(praeguneOperatsioon.kasOnVõimalikLäbimänguJätkata()) {
                    //mitteoluline viga
                    hindamistulemus.suurendaValedeKäikudeArvu();
                }
                else {
                    //oluline viga
                    hindamistulemus.setOlulineViga(i);
                    hindamistulemus.setKäikudeArv(hindamistulemus.getOluliseVeaIndeks()); //kõigi käikude arv kuni praeguseni
                    hindamistulemus.suurendaValedeKäikudeArvu();
                    break;
                }
            }
            viimaneOperatsioon = praeguneOperatsioon;
        }

        hindamistulemus.setRaskusparameeter(this.leiaRaskusparameeter(tehtudOperatsioonid));
        hindamistulemus.setOodatudRaskusparameeter(this.leiaTegelikRaskusparameeter(tehtudOperatsioonid.get(0)));
        return hindamistulemus;

    }

    protected abstract int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudOperatsioonid);
    protected int leiaTegelikRaskusparameeter(Massiivioperatsioon esimeneOperatsioon) {
        List<Massiivioperatsioon> operatsioonid = new ArrayList<>();

        Massiivioperatsioon viimaneOperatsioon = esimeneOperatsioon;
        operatsioonid.add(viimaneOperatsioon);

        while(!(viimaneOperatsioon instanceof LäbimänguLõpetamine)) {
            viimaneOperatsioon = viimaneOperatsioon.järgmineÕigeOperatsioon();
            operatsioonid.add(viimaneOperatsioon);
        }

        return leiaRaskusparameeter(operatsioonid);
    }
}
