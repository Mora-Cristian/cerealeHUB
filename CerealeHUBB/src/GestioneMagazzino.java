import Persone.*;
import Pacchetti.Packet;
import Pacchetti.StatoPacket;
import java.util.*;

public class GestioneMagazzino {

    private ArrayList<Utente> utenti;
    private ArrayList<Corriere> corrieri;
    private PriorityQueue<Packet> pacchiDaConsegnare; // Nome originale
    private HashMap<String, ArrayList<Packet>> pacchiPerCorriere; // Nome originale

    public GestioneMagazzino() {
        this.utenti = new ArrayList<>();
        this.corrieri = new ArrayList<>();
        this.pacchiDaConsegnare = new PriorityQueue<>();
        this.pacchiPerCorriere = new HashMap<>();
    }

    // --- METODI PER UTENTI ---

    public void aggiungiUtente(Utente u) {
        utenti.add(u);
    }

    public void visualizzaTuttiUtenti() {
        System.out.println("\n--- ELENCO UTENTI ---");
        for (Utente u : utenti) {
            System.out.println(u.getNome() + " " + u.getCognome() + " | Pacchi pronti: " + u.quantiPacchetti());
        }
    }

    public void raccogliPacchiDaUtenti() {
        for (Utente u : utenti) {
            // Copia per non svuotare la lista originale dell'utente
            PriorityQueue<Packet> listaUtente = new PriorityQueue<>(u.getLista());
            while (!listaUtente.isEmpty()) {
                Packet p = listaUtente.poll();
                // Importante: prendiamo solo i pacchi che non sono già stati raccolti
                if (p.getStato() == StatoPacket.IN_MAGAZZINO) {
                    p.cambiaStato(StatoPacket.IN_CONSEGNA);
                    pacchiDaConsegnare.add(p);
                }
            }
        }
        System.out.println("Raccolta completata. Pacchi in magazzino: " + pacchiDaConsegnare.size());
    }

    // --- METODI PER CORRIERI ---

    public void aggiungiCorriere(Corriere c) {
        corrieri.add(c);
    }

    public void assegnaPacchi() {
        if (pacchiDaConsegnare.isEmpty() || corrieri.isEmpty()) return;

        int i = 0;
        int tentativiFalliti = 0;

        while (!pacchiDaConsegnare.isEmpty() && tentativiFalliti < corrieri.size()) {
            Corriere corriere = corrieri.get(i % corrieri.size());
            Packet p = pacchiDaConsegnare.peek();

            try {
                corriere.assegnaPacco(p);
                pacchiDaConsegnare.poll(); // Rimosso solo se accettato
                p.cambiaStato(StatoPacket.IN_CONSEGNA);

                // Aggiornamento HashMap (Storico)
                pacchiPerCorriere.computeIfAbsent(corriere.getMatricola(), k -> new ArrayList<>()).add(p);

                tentativiFalliti = 0;
            } catch (RuntimeException e) {
                tentativiFalliti++;
            }
            i++;
        }
    }

    public void visualizzaCorrieriESpedizioni() {
        System.out.println("\n--- STATO SPEDIZIONI (HashMap) ---");
        for (String mat : pacchiPerCorriere.keySet()) {
            System.out.println("\nCorriere: " + mat);
            for (Packet p : pacchiPerCorriere.get(mat)) {
                System.out.println("  - " + p.toString() + " [" + p.getStato() + "]");
            }
        }
    }

    // --- METODI DI RICERCA (PER IL MAIN) ---

    public Utente cercaUtente(String n, String c) {
        for (Utente u : utenti) {
            if (u.getNome().equalsIgnoreCase(n) && u.getCognome().equalsIgnoreCase(c)) return u;
        }
        return null;
    }

    public Corriere cercaCorriere(String m) {
        for (Corriere c : corrieri) {
            if (c.getMatricola().equalsIgnoreCase(m)) return c;
        }
        return null;
    }
}