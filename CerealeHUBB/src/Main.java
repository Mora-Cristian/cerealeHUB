import Persone.*;
import Pacchetti.*;
import java.util.Scanner;

public class Main {
    // Scanner statico accessibile da tutti i metodi della classe
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GestioneMagazzino gm = new GestioneMagazzino();

        // --- DATI DI TEST ---
        gm.aggiungiUtente(new Utente("Mario", "Rossi", "Via Roma", "10"));
        gm.aggiungiCorriere(new Corriere("Marco", "Neri"));

        int sceltaPrincipale = -1;

        while (sceltaPrincipale != 0) {
            System.out.println("\n********** SISTEMA LOGISTICO CENTRALE **********");
            System.out.println("1. AREA UTENTE");
            System.out.println("2. AREA CORRIERE");
            System.out.println("3. AREA MAGAZZINO (ADMIN)");
            System.out.println("0. ESCI");
            System.out.print("Seleziona area: ");

            sceltaPrincipale = scanner.nextInt();
            scanner.nextLine(); // Pulisce il buffer dopo l'invio del numero

            switch (sceltaPrincipale) {
                case 1: menuAreaUtente(gm); break;
                case 2: menuAreaCorriere(gm); break;
                case 3: menuAreaMagazzino(gm); break;
                case 0: System.out.println("Uscita in corso..."); break;
                default: System.out.println("Scelta errata.");
            }
        }
    }

    // ==========================================
    //   AREA UTENTE
    // ==========================================
    private static void menuAreaUtente(GestioneMagazzino gm) {
        System.out.print("Nome: "); String n = scanner.nextLine();
        System.out.print("Cognome: "); String c = scanner.nextLine();
        Utente u = gm.cercaUtente(n, c);

        if (u == null) {
            System.out.println("Utente non trovato!");
            return;
        }

        int scelta = -1;
        while (scelta != 0) {
            System.out.println("\n--- MENU UTENTE: " + u.getNome() + " ---");
            System.out.println("1. Crea pacco");
            System.out.println("2. I miei pacchi");
            System.out.println("0. Torna");
            scelta = scanner.nextInt();
            scanner.nextLine();

            if (scelta == 1) u.aggiungiPacchetto();
            else if (scelta == 2) u.visualizzaPacchetti();
        }
    }

    // ==========================================
    //   AREA CORRIERE
    // ==========================================
    private static void menuAreaCorriere(GestioneMagazzino gm) {
        System.out.print("Matricola: ");
        String mat = scanner.nextLine();
        Corriere c = gm.cercaCorriere(mat);

        if (c == null) {
            System.out.println("Matricola non valida.");
            return;
        }

        int scelta = -1;
        while (scelta != 0) {
            System.out.println("\n--- MENU CORRIERE: " + c.getMatricola() + " ---");
            System.out.println("1. Carico attuale (Priority)");
            System.out.println("2. Consegna prossimo");
            System.out.println("0. Torna");
            scelta = scanner.nextInt();
            scanner.nextLine();

            if (scelta == 1) c.vediStatoMezzo();
            else if (scelta == 2) c.consegnaPacco();
        }
    }

    // ==========================================
    //   AREA MAGAZZINO (ADMIN)
    // ==========================================
    private static void menuAreaMagazzino(GestioneMagazzino gm) {
        int scelta = -1;
        while (scelta != 0) {
            System.out.println("\n--- PANNELLO AMMINISTRATORE ---");
            System.out.println("1. Visualizza tutti gli Utenti");
            System.out.println("2. Visualizza tutti i Corrieri");
            System.out.println("3. CREA NUOVO UTENTE");   // Nuova opzione
            System.out.println("4. CREA NUOVO CORRIERE"); // Nuova opzione
            System.out.println("5. RACCOGLI pacchi dagli utenti");
            System.out.println("6. ASSEGNA pacchi ai corrieri");
            System.out.println("7. Visualizza Stato Generale (HashMap)");
            System.out.println("0. Torna");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine(); // Pulisce il buffer

            switch (scelta) {
                case 1:
                    gm.visualizzaTuttiUtenti();
                    break;
                case 2:
                    gm.visualizzaCorrieriESpedizioni();
                    break;
                case 3: // CREAZIONE UTENTE
                    System.out.print("Nome: "); String nomeU = scanner.nextLine();
                    System.out.print("Cognome: "); String cognU = scanner.nextLine();
                    System.out.print("Indirizzo: "); String indU = scanner.nextLine();
                    System.out.print("Civico: "); String civU = scanner.nextLine();

                    Utente nuovoUtente = new Utente(nomeU, cognU, indU, civU);
                    gm.aggiungiUtente(nuovoUtente);
                    System.out.println("Utente creato con successo!");
                    break;

                case 4: // CREAZIONE CORRIERE
                    System.out.print("Nome: "); String nomeC = scanner.nextLine();
                    System.out.print("Cognome: "); String cognC = scanner.nextLine();

                    Corriere nuovoCorriere = new Corriere(nomeC, cognC);
                    gm.aggiungiCorriere(nuovoCorriere);
                    System.out.println("Corriere creato con successo!");
                    break;

                case 5: gm.raccogliPacchiDaUtenti(); break;
                case 6: gm.assegnaPacchi(); break;
                case 7: gm.visualizzaCorrieriESpedizioni(); break;
            }
        }
    }
}