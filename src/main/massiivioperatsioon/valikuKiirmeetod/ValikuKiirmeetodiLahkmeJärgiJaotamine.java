package main.massiivioperatsioon.valikuKiirmeetod;

import main.massiiviSeis.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioon.LahkmeJärgiJaotamine;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;

public class ValikuKiirmeetodiLahkmeJärgiJaotamine extends LahkmeJärgiJaotamine {

    ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis;

    public ValikuKiirmeetodiLahkmeJärgiJaotamine(MassiiviSeis massiivPealeOperatsiooni, int lahkmeJärgiJaotamisePiiristJärgnevIndeks) {
        super(massiivPealeOperatsiooni, lahkmeJärgiJaotamisePiiristJärgnevIndeks);
    }

    //TODO mõelda kas on parem lahendus kui seda välja ja geti ja seti 3 eri kohas kopeerida
    @Override
    public ValikuKiirmeetodiMassiiviSeis getSeis() {
        return valikuKiirmeetodiMassiiviSeis;
    }

    @Override
    public void setSeis(MassiiviSeis seis) {
        if(seis instanceof ValikuKiirmeetodiMassiiviSeis uusSeis) {
            this.valikuKiirmeetodiMassiiviSeis = uusSeis;
        }
        else {
            throw new RuntimeException("valikukiirmeetodi seis peab olema valikukiirmeetodiseisu isend");
        }
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {//see on võimalik ainult vea korral
            return new ValikuKiirmeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
        }

        if(getSeis().getTööalaleJärgnevIndeks() - getSeis().getTööalaAlgusIndeks() == 1) {//see on võimalik ainult vea korral
            return new LäbimänguLõpetamine(getSeis());
        }
        int praegunePikkus = this.getLahkmeJärgiJaotamisePiiristJärgnevIndeks();
        int oodatavPikkus = getSeis().getVastusePiir();

        if(praegunePikkus == oodatavPikkus
        || (praegunePikkus == getSeis().getTööalaAlgusIndeks() && praegunePikkus+1 == oodatavPikkus)) {
            //kui lahkme järgi jagamine ei muutnud seisu ja läbimäng tuleks peale piiri nihutamist lõpetada
            return new LäbimänguLõpetamine(getSeis());
        }
        if(praegunePikkus > oodatavPikkus) {
            return new ValikuKiirmeetodiTööalaValimine(getSeis().getTööalaAlgusIndeks(), praegunePikkus, getSeis());
        }
        if(praegunePikkus == getSeis().getTööalaAlgusIndeks()) {//kui lahkme järgi jagamine ei muutnud seisu
        return new ValikuKiirmeetodiTööalaValimine(praegunePikkus+1, getSeis().getTööalaleJärgnevIndeks(), getSeis());
        }
        return new ValikuKiirmeetodiTööalaValimine(praegunePikkus, getSeis().getTööalaleJärgnevIndeks(), getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(MassiiviTööriistad.kasTööalaValimata(getSeis())) {
            return true;
        }
        Massiivioperatsioon järgmineÕigeKäik = järgmineÕigeKäik();

        return (järgmineÕigeKäik instanceof LäbimänguLõpetamine
                && ValikuKiirmeetodiTööriistad.kasVähimadElemendidOnEes(valikuKiirmeetodiMassiiviSeis))
                || järgmineÕigeKäik.kasOnVõimalikLäbimänguJätkata();
    }
}
