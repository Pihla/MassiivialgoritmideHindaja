package main.massiivioperatsioonid;


import main.MassiiviSeis;

import java.util.Objects;

public abstract class TööalaValimine extends Massiivioperatsioon {
    public TööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
        if(uusTööalaAlgus >= uusTööalaleJärgnevIndeks) {
            throw new RuntimeException("Tööala algus peab asuma tööala lõpust eespool");
        }
        this.seis.setTööalaAlgusIndeks(uusTööalaAlgus);
        this.seis.setTööalaleJärgnevIndeks(uusTööalaleJärgnevIndeks);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TööalaValimine tööalaValimine)) {
            return false;
        }

        return Objects.equals(this.seis.getTööalaAlgusIndeks(), tööalaValimine.seis.getTööalaAlgusIndeks())
                && Objects.equals(this.seis.getTööalaleJärgnevIndeks(), tööalaValimine.seis.getTööalaleJärgnevIndeks());
    }

    @Override
    public String toString() {
        return "Tööala valimine "+ seis.getTööalaAlgusIndeks() + ", " + seis.getTööalaleJärgnevIndeks()+". Uus tööala: " + this.seis;
    }
}
