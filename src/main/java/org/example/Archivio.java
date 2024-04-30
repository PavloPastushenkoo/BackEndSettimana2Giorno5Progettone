package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio {
    private List<Prodotto> catalogo = new ArrayList<>();

    public void aggiungiProdotto(Prodotto prodotto) throws IllegalArgumentException {
        if (catalogo.stream().noneMatch(prodottoP -> prodottoP.getIsbn().equals(prodotto.getIsbn()))) {
            catalogo.add(prodotto);
            System.out.println("Aggiunto prodotto all'archivio. ISBN: " + prodotto.getIsbn());
        } else {
            throw new IllegalArgumentException("ISBN duplicato: "+prodotto.getIsbn());
        }
    }

    public void rimuoviProdotto(String isbn) throws Exception {
        if (catalogo.removeIf(prodotto -> prodotto.getIsbn().equals(isbn))) {
            System.out.println("Prodotto rimosso con successo. ISBN: "+isbn);
        } else {
            throw new Exception("Prodotto non trovato");
        };
    }

    public Optional<Prodotto> ricercaPerISBN(String isbn) {
        return catalogo.stream()
                .filter(prodotto -> prodotto.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Prodotto> ricercaPerAnnoPubblicazione(Integer anno) {
        return catalogo.stream().filter(prodotto -> prodotto.
                        getDataPubblicazione().
                        getYear() == anno
                ).
                collect(Collectors.toList());
    }

    public List<Prodotto> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(prodotto -> prodotto instanceof Libro)
                .map(prodotto -> (Libro) prodotto)
                .filter(libro -> libro.getAutore().contains(autore))
                .collect(Collectors.toList());
    }

    public void salvaSuDisco(String nomeFile) throws IOException {
        String data = catalogo.stream()
                .map(prodotto -> {
                    if (prodotto instanceof Libro) {
                        return this.libro((Libro) prodotto);
                    } else if (prodotto instanceof Rivista) {
                        return this.rivista((Rivista) prodotto);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.joining(System.lineSeparator()));
        FileUtils.writeStringToFile(new File(nomeFile), data, "UTF-8");
    }

    public List<Prodotto> caricaDaDisco(String nomeFile) throws IOException {
        List<String> lines = FileUtils.readLines(new File(nomeFile), "UTF-8");
        return lines.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        String isbn = parts[0];
                        String titolo = parts[1];
                        LocalDate annoPubblicazione = LocalDate.parse(parts[2]); // Assuming the date is in ISO format
                        int numPagine = Integer.parseInt(parts[3]);
                        String autore = parts[4];
                        String genere = parts[5];
                        return new Libro(isbn, titolo, annoPubblicazione, numPagine, autore, genere);
                    } else if (parts.length == 5) {
                        String isbn = parts[0];
                        String titolo = parts[1];
                        LocalDate annoPubblicazione = LocalDate.parse(parts[2]); // Assuming the date is in ISO format
                        int numPagine = Integer.parseInt(parts[3]);
                        String periodicita = parts[4];
                        if (periodicita != null) {
                            return new Rivista(isbn, titolo, annoPubblicazione, numPagine,
                                    Periodicita.fromString(periodicita));
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String libro(Libro book) {
        return book.getIsbn() +
                "," + book.getTitolo() +
                "," + book.getDataPubblicazione() +
                "," + book.getNumPagine() +
                "," + book.getAutore() +
                "," + book.getGenere();
    }

    private String rivista(Rivista news) {
        return news.getIsbn() +
                "," + news.getTitolo() +
                "," + news.getDataPubblicazione() +
                "," + news.getNumPagine() +
                "," + news.getPeriodicita();
    }

    @Override
    public String toString() {
        return "Archivio{" +
                "catalogo=" + this.catalogo +
                '}';
    }
}
