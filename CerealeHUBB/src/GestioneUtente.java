import Persone.*;
import Pacchetti.*;

import java.util.Scanner;

public class GestioneUtente {
    private Utente utente;
    Scanner scanner = new Scanner(System.in);
    // Il costruttore riceve l'utente che ha appena fatto il login
    public GestioneUtente(Utente utente) {
        this.utente = utente;
    }

    // Metodo per visualizzare solo i pacchi dell'utente corrente
    public void visualizzaMieiPacchi() {
        System.out.println("\n--- I tuoi pacchetti ---");
        try {
            utente.visualizzaPacchetti();
        } catch (RuntimeException e) {
            // Gestisce il caso in cui la lista sia vuota (lancia ErroreNellaPersona)
            System.out.println(e.getMessage());
        }
    }

    public void aggiungiPacco() {
        try {
            System.out.println("Inserisci il peso (kg):");
            float peso = scanner.nextFloat();
            scanner.nextLine(); // Pulizia buffer dopo nextFloat

            System.out.println("Inserisci il costo base:");
            int costo = scanner.nextInt();
            scanner.nextLine(); // Pulizia buffer dopo nextInt

            System.out.println("Scegli il tipo: 1) Standard 2) Premium");
            int tipo = scanner.nextInt();
            scanner.nextLine(); // Pulizia buffer dopo nextInt

            Packet p;
            if (tipo == 2) {
                p = new PacketPremium(peso, StatoPacket.IN_MAGAZZINO, costo);
            } else {
                p = new PacketStandard(peso, StatoPacket.IN_MAGAZZINO, costo);
            }

            utente.aggiungiPacchetto(p);
            System.out.println("Pacco aggiunto con successo alla tua lista personale.");

        } catch (Exception e) {
            System.out.println("Errore durante la creazione del pacco: " + e.getMessage());
        }
    }

    // Metodo per cambiare la password dell'utente loggato
    public void modificaPassword(Scanner sc) {
        System.out.println("Inserisci la nuova password:");
        String nuovaPsw = sc.nextLine();
        try {
            // Sfrutta il metodo setPsw della classe Utente che ha già i controlli regex
            utente.setPsw(nuovaPsw);
            System.out.println("Password aggiornata con successo.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public void vediProfilo() {
        System.out.println("\n--- Dettagli Profilo ---");
        System.out.println(utente.toString()); // Usa il toString di Persona/Utente
        System.out.println("Numero di pacchi pronti per il ritiro: " + utente.quantiPacchetti());
    }
}