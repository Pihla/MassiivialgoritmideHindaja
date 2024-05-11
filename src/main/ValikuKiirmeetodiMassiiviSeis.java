package main;

public class ValikuKiirmeetodiMassiiviSeis extends MassiiviSeis{
    int vastusePiir; //esimese elemendi indeks, mis ei jää vähimate elementide hulka

    public ValikuKiirmeetodiMassiiviSeis(int[] massiiv, Integer tööalaAlgusIndeks, Integer tööalaleJärgnevIndeks, int vastusePiir) {
        super(massiiv, tööalaAlgusIndeks, tööalaleJärgnevIndeks);
        this.vastusePiir = vastusePiir;
    }

    public int getVastusePiir() {
        return vastusePiir;
    }


    @Override
    public ValikuKiirmeetodiMassiiviSeis teeKoopia() {
        return new ValikuKiirmeetodiMassiiviSeis(this.massiiv, this.tööalaAlgusIndeks, this.tööalaleJärgnevIndeks, this.vastusePiir);
    }
}
