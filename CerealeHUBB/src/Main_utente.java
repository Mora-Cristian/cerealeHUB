import Persone.Utente;
import Pacchetti.Packet;

import java.util.Scanner;

public class Main_utente {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestioneMagazzino gm = new GestioneMagazzino();

        Utente utenteLoggato = null;

        System.out.println("==== BENVENUTO ====");
        System.out.println("1) Registrati");
        System.out.println("2) Accedi");

        int scelta = Integer.parseInt(sc.nextLine());

        switch (scelta) {

            case 1:
                System.out.println("inserisci nome:");
                String nome = sc.nextLine();

                System.out.println("inserisci cognome:");
                String cognome = sc.nextLine();

                System.out.println("inserisci via:");
                String via = sc.nextLine();

                System.out.println("inserisci numero civico:");
                String num = sc.nextLine();

                System.out.println("inserisci password:");
                String psw = sc.nextLine();

                if (!gm.controllo_utenti(nome, cognome)) {
                    utenteLoggato = new Utente(nome, cognome, via, num,psw);
                    gm.utenti.add(utenteLoggato);
                    System.out.println("registrazione completata");
                } else {
                    System.out.println("utente già esistente");
                    return;
                }
                break;

            case 2:
                System.out.println("inserisci nome:");
                nome = sc.nextLine();

                System.out.println("inserisci cognome:");
                cognome = sc.nextLine();

                System.out.println("inserisci password:");
                psw = sc.nextLine();

                utenteLoggato = gm.accedi_utenti(nome, cognome, psw);

                if (utenteLoggato == null) {
                    System.out.println("credenziali errate");
                    return;
                } else {
                    System.out.println("accesso effettuato");
                }
                break;

            default:
                System.out.println("scelta non valida");
                return;
        }

        int sceltaMenu;

        do {
            System.out.println("\n==== MENU UTENTE ====");
            System.out.println("1) Gestione pacchi");
            System.out.println("2) Info account");
            System.out.println("3) Operazioni magazzino");
            System.out.println("4) Logout");

            sceltaMenu = Integer.parseInt(sc.nextLine());

            switch (sceltaMenu) {

                case 1:
                    int sceltaPacchi;
                    do {
                        System.out.println("\n--- PACCHI ---");
                        System.out.println("1) Aggiungi pacco");
                        System.out.println("2) Visualizza pacchi");
                        System.out.println("3) Estrai pacco");
                        System.out.println("4) Numero pacchi");
                        System.out.println("5) Indietro");

                        sceltaPacchi = Integer.parseInt(sc.nextLine());

                        switch (sceltaPacchi) {

                            case 1:
                                try {
                                    //agiungi paramteri per il nuovo pacchetto
                                    Packet p = new Packet();
                                    utenteLoggato.aggiungiPacchetto(p);

                                    System.out.println("pacco aggiunto");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2:
                                try {
                                    utenteLoggato.visualizzaPacchetti();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 3:
                                try {
                                    Packet p = utenteLoggato.estraiPacchetto();
                                    System.out.println("estratto: " + p);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 4:
                                System.out.println("pacchi totali: " + utenteLoggato.quantiPacchetti());
                                break;
                        }

                    } while (sceltaPacchi != 5);
                    break;

                case 2:
                    System.out.println("\n--- INFO ACCOUNT ---");
                    System.out.println("nome: " + utenteLoggato.getNome());
                    System.out.println("cognome: " + utenteLoggato.getCognome());
                    System.out.println("pacchi: " + utenteLoggato.quantiPacchetti());
                    break;

                case 3:
                    int sceltaMagazzino;
                    do {
                        System.out.println("\n--- MAGAZZINO ---");
                        System.out.println("1) Invia pacchi al magazzino");
                        System.out.println("2) Visualizza pacchi globali");
                        System.out.println("3) Assegna pacchi ai corrieri");
                        System.out.println("4) Indietro");

                        sceltaMagazzino = Integer.parseInt(sc.nextLine());

                        switch (sceltaMagazzino) {

                            case 1:
                                gm.raccogliPacchiDaUtenti();
                                System.out.println("pacchi inviati al magazzino");
                                break;

                            case 2:
                                gm.visualizzaPacchi();
                                break;

                            case 3:
                                gm.assegnaPacchi();
                                System.out.println("pacchi assegnati");
                                break;
                        }

                    } while (sceltaMagazzino != 4);
                    break;

                case 4:
                    System.out.println("logout");
                    break;

                default:
                    System.out.println("scelta non valida");
            }

        } while (sceltaMenu != 4);

        sc.close();
    }
}