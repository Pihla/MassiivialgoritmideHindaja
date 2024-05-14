package main;

import main.kasutajaliides.MullimeetodiKasutajaliides;
import main.kasutajaliides.PistemeetodiKasutajaliides;
import main.kasutajaliides.ValikuKiirmeetodiKasutajaliides;
import main.kasutajaliides.ValikumeetodiKasutajaliides;
import main.massiiviSeis.MassiiviSeis;

import static main.KorrektseteLäbimängudeKuvamine.*;

public class Main {


    public static void main(String[] args) {
        //kuvaKõigiMeetoditeKorrektsedLäbimängud(); // kuvab järjest kõikide meetodide korrektsed läbimängud

        //PistemeetodiKasutajaliides kasutajaliides = new PistemeetodiKasutajaliides();
        ValikuKiirmeetodiKasutajaliides kasutajaliides = new ValikuKiirmeetodiKasutajaliides();
        //ValikumeetodiKasutajaliides kasutajaliides = new ValikumeetodiKasutajaliides();
        //MullimeetodiKasutajaliides kasutajaliides = new MullimeetodiKasutajaliides();
        kasutajaliides.meetodiLäbimäng(); //alustab meetodi läbimängu
    }









}
