package org.example;

import java.time.LocalDate;

public class Prodotto {
    private String isbn;
    private String titolo;
    private LocalDate dataPubblicazione;
    private Integer numPagine;

    public Prodotto(String isbn, String titolo, LocalDate dataPubblicazione, Integer numPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.numPagine = numPagine;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public LocalDate getDataPubblicazione() {
        return this.dataPubblicazione;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", numPagine=" + numPagine +
                '}';
    }

    public String getTitolo() {
        return this.titolo;
    }

    public Integer getNumPagine() {
        return this.numPagine;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public void setNumPagine(Integer numPagine) {
        this.numPagine = numPagine;
    }
}
