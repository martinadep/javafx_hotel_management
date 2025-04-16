package com.company;

import java.util.Comparator;

public class ComparatrorByName implements Comparator<Prenotazione> {
    @Override
    public int compare(Prenotazione o1, Prenotazione o2) {
        return o1.cognome.compareTo(o2.cognome);
    }
}
