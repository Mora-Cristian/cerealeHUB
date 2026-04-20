package Persone;

import Pacchetti.Packet;
import Pacchetti.PacketPremium;
import Pacchetti.PacketStandard;
import Pacchetti.StatoPacket;

import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Utente extends Persona {

    private String via;
    private int civico;
    private PriorityQueue<Packet> lista;

    public Utente(String nome, String cognome, String via, int civico) {
        super(nome, cognome);
        this.via = via;
        this.civico = civico;
        this.lista = new PriorityQueue<>();
    }

    // METODI UTENTE
    private Scanner scanner = new Scanner(System.in);
    public void aggiungiPacchetto() {
        try {
            System.out.println("\n--- NUOVA SPEDIZIONE ---");
            Packet p;
            System.out.print("Peso: ");
            float peso = scanner.nextFloat(); scanner.nextLine();

            System.out.print("Costo: ");
            int costo = scanner.nextInt(); scanner.nextLine();

            System.out.print("Tipo (1-premium, qualunque valore - standard ): ");
            int tipo = scanner.nextInt(); scanner.nextLine();

            System.out.println("Fragile: True - False");
            boolean fragile = scanner.nextBoolean(); scanner.nextLine();
            if (tipo == 1) {
                 p = new PacketPremium(peso, StatoPacket.IN_MAGAZZINO, costo, fragile);
            } else {
                 p = new PacketStandard(peso, StatoPacket.IN_MAGAZZINO, costo, fragile);
            }

            this.lista.add(p);
            System.out.println("Pacco aggiunto alla tua lista!");
        } catch (InputMismatchException e) {
            System.out.println("Errore: Input errato!");
            scanner.nextLine();
        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
            scanner.nextLine();
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

    public PriorityQueue<Packet> getLista() {
        return lista;
    }

    public int quantiPacchetti() {
        return lista.size();
    }
}