package main.läbimänguHindajad;

import main.massiivioperatsioonid.Massiivioperatsioon;

import java.util.List;

public class ValikuKiirmeetodiLäbimänguHindaja extends LäbimänguHindaja{
    @Override
    protected int leiaRaskusparameeter(List<Massiivioperatsioon> tehtudKäigud) {
        return 1;
    }
}
