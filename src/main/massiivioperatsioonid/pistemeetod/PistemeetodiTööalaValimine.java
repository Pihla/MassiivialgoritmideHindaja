package main.massiivioperatsioonid.pistemeetod;


import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimine;

public class PistemeetodiTööalaValimine extends TööalaValimine {


    public PistemeetodiTööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaleJärgnevIndeks, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeKäik() {
        if(this.getSeis().getTööalaleJärgnevIndeks() > 1
                && this.getSeis().getMassiiv()[this.getSeis().getTööalaleJärgnevIndeks()-1] <
                this.getSeis().getMassiiv()[this.getSeis().getTööalaleJärgnevIndeks()-2]) {

            int elemendiIndeks = this.getSeis().getTööalaleJärgnevIndeks()-1;
            int pistetavNumber = this.getSeis().getMassiiv()[elemendiIndeks];

            while(elemendiIndeks > this.getSeis().getTööalaAlgusIndeks() &&
                    this.getSeis().getMassiiv()[elemendiIndeks-1] > pistetavNumber) {
                elemendiIndeks--;
            }
            return new PistemeetodiPiste(this.getSeis().getTööalaleJärgnevIndeks()-1, elemendiIndeks, this.getSeis());
        }
        if(this.getSeis().getTööalaleJärgnevIndeks() == this.getSeis().getMassiiv().length) {
            return new LäbimänguLõpetamine(this.getSeis());
        }
        return new PistemeetodiTööalaValimine(this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks()+1, this.getSeis());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(!MassiiviTööriistad.kasOnÕigedElemendidKuniIndeksini(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getSeis().getMassiiv(), this.getSeis().getTööalaAlgusIndeks(), this.getSeis().getTööalaleJärgnevIndeks()-1);
    }


}
