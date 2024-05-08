package main.massiivioperatsioonid;


import main.MassiiviSeis;

import java.util.Objects;

public abstract class TööalaValimineVõiMuutmine extends Massiivioperatsioon {
    public TööalaValimineVõiMuutmine(MassiiviSeis massiivEnneOperatsiooni, int uusTööalaAlgus, int uusTööalaLõpp) {
        super(massiivEnneOperatsiooni);
        this.massiivPealeOperatsiooni.setTööalaAlgusIndeks(uusTööalaAlgus);
        this.massiivPealeOperatsiooni.setTööalaleJärgnevIndeks(uusTööalaLõpp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TööalaValimineVõiMuutmine tööalaValimineVõiMuutmine)) {
            return false;
        }

        return Objects.equals(this.massiivPealeOperatsiooni.getTööalaAlgusIndeks(), tööalaValimineVõiMuutmine.massiivPealeOperatsiooni.getTööalaAlgusIndeks())
                && Objects.equals(this.massiivPealeOperatsiooni.getTööalaleJärgnevIndeks(), getMassiivPealeOperatsiooni().getTööalaleJärgnevIndeks());
    }

    @Override
    public String toString() {
        return "Tööala valimine. Uus tööala: " + this.massiivPealeOperatsiooni;
    }
}
