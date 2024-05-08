package main.massiivioperatsioonid.pistemeetod;


import main.MassiiviSeis;
import main.MassiiviTööriistad;
import main.massiivioperatsioonid.LäbimänguLõpetamine;
import main.massiivioperatsioonid.Massiivioperatsioon;
import main.massiivioperatsioonid.TööalaValimineVõiMuutmine;

public class PistemeetodiTööalaValimineVõiMuutmine extends TööalaValimineVõiMuutmine {


    public PistemeetodiTööalaValimineVõiMuutmine(int uusTööalaAlgus, int uusTööalaLõpp, MassiiviSeis massiivEnneOperatsiooni) {
        super(uusTööalaAlgus, uusTööalaLõpp, massiivEnneOperatsiooni);
    }

    @Override
    public Massiivioperatsioon järgmineÕigeOperatsioon() {
        if(this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks() > 1
                && this.getMassiivPealeOperatsiooni().getMassiiv()[this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()-1] <
                this.getMassiivPealeOperatsiooni().getMassiiv()[this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()-2]) {

            int elemendiIndeks = this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()-1;
            int pistetavNumber = this.getMassiivPealeOperatsiooni().getMassiiv()[elemendiIndeks];

            while(elemendiIndeks > this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks() &&
                    this.getMassiivPealeOperatsiooni().getMassiiv()[elemendiIndeks-1] > pistetavNumber) {
                elemendiIndeks--;
            }
            return new PistemeetodiPiste(this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()-1, elemendiIndeks, this.getMassiivPealeOperatsiooni());
        }
        if(this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks() == this.getMassiivPealeOperatsiooni().getMassiiv().length) {
            return new LäbimänguLõpetamine(this.getMassiivPealeOperatsiooni());
        }
        return new PistemeetodiTööalaValimineVõiMuutmine(this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()+1, this.getMassiivPealeOperatsiooni());
    }

    @Override
    public boolean kasOnVõimalikLäbimänguJätkata() {
        if(!MassiiviTööriistad.kasOnÕigedElemendidKuniIndeksini(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks())) {
            return false;
        }
        return MassiiviTööriistad.kasVahemikOnSorteeritud(this.getMassiivPealeOperatsiooni().getMassiiv(), this.getMassiivPealeOperatsiooni().getTööalaAlgusIndeks(), this.getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks()-1);
    }


}
