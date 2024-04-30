package org.example;

import java.time.LocalDate;

public class Rivista extends Prodotto {
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, LocalDate annoPubblicazione, Integer numPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return this.periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
