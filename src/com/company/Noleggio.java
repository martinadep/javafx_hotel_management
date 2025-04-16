package com.company;

public class Noleggio extends Extra{
    public final double SCONTO = 0.05;
    public int taglia;

    public Noleggio(String nome, int taglia, double costoSingolo, int giorni) {
        this.nome = nome;
        this.numero = giorni;
        this.costoSingolo = costoSingolo;
        this.taglia = taglia;
    }

    @Override
    public double getSconto() {
        return (numero-1)*SCONTO*getCosto();
    }

    @Override
    public String descrizione() {
        return nome + ", tg: " + taglia + " (" + costoSingolo + " $ x " + numero + "gg): " + String.format("%.2f $\n", getCosto());
    }
}
