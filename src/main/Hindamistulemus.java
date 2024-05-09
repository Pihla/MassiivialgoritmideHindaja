package main;

public class Hindamistulemus {
    private int käikudeArv;
    private int valedeKäikudeArv = 0;
    private Integer oluliseVeaIndeks;
    private int raskusparameeter;
    private int oodatudRaskusparameeter;

    //TODO vaadata üle kas selle klassi kasutamine on loogiline
    public Hindamistulemus(int käikudeArv) {
        this.käikudeArv = käikudeArv;
    }

    public void suurendaValedeKäikudeArvu() {
        this.valedeKäikudeArv += 1;
    }
    public int getKäikudeArv() {
        return käikudeArv;
    }

    public int getValedeKäikudeArv() {
        return valedeKäikudeArv;
    }

    public Integer getOluliseVeaIndeks() {
        return oluliseVeaIndeks;
    }

    public void setKäikudeArv(int käikudeArv) {
        this.käikudeArv = käikudeArv;
    }

    public void setOlulineViga(Integer oluliseVeaIndeks) {
        this.oluliseVeaIndeks = oluliseVeaIndeks;
    }

    public int getRaskusparameeter() {
        return raskusparameeter;
    }

    public void setRaskusparameeter(int raskusparameeter) {
        this.raskusparameeter = raskusparameeter;
    }

    public int getOodatudRaskusparameeter() {
        return oodatudRaskusparameeter;
    }

    public void setOodatudRaskusparameeter(int oodatudRaskusparameeter) {
        this.oodatudRaskusparameeter = oodatudRaskusparameeter;
    }

    public double arvutaPunktid() {
        int õigeidKäike = käikudeArv-valedeKäikudeArv;
        double õigeteKäikudeOsakaal = 1.0*õigeidKäike/käikudeArv;
        double raskuseSuhtelineHinnang = 1.0*raskusparameeter/oodatudRaskusparameeter;
        raskuseSuhtelineHinnang = Math.min(raskuseSuhtelineHinnang, 1);

        return õigeteKäikudeOsakaal * raskuseSuhtelineHinnang;
    }

    public String toString() {
        int õigeidKäike = käikudeArv-valedeKäikudeArv;
        double õigeteKäikudeOsakaal = 1.0*õigeidKäike/käikudeArv;
        double raskuseSuhtelineHinnang = 1.0*raskusparameeter/oodatudRaskusparameeter;
        String oluliseVeaInfo = oluliseVeaIndeks == null ? "puudub" : "indeksil "+oluliseVeaIndeks;

        return String.format("Raskusparameeter %d/%d ehk %.2f, õigeid käike %d/%d ehk %.2f, oluline viga %s, kokku punkte %.2f", raskusparameeter, oodatudRaskusparameeter, raskuseSuhtelineHinnang, õigeidKäike, käikudeArv, õigeteKäikudeOsakaal, oluliseVeaInfo, arvutaPunktid());

    }
}
