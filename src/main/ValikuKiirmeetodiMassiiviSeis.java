package main;

public class ValikuKiirmeetodiMassiiviSeis extends MassiiviSeis{
    int vastusePiir; //esimese elemendi indeks, mis ei jää vähimate elementide hulka. võrdne enne piiri olevate elementide arvuga

    public ValikuKiirmeetodiMassiiviSeis(int[] massiiv, Integer tööalaAlgusIndeks, Integer tööalaleJärgnevIndeks, int vastusePiir) {
        super(massiiv, tööalaAlgusIndeks, tööalaleJärgnevIndeks);
        if(vastusePiir <= 0)
            throw new RuntimeException("Valiku kiirmeetodiga tuleb tuua massiivi ette otsa vähemalt 1 element");
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
