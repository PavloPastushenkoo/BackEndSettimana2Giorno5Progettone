package org.example;

import java.time.LocalDate;

public class Libro extends Prodotto {
    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, LocalDate annoPubblicazione, Integer numPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return this.autore;
    }

    public String getGenere() {
        return this.genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
