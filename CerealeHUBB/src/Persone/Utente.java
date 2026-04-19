package Persone;

import Pacchetti.Packet;
import Pacchetti.PacketPremium;
import Pacchetti.PacketStandard;
import Pacchetti.StatoPacket;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Utente extends Persona {

    private String via;
    private String civico;
    private PriorityQueue<Packet> lista;
    // Dichiarazione dello scanner come campo della classe
    private Scanner scanner;

    public Utente(String nome, String cognome, String via, String civico) {
        super(nome, cognome);
        this.via = via;
        this.civico = civico;
        this.lista = new PriorityQueue<>();
        // Inizializzazione dello scanner
        this.scanner = new Scanner(System.in);
    }

    // --- METODI DI INTERAZIONE ---

    /**
     * Aggiunge un pacchetto chiedendo l'input direttamente tramite lo scanner di classe.
     */
    public void aggiungiPacchetto() {
        try {
            System.out.println("\n--- NUOVA SPEDIZIONE ---");
            Packet p;
            System.out.print("Peso: ");
            float peso = scanner.nextFloat(); scanner.nextLine(); // Uso dello scanner interno

            System.out.print("Costo: ");
            int costo = scanner.nextInt(); scanner.nextLine();

            System.out.print("Tipo (1-Standard, 2-Premium): ");
            int tipo = scanner.nextInt(); scanner.nextLine();

            System.out.println("Fragile: True - False");
            boolean fragile = scanner.nextBoolean(); scanner.nextLine();
            if (tipo == 2) {
                 p = new PacketPremium(peso, StatoPacket.IN_MAGAZZINO, costo, fragile);
            } else {
                 p = new PacketStandard(peso, StatoPacket.IN_MAGAZZINO, costo, fragile);
            }

            this.lista.add(p);
            System.out.println("Pacco aggiunto alla tua lista!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
            scanner.nextLine(); // Pulisce il buffer in caso di errore
        }
    }

    public void visualizzaPacchetti() {
        System.out.println("\n--- STATO DEI TUOI PACCHI ---");
        if (lista.isEmpty()) {
            System.out.println("Nessun pacco in attesa.");
            return;
        }

        PriorityQueue<Packet> copia = new PriorityQueue<>(this.lista);
        while (!copia.isEmpty()) {
            System.out.println("- " + copia.poll());
        }
    }


    // --- METODI COMPATIBILI CON GESTIONEMAGAZZINO ---




    public PriorityQueue<Packet> getLista() {
        return lista;
    }

    public int quantiPacchetti() {
        return lista.size();
    }
}