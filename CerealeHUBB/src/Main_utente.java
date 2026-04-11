import Persone.Utente;

import java.util.Scanner;

public class Main_utente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestioneMagazzino gm = new GestioneMagazzino();
        System.out.println("1)registrati o 2)accedi ");
        int scelta = sc.nextInt();
        sc.nextLine();
        switch (scelta){
            case 1:
                System.out.println("inserisci il tuo nome");
                String nome = sc.next();
                sc.nextLine();
                System.out.println("inserisci il tuo cognome");
                String cognome = sc.next();
                sc.nextLine();
                System.out.println("inserisci la tua via");
                String via = sc.next();
                sc.nextLine();
                System.out.println("inserisci il tuo numero civico");
                String num = sc.next();
                sc.nextLine();
                if(!gm.controllo_utenti(nome,cognome)){
                    Utente u = new Utente(nome,cognome,via,num);
                    gm.utenti.add(u);
                    System.out.println("utente aggiunto con successo");
                }else{System.out.println("utente gia esistente");}

        }
    }
}
