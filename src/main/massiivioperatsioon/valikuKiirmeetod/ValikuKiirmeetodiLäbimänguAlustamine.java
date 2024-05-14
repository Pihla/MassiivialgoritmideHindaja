package main.massiivioperatsioon.valikuKiirmeetod;

import main.massiiviSeis.MassiiviSeis;
import main.massiiviSeis.ValikuKiirmeetodiMassiiviSeis;
import main.massiivioperatsioon.LäbimänguAlustamine;
import main.massiivioperatsioon.Massiivioperatsioon;

public class ValikuKiirmeetodiLäbimänguAlustamine extends LäbimänguAlustamine {
    public ValikuKiirmeetodiLäbimänguAlustamine(ValikuKiirmeetodiMassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
    }

    ValikuKiirmeetodiMassiiviSeis valikuKiirmeetodiMassiiviSeis;
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
        return new ValikuKiirmeetodiTööalaValimine(0, getSeis().getMassiiv().length, getSeis());
    }

    @Override
    public String toString() {
        return String.format("Valiku kiirmeetodi läbimängu alustamine massiivil %s.", this.getSeis());
    }
}
