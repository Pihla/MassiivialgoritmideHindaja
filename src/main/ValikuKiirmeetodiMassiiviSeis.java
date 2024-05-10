package main;

public class ValikuKiirmeetodiMassiiviSeis extends MassiiviSeis{
    int vastusePiir; //esimese elemendi indeks, mis ei jää vähimate elementide hulka

    public ValikuKiirmeetodiMassiiviSeis(int[] massiiv, Integer tööalaAlgusIndeks, Integer tööalaleJärgnevIndeks, int leitavateElementideArv) {
        super(massiiv, tööalaAlgusIndeks, tööalaleJärgnevIndeks);
        this.vastusePiir = leitavateElementideArv;
    }

    public int getVastusePiir() {
        return vastusePiir;
    }

    @Override
    public String toString() {
        return super.toString() + ", otsime esimest "+vastusePiir + " elementi";
    }

    @Override
    public ValikuKiirmeetodiMassiiviSeis teeKoopia() {
        return new ValikuKiirmeetodiMassiiviSeis(this.massiiv, this.tööalaAlgusIndeks, this.tööalaleJärgnevIndeks, this.vastusePiir);
    }
}
