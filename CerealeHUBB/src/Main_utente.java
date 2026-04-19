import Persone.*;
import Pacchetti.*;
import java.util.Scanner;

public class Main_utente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestioneMagazzino gm = new GestioneMagazzino();
        Utente utenteLoggato = null;

        // Inizializzazione dati di test (Corrieri)
        gm.aggiungiCorriere(new Corriere("Marco", "Rossi"));
        gm.aggiungiCorriere(new Corriere("Anna", "Verdi"));

        System.out.println("==== BENVENUTO NEL SISTEMA LOGISTICO ====");

        // --- FASE 1: ACCESSO / REGISTRAZIONE ---
        boolean uscire = false;
        while (utenteLoggato == null && !uscire) {
            System.out.println("1) Registrati\n2) Accedi\n3) Esci");
            int inizio = sc.nextInt(); sc.nextLine();

            switch (inizio) {
                case 1:
                    System.out.println("Nome:"); String n = sc.nextLine();
                    System.out.println("Cognome:"); String c = sc.nextLine();
                    if (!gm.controllo_utenti(n, c)) {
                        System.out.println("Via:"); String v = sc.nextLine();
                        System.out.println("Civico:"); String civ = sc.nextLine();
                        System.out.println("Password:"); String p = sc.nextLine();
                        utenteLoggato = new Utente(n, c, v, civ, p);
                        gm.registraUtente(utenteLoggato);
                        System.out.println("Registrato e loggato!");
                    } else {
                        System.out.println("Utente già esistente!");
                    }
                    break;
                case 2:
                    System.out.println("Nome:"); String ln = sc.nextLine();
                    System.out.println("Cognome:"); String lc = sc.nextLine();
                    System.out.println("Password:"); String lp = sc.nextLine();
                    utenteLoggato = gm.accedi_utenti(ln, lc, lp);
                    if (utenteLoggato == null) System.out.println("Credenziali errate.");
                    break;
                case 3: uscire = true; break;
            }
        }

        if (uscire) return;

        // --- FASE 2: MENU PRINCIPALE (Utilizzo di tutti i metodi) ---
        int scelta;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Crea Pacchetto (Standard/Premium)");
            System.out.println("2) I tuoi pacchi in attesa");
            System.out.println("3) Invia pacchi al Magazzino (raccogliPacchiDaUtenti)");
            System.out.println("4) Visualizza Magazzino (visualizzaPacchi)");
            System.out.println("5) Assegna ai Corrieri (assegnaPacchi)");
            System.out.println("6) Logout");

            scelta = sc.nextInt(); sc.nextLine();

            switch (scelta) {
                case 1:
                    try {
                        System.out.println("Peso:"); float peso = sc.nextFloat(); sc.nextLine();
                        System.out.println("Costo:"); int costo = sc.nextInt(); sc.nextLine();
                        System.out.println("Premium? (1: Si, 2: No)"); int tipo = sc.nextInt(); sc.nextLine();

                        Packet p = (tipo == 1) ?
                                new PacketPremium(peso, StatoPacket.IN_MAGAZZINO, costo) :
                                new PacketStandard(peso, StatoPacket.IN_MAGAZZINO, costo);

                        utenteLoggato.aggiungiPacchetto();
                        System.out.println("Pacco creato.");
                    } catch (Exception e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 2:
                    utenteLoggato.visualizzaPacchetti();
                    break;

                case 3:
                    // Utilizza gm.raccogliPacchiDaUtenti()
                    gm.raccogliPacchiDaUtenti();
                    break;

                case 4:
                    // Utilizza gm.visualizzaPacchi()
                    System.out.println("Pacchi pronti per la consegna (ordinati per priorità):");
                    gm.visualizzaPacchi();
                    break;

                case 5:
                    // Utilizza gm.assegnaPacchi()
                    gm.assegnaPacchi();
                    break;
            }
        } while (scelta != 6);
    }

}