package com.company;

public abstract class Extra {
    public String nome;
    public int numero;
    public double costoSingolo;

    public double getCosto(){
        return numero * costoSingolo;
    }
    public abstract double getSconto();

    public double getPrezzo(){
        return getCosto() - getSconto();
    }
    public abstract String descrizione();

}
