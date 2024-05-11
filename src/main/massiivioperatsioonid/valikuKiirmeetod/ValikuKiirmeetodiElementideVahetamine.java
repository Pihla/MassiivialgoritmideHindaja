package main.massiivioperatsioonid.valikuKiirmeetod;

import main.MassiiviSeis;
import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.ElementideVahetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class ValikuKiirmeetodiElementideVahetamine extends ElementideVahetamine {

    ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis;
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

    public int getLahe() {
        return lahe;
    }

    int lahe; //lahkme järgi kaheks jaotamise lahkme väärtus
    public ValikuKiirmeetodiElementideVahetamine(int esimeseElemendiIndeks, int teiseElemendiIndeks, ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni, int lahe) {
        super(esimeseElemendiIndeks, teiseElemendiIndeks, massiivEnneOperatsiooni);
        this.lahe = lahe;
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return ValikuKiirmeetodiTööriistad.leiaJärgmineÕigeKäik(getSeis(), lahe, getVasakpoolseElemendiIndeks()+1, getParempoolseElemendiIndeks()-1);
   /*
   Massiivioperatsioon käik = ValikuKiirmeetodiTööriistad.leiaJärgmineÕigeKäik(getSeis(), lahe, getVasakpoolseElemendiIndeks() + 1, getParempoolseElemendiIndeks() - 1);
        if(käik == null) {
            käik =
        }
        return käik;
    */
     }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        //TODO mõelda siia midagi paremat

        //teen käike kuni tööala muutmiseni ja siis otsustan
        Massiivioperatsioon prageuneKäik = this;
        while(prageuneKäik instanceof ValikuKiirmeetodiElementideVahetamine valikuKiirmeetodiElementideVahetamine) {
            prageuneKäik = valikuKiirmeetodiElementideVahetamine.järgmineÕigeKäik();
        }
        return prageuneKäik.kasOnVõimalikLäbimänguJätkata();
    }
}
