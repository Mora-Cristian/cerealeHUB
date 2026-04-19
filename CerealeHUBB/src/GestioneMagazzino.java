import Pacchetti.Packet;
import Persone.Corriere;
import Persone.Utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GestioneMagazzino {

    static PriorityQueue<Packet> pacchiDaConsegnare;
    static ArrayList<Utente> utenti;
    static ArrayList<Corriere> corrieri;
    static ArrayList<Packet> storico;
    static Map<String, ArrayList<Packet>> pacchiPerCorriere;

    public GestioneMagazzino() {
        pacchiDaConsegnare = new PriorityQueue<>();
        corrieri = new ArrayList<>();
        utenti = new ArrayList<>();
        storico = new ArrayList<>();
        pacchiPerCorriere = new HashMap<>();
    }

    public boolean controllo_utenti(String nome,String cognome){
        for(Utente i:utenti){
            if(i.getNome().equalsIgnoreCase(nome)&& i.getCognome().equalsIgnoreCase(cognome)){
                return true;
            }
        }
        return false;
    }
    public Utente accedi_utenti(String nome, String cognome, String psw){
        for(Utente i : utenti){
            if(i.getNome().equalsIgnoreCase(nome) &&
                    i.getCognome().equalsIgnoreCase(cognome)){

                if(i.controlloPsw(psw)){
                    return i;
                }
            }
        }
        return null;
    }

    // Registra un utente
    public void registraUtente(Utente utente) {
        utenti.add(utente);
    }

    // Aggiunge un corriere
    public void aggiungiCorriere(Corriere corriere) {
        corrieri.add(corriere);
    }

    // Visualizza i pacchi in ordine di priorità
    public void visualizzaPacchi() {
        PriorityQueue<Packet> copia = new PriorityQueue<>(pacchiDaConsegnare);

        while (!copia.isEmpty()) {
            System.out.println(copia.poll());
        }
    }

    // Raccoglie tutti i pacchi dagli utenti
    public void raccogliPacchiDaUtenti() {
        for (Utente u : utenti) {
            while (!u.isVuota()) {
                pacchiDaConsegnare.add(u.estraiPacchetto());
            }
        }
    }

    // Assegna i pacchi ai corrieri (a rotazione)
    public void assegnaPacchi() {
        if (pacchiDaConsegnare.isEmpty()) {
            System.out.println("Nessun pacco da assegnare");
            return;
        }

        if (corrieri.isEmpty()) {
            System.out.println("Nessun corriere disponibile");
            return;
        }

        int i = 0;
        int tentativiFalliti = 0; // Per evitare loop infiniti se tutti i corrieri sono pieni

        while (!pacchiDaConsegnare.isEmpty() && tentativiFalliti < corrieri.size()) {
            Corriere corriere = corrieri.get(i % corrieri.size());

            // Usiamo peek() per guardare il pacco senza rimuoverlo dalla coda finché non siamo sicuri
            Packet p = pacchiDaConsegnare.peek();

            try {
                corriere.assegnaPacco(p); // Prova ad assegnare (può lanciare l'eccezione)
                pacchiDaConsegnare.poll(); // Se non ci sono errori, rimuoviamo il pacco dalla coda del magazzino

                // Logica dello storico
                String matricola = corriere.getMatricola();
                pacchiPerCorriere.computeIfAbsent(matricola, k -> new ArrayList<>()).add(p);

                tentativiFalliti = 0; // Reset dei tentativi se l'assegnazione va a buon fine
            } catch (RuntimeException e) {
                // Se il corriere è pieno (capacità > 15), l'eccezione viene catturata qui
                System.out.println("Salto " + corriere.getMatricola() + ": " + e.getMessage());
                tentativiFalliti++;
            }
            i++; // Passa al prossimo corriere nella lista
        }

        if (!pacchiDaConsegnare.isEmpty()) {
            System.out.println("Attenzione: sono rimasti " + pacchiDaConsegnare.size() + " pacchi. Tutti i corrieri sono pieni.");
        }
    }

}