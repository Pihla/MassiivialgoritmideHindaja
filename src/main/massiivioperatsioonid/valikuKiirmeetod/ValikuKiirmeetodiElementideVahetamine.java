package main.massiivioperatsioonid.valikuKiirmeetod;

import main.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioonid.ElementideVahetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;

public class ValikuKiirmeetodiElementideVahetamine extends ElementideVahetamine {

    ValikuKiirmeetodiMassiiviSeis seis;
    @Override
    public ValikuKiirmeetodiMassiiviSeis getSeis() {
        return seis;
    }
    int lahe; //lahkme järgi kaheks jaotamise lahkme väärtus
    public ValikuKiirmeetodiElementideVahetamine(int esimeseElemendiIndeks, int teiseElemendiIndeks, ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni, int lahe) {
        super(esimeseElemendiIndeks, teiseElemendiIndeks, massiivEnneOperatsiooni);
        this.seis = massiivEnneOperatsiooni.teeKoopia();//TODO mõelda kuidas saaks mitte siin seda teha
        this.lahe = lahe;
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        return ValikuKiirmeetodiTööriistad.leiaJärgmineÕigeKäik(getSeis(), lahe, getVasakpoolseElemendiIndeks()+1, getParempoolseElemendiIndeks()-1);
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
