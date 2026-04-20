package Persone;

import Pacchetti.Packet;
import Pacchetti.StatoPacket;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Corriere extends Persona {

    private String matricola;
    private static int contatore = 0;
    private int capacitaMax;
    private PriorityQueue<Packet> pacchiAssegnati;

    public Corriere(String nome, String cognome) {
        super(nome, cognome);
        this.pacchiAssegnati = new PriorityQueue<>();
        this.capacitaMax = 15;
        generaMatricola();
        contatore++;
        System.out.println(matricola);
    }

    // --- LOGICA DI GESTIONE INTERNA ---


    public void visualizzaMioCarico() {
        System.out.println("\n--- CARICO CORRIERE: " + this.matricola + " ---");
        if (pacchiAssegnati.isEmpty()) {
            System.out.println("Il furgone è vuoto.");
            return;
        }

        // Creiamo una copia per la visualizzazione
        PriorityQueue<Packet> copia = new PriorityQueue<>(this.pacchiAssegnati);
        int i = 1;
        while (!copia.isEmpty()) {
            System.out.println(i + ") " + copia.poll());
            i++;
        }
    }


    /**
     * Mostra quanto spazio è rimasto nel mezzo.
     */
    public void vediStatoMezzo() {
        int occupati = pacchiAssegnati.size();
        System.out.println("\n--- STATO VEICOLO ---");
        System.out.println("Pacchi a bordo: " + occupati + " / " + capacitaMax);
        System.out.println("Spazio libero: " + (capacitaMax - occupati));
    }

    // --- METODI ORIGINALI ---

    public void assegnaPacco(Packet p) {
        if (pacchiAssegnati.size() >= capacitaMax) {
            throw new ErroreNellaPersona("Capacità massima raggiunta per il corriere " + matricola);
        }
        pacchiAssegnati.add(p);
    }

    public String getMatricola() {
        return matricola;
    }

    public void consegnaPacco() {
        // 1. Controllo se il corriere ha effettivamente dei pacchi a bordo
        if (pacchiAssegnati.isEmpty()) {
            System.out.println("Il corriere " + matricola + " non ha pacchi da consegnare.");
            return;
        }

        Packet p = pacchiAssegnati.poll();

        // 3. Cambio di stato
        p.cambiaStato(StatoPacket.CONSEGNATO);

        System.out.println("Pacco consegnato con successo dal corriere " + matricola + "!");
        System.out.println("Dettagli pacco: " + p.toString());
        System.out.println("Spazio rimanente nel furgone: " + (15 - pacchiAssegnati.size()));
    }

    public PriorityQueue<Packet> getPacchiAssegnati() {
        return pacchiAssegnati;
    }

    private void generaMatricola() {
        char[] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int numero = (contatore % 99) + 1;
        int indiceLettere = contatore / 99;
        int primaLettera = (indiceLettere / 26) % 26;
        int secondaLettera = indiceLettere % 26;
        String numeroFormattato = String.format("%02d", numero);
        this.matricola = "" + alfabeto[primaLettera] + alfabeto[secondaLettera] + numeroFormattato;
    }

    @Override
    public String toString() {
        return "nome: "+getNome()+", cognome: "+getCognome()+", matricola: "+matricola;
    }
}