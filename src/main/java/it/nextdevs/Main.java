package it.nextdevs;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        try {
            archivio.aggiungiProdotto(new Libro("8831003380", "Harry Potter e la pietra filosofale", LocalDate.of(1997, 1,
                    1), 304, "J. K. Rowling", "Fantasy"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            archivio.aggiungiProdotto(new Libro("8831003380", "Harry Potter e la pietra filosofale", LocalDate.of(1997, 1,
                    1), 304, "J. K. Rowling", "Fantasy"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            archivio.aggiungiProdotto(new Libro("9780439784542", "Harry Potter e il principe mezzosangue ", LocalDate.of(2005, 7,
                    16), 304, "J. K. Rowling", "Fantasy"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            archivio.aggiungiProdotto(new Rivista("977038156000", "TV Sorrisi e Canzoni", LocalDate.of(2024,
                    4, 1), 50, Periodicita.SETTIMANALE));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            archivio.aggiungiProdotto(new Rivista("977038156001", "TV Sorrisi e Canzoni", LocalDate.of(2023,
                    4, 1), 50, Periodicita.SETTIMANALE));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("----------------------");
        System.out.println("Rimozione prodotto con ISBN");
        try {
            archivio.rimuoviProdotto("977038156001");
        } catch (Exception e) {
            System.err.println("Errore: "+e.getMessage());
        }
        System.out.println("----------------------");
        System.out.println("Ricerca per ISBN: "+archivio.ricercaPerISBN("8831003380"));

        List<Prodotto> prodottiAnno2024 = archivio.ricercaPerAnnoPubblicazione(2024);
        System.out.println("----------------------");
        System.out.println("Prodotti 2024:");
        prodottiAnno2024.forEach(System.out::println);

        System.out.println("----------------------");
        List<Prodotto> ricercaPerAutore = archivio.ricercaPerAutore("Rowling");
        System.out.println("Libri di J. K. Rowling");
        ricercaPerAutore.forEach(System.out::println);
        System.out.println("----------------------");
        try {
            archivio.salvaSuDisco("archivio.txt");
            System.out.println("File Salvato con successo");
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio del file");
        } catch (Exception e) {
            System.err.println("Errore generico nel salvataggio del file");
        }
        System.out.println("----------------------");
        try {
            List<Prodotto> archivioCaricato = archivio.caricaDaDisco("archivio.txt");
            System.out.println("Archivio caricato da disco:");
            archivioCaricato.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Errore nel caricamento del file");
        } catch (Exception e) {
            System.err.println("Errore generico nel caricamento del file");
        }
    }
}
