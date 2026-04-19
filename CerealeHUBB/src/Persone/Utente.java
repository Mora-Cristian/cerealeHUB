package Persone;

import Pacchetti.Packet;
import Pacchetti.PacketPremium;
import Pacchetti.PacketStandard;
import Pacchetti.StatoPacket;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Utente extends Persona {

    private String Psw; // Nome variabile usato in GestioneMagazzino
    private String via;
    private String civico;
    private PriorityQueue<Packet> lista; // Nome usato in GestioneMagazzino

    public Utente(String nome, String cognome, String via, String civico, String password) {
        super(nome, cognome);
        this.via = via;
        this.civico = civico;
        this.Psw = password;
        this.lista = new PriorityQueue<>();
    }

    // --- METODI DI INTERAZIONE (L'utente gestisce se stesso) ---

    public void creaEInviaPacco(Scanner sc) {
        try {
            System.out.println("\n--- NUOVA SPEDIZIONE ---");
            System.out.print("Peso: ");
            float peso = sc.nextFloat(); sc.nextLine();

            System.out.print("Costo: ");
            int costo = sc.nextInt(); sc.nextLine();

            System.out.print("Tipo (1-Standard, 2-Premium): ");
            int tipo = sc.nextInt(); sc.nextLine();

            Packet p;
            if (tipo == 2) {
                p = new PacketPremium(peso, StatoPacket.IN_MAGAZZINO, costo);
            } else {
                p = new PacketStandard(peso, StatoPacket.IN_MAGAZZINO, costo);
            }

            this.lista.add(p); // Aggiunge alla coda che il magazzino poi svuoterà
            System.out.println("Pacco aggiunto alla tua lista!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
            sc.nextLine();
        }
    }

    public void visualizzaMieiPacchi() {
        System.out.println("\n--- STATO DEI TUOI PACCHI ---");
        if (lista.isEmpty()) {
            System.out.println("Nessun pacco in attesa.");
            return;
        }

        // Copia per non svuotare la lista vera
        PriorityQueue<Packet> copia = new PriorityQueue<>(this.lista);
        while (!copia.isEmpty()) {
            System.out.println("- " + copia.poll());
        }
    }

    // --- METODI COMPATIBILI CON GESTIONEMAGAZZINO ---

    // Metodo fondamentale per il login in GestioneMagazzino
    public boolean controlloPsw(String psw) {
        return this.Psw.equals(psw);
    }

    // Getter per la password (usato per i controlli regex o login)
    public String getPsw() {
        return Psw;
    }

    public void setPsw(String password) {
        this.Psw = password;
    }

    // Getter per la lista (usato da gm.raccogliPacchiDaUtenti)
    public PriorityQueue<Packet> getLista() {
        return lista;
    }

    // Usato per i report nel magazzino
    public int quantiPacchetti() {
        return lista.size();
    }
}