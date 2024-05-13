package main.massiivioperatsioon;


import main.massiiviSeis.MassiiviSeis;

import java.util.Objects;

public abstract class TööalaValimine extends Massiivioperatsioon {
    public TööalaValimine(int uusTööalaAlgus, int uusTööalaleJärgnevIndeks, MassiiviSeis massiivEnneOperatsiooni) {
        super(massiivEnneOperatsiooni);
        if(uusTööalaAlgus >= uusTööalaleJärgnevIndeks) {
            throw new RuntimeException("Tööala algus peab asuma tööala lõpust eespool");
        }
        this.getSeis().setTööalaAlgusIndeks(uusTööalaAlgus);
        this.getSeis().setTööalaleJärgnevIndeks(uusTööalaleJärgnevIndeks);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TööalaValimine tööalaValimine)) {
            return false;
        }

        return Objects.equals(this.getSeis().getTööalaAlgusIndeks(), tööalaValimine.getSeis().getTööalaAlgusIndeks())
                && Objects.equals(this.getSeis().getTööalaleJärgnevIndeks(), tööalaValimine.getSeis().getTööalaleJärgnevIndeks());
    }

    @Override
    public String toString() {
        return "Tööala valimine "+ getSeis().getTööalaAlgusIndeks() + ", " + getSeis().getTööalaleJärgnevIndeks()+". Uus tööala: " + this.getSeis();
    }
}
