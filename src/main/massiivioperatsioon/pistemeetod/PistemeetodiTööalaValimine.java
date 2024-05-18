package main.massiivioperatsioon.pistemeetod;


import main.MassiiviTööriistad;
import main.massiiviSeis.MassiiviSeis;
import main.massiivioperatsioon.LäbimänguLõpetamine;
import main.massiivioperatsioon.Massiivioperatsioon;
import main.massiivioperatsioon.TööalaValimine;

public class PistemeetodiTööalaValimine extends TööalaValimine {

    public PistemeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        MassiiviSeis seis = getSeis();
        if (seis.getTööalaleJärgnevIndeks() - seis.getTööalaAlgusIndeks() >= 2
                && seis.getMassiiv()[seis.getTööalaleJärgnevIndeks() - 1] <
                seis.getMassiiv()[seis.getTööalaleJärgnevIndeks() - 2]) {

            int elemendiIndeks = seis.getTööalaleJärgnevIndeks() - 1;
            int pistetavNumber = seis.getMassiiv()[elemendiIndeks];

            while (elemendiIndeks > seis.getTööalaAlgusIndeks() &&
                    seis.getMassiiv()[elemendiIndeks - 1] > pistetavNumber) {
                elemendiIndeks--;
            }
            return new PistemeetodiPiste(seis.getTööalaleJärgnevIndeks() - 1, elemendiIndeks, seis);
        }
        if (seis.getTööalaleJärgnevIndeks() == seis.getMassiiv().length) {
            return new LäbimänguLõpetamine(seis);
        }
        return new PistemeetodiTööalaValimine(seis.getTööalaAlgusIndeks(), seis.getTööalaleJärgnevIndeks() + 1, seis);
    }

    @Override
    public boolean läbimänguOnVõimalikJätkata() {
        if (PistemeetodiTööriistad.valedElemendidEnneIndeksit(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(getSeis().getMassiiv(), getSeis().getTööalaAlgusIndeks(), getSeis().getTööalaleJärgnevIndeks() - 1);
    }


}
