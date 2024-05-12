package main.läbimänguHindajad;

import main.Hindamistulemus;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguAlustamine;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.ArrayList;
import java.util.List;

public abstract class LäbimänguHindaja {

    public Hindamistulemus hinda(List<Massiivioperatsioon> tehtudKäigud) {
        if(!(tehtudKäigud.get(0) instanceof LäbimänguAlustamine)) {
            throw new RuntimeException("Esimene käik peab olema läbimängu alustamine");
        }
        if(!(tehtudKäigud.get(tehtudKäigud.size()-1) instanceof LäbimänguLõpetamine)) {
            throw new RuntimeException("Viimane käik peab olema läbimängu lõpetamine");
        }

        Hindamistulemus hindamistulemus = new Hindamistulemus(tehtudKäigud.size()-1); // -1 kuna läbimängu alustamise operatsioon ei ole kasutaja enda otsustatud

        Massiivioperatsioon viimaneKäik = tehtudKäigud.get(0);
        for (int i = 1; i < tehtudKäigud.size(); i++) {
            Massiivioperatsioon praeguneKäik = tehtudKäigud.get(i);

            if(!praeguneKäik.equals(viimaneKäik.järgmineÕigeKäik())) {

                //kui läbimängu on võimalik jätkata või praegune käik on läbimängu lõpetamine ja tulemus on õige
                if(praeguneKäik.kasOnVõimalikLäbimänguJätkata()
                    || (praeguneKäik instanceof LäbimänguLõpetamine && MassiiviTööriistad.kasÕigeTulemus(praeguneKäik.getSeis()))) {

                    //mitteoluline viga
                    hindamistulemus.suurendaValedeKäikudeArvu();
                }
                else {
                    //oluline viga
                    hindamistulemus.setOlulineViga(i);
                    hindamistulemus.setKäikudeArv(i); //kõigi käikude arv kuni praeguseni
                    hindamistulemus.suurendaValedeKäikudeArvu();
                    break;
                }
            }
            viimaneKäik = praeguneKäik;
        }

        //TODO vb peaks raskusparameetril mitte lugema valesid käike
        hindamistulemus.setRaskusparameeter(this.leiaRaskusparameeter(hindamistulemus.getOluliseVeaIndeks() == null ? tehtudKäigud : tehtudKäigud.subList(0, hindamistulemus.getOluliseVeaIndeks())));
        hindamistulemus.setOodatudRaskusparameeter(this.leiaTegelikRaskusparameeter(tehtudKäigud.get(0)));
        return hindamistulemus;

    }

    protected abstract int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud);
    protected int leiaTegelikRaskusparameeter(Massiivioperatsioon esimeneKäik) {
        List<Massiivioperatsioon> käigud = new ArrayList<>();

        Massiivioperatsioon viimatineKäik = esimeneKäik;
        käigud.add(viimatineKäik);

        while(!(viimatineKäik instanceof LäbimänguLõpetamine)) {
            viimatineKäik = viimatineKäik.järgmineÕigeKäik();
            käigud.add(viimatineKäik);
        }

        return leiaRaskusparameeter(käigud);
    }
}
