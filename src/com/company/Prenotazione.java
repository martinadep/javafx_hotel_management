package com.company;

import java.util.LinkedList;
import java.util.List;

public class Prenotazione implements Comparable<Prenotazione> {
    public static final double COSTO_PERSONA_NOTTE = 50.0;

    public String cognome;
    public int persone;
    public int notti;
    private List<Extra> extras = new LinkedList<>();

    public Prenotazione(String cognome, int persone, int notti) {
        this.cognome = cognome;
        this.persone = persone;
        this.notti = notti;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public double getCostoNotti(){
        return notti*persone*COSTO_PERSONA_NOTTE;
    }

    public double getCostoConExtra(){
        double sum = getCostoNotti();
        for(Extra e : extras){
            sum += e.getCosto();
        }
        return sum;
    }
    public double getScontoExtra(){
        double sum = 0;
        for(Extra e : extras){
            sum += e.getSconto();
        }
        return sum;
    }
    public double getPrezzoFinale(){
        return getCostoConExtra() - getScontoExtra();
    }

    @Override
    public int compareTo(Prenotazione o) {
        return o.persone - persone;
    }
}
