package main;


import java.util.Arrays;

public class MassiiviSeis {
    int[] massiiv;
    Integer tööalaAlgusIndeks;
    Integer tööalaleJärgnevIndeks;

    public int[] getMassiiv() {
        return massiiv;
    }

    public Integer getTööalaAlgusIndeks() {
        return tööalaAlgusIndeks;
    }

    public Integer getTööalaleJärgnevIndeks() {
        return tööalaleJärgnevIndeks;
    }

    public void setTööalaAlgusIndeks(int tööalaAlgusIndeks) {
        this.tööalaAlgusIndeks = tööalaAlgusIndeks;
    }

    public void setTööalaleJärgnevIndeks(int tööalaleJärgnevIndeks) {
        this.tööalaleJärgnevIndeks = tööalaleJärgnevIndeks;
    }

    public MassiiviSeis(int[] massiiv, Integer tööalaAlgusIndeks, Integer tööalaleJärgnevIndeks) {
        this.massiiv = Arrays.copyOf(massiiv, massiiv.length);
        this.tööalaAlgusIndeks = tööalaAlgusIndeks;
        this.tööalaleJärgnevIndeks = tööalaleJärgnevIndeks;
    }

    public MassiiviSeis teeKoopia() {
        return new MassiiviSeis(this.massiiv, this.tööalaAlgusIndeks, this.tööalaleJärgnevIndeks);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.massiiv.length; i++) {
            if(this.tööalaAlgusIndeks != null && this.getTööalaleJärgnevIndeks() != null && (i == this.tööalaAlgusIndeks || i == this.tööalaleJärgnevIndeks)) {
                sb.append("|");
                if(i != this.massiiv.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append(massiiv[i]);
            if(i != this.massiiv.length - 1) {
                sb.append(" ");
            }
        }
        if(this.getTööalaleJärgnevIndeks() != null && this.tööalaleJärgnevIndeks == this.massiiv.length) {
            sb.append("|");
        }
        sb.append("]");
        return sb.toString();
    }
}
