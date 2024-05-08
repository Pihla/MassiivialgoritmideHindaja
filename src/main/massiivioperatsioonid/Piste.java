package main.massiivioperatsioonid;


import main.MassiiviSeis;

public abstract class Piste extends Massiivioperatsioon {
    private int pisteAlgusIndeks;
    private int pisteLõpuIndeks;

    public Piste(int pisteAlgusIndeks, int pisteLõpuIndeks, MassiiviSeis massiivEnnePistet) {
        super(massiivEnnePistet);
        this.pisteAlgusIndeks = pisteAlgusIndeks;
        this.pisteLõpuIndeks = pisteLõpuIndeks;
        teePiste();
    }

    private void teePiste() {
        //TODO muuta ilusamaks

        int samm;

        if(pisteAlgusIndeks>pisteLõpuIndeks) samm = -1;
        else samm = 1;//TODO teha nii et töötaks sellel puhul ka

        int[] massiiv = massiivPealeOperatsiooni.getMassiiv();
        int tõstetavElement = massiiv[pisteAlgusIndeks];
        int praeguneIndeks = pisteAlgusIndeks;

        while(praeguneIndeks != pisteLõpuIndeks) {
            massiiv[praeguneIndeks] = massiiv[praeguneIndeks+samm];
            praeguneIndeks += samm;
        }
        massiiv[pisteLõpuIndeks] = tõstetavElement;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Piste piste)) {
            return false;
        }

        return this.pisteAlgusIndeks == piste.pisteAlgusIndeks
                && this.pisteLõpuIndeks == piste.pisteLõpuIndeks;
    }

    @Override
    public String toString() {
        return String.format("Piste indeksilt %d indeksile %d. Massiivi seis peale pistet: %s", this.pisteAlgusIndeks, this.pisteLõpuIndeks, this.massiivPealeOperatsiooni);
    }
}
