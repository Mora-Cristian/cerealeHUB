import Pacchetti.Packet;
import Persone.Corriere;
import Persone.Utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GestioneMagazzino {

    PriorityQueue<Packet> pacchiDaConsegnare;
    ArrayList<Utente> utenti;
    ArrayList<Corriere> corrieri;
    ArrayList<Packet> storico;
    Map<String, ArrayList<Packet>> pacchiPerCorriere;

    public GestioneMagazzino() {
        pacchiDaConsegnare = new PriorityQueue<>();
        corrieri = new ArrayList<>();
        utenti = new ArrayList<>();
        storico = new ArrayList<>();
        pacchiPerCorriere = new HashMap<>();
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

        while (!pacchiDaConsegnare.isEmpty()) {

            Corriere corriere = corrieri.get(i % corrieri.size());
            Packet p = pacchiDaConsegnare.poll();

            corriere.assegnaPacco(p);

            String matricola = corriere.getMatricola();

            if (pacchiPerCorriere.containsKey(matricola)) {
                pacchiPerCorriere.get(matricola).add(p);
            } else {
                ArrayList<Packet> lista = new ArrayList<>();
                lista.add(p);
                pacchiPerCorriere.put(matricola, lista);
            }

            i++;
        }
    }
}