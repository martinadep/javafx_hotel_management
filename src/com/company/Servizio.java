package com.company;

public class Servizio extends Extra{
    public final double SCONTO = 0.2;
    public String versione;

    public Servizio(String nome, String versione, double costoSingolo, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.costoSingolo = costoSingolo;
        this.versione = versione;
    }

    @Override
    public double getSconto() {
        if(numero >= 3){
            return getCosto()*SCONTO;
        }
        else return 0;
    }

    @Override
    public String descrizione() {
        return numero + " x " + nome + " " + versione + "(" + costoSingolo + " $): " + String.format("%.2f $\n", getCosto()) ;
    }
}
