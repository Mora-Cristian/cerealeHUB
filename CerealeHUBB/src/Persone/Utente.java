package Persone;

import java.util.PriorityQueue;
import Pacchetti.Packet;
import java.util.Scanner;

public class Utente extends Persona {

    private PriorityQueue<Packet> lista;
    private String via;
    private String numeroCivico;
    private String psw;

    public Utente(String nome, String cognome, String via, String numeroCivico) {
        super(nome, cognome);
        this.lista = new PriorityQueue<>();
        this.via = via;
        setNumeroCivico(numeroCivico);
        chiediPsw();
    }

    //controllo sul numero civico
    public void setNumeroCivico(String numeroCivico) {
        if(!numeroCivico.matches(".*\\d.*")){
            throw new ErroreNellaPersona("Utente: deve contenere almeno un numero");
        }
        this.numeroCivico = numeroCivico;
    }
    public void setPsw(String psw){
        if(psw.length()< 8){
            throw new ErrorePsw("la password è troppo corta");
        } else if (!psw.matches(".*[A-Z].*")) {
            throw new ErrorePsw("la password deve avere almeno un carattere speciale");
        }else if(!psw.matches(".*[^a-zA-Z0-9].*")){
            throw new ErrorePsw("la password deve avere almeno un caratettere speciale");
        }
        this.psw= psw;
    }


    public void chiediPsw() {
        Scanner sc = new Scanner(System.in);
        int tentativi = 0;
        boolean valida = false;
        while (tentativi < 3 && !valida) {
            System.out.print("inserisci password: ");
            String psw = sc.nextLine();
            try {
                setPsw(psw);
                valida = true;
            } catch (ErrorePsw e) {
                tentativi++;
                System.out.println(e.getMessage());
                System.out.println("tentativi rimasti: " + (3 - tentativi));
            }}
        if (valida) {
            System.out.println("password accettata!");
        } else {
            System.out.println("hai esaurito i tentativi.");
        }}


    // Aggiunge un pacco
    public void aggiungiPacchetto(Packet p) {
        lista.add(p);
    }

    // Estrae il pacco con priorità più alta
    public Packet estraiPacchetto() {
        if (isVuota()) {
            throw new ErroreNellaPersona("Utente: Non si può estrarre: nessun pacchetto presente");
        }
        return lista.poll();
    }

    // Visualizza senza rimuovere
    public Packet visualizzaPacchetto() {
        if (isVuota()) {
            throw new ErroreNellaPersona("Utente: Non si può visualizzare: nessun pacchetto presente");
        }
        return lista.peek();
    }
    //Visualizza tutti i pacchetti
    public void visualizzaPacchetti(){
        if (isVuota()) {
            throw new ErroreNellaPersona("Utente: Non si può visualizzare: nessun pacchetto presente");
        }
        for (Packet p : lista) {
            System.out.println(p);
        }
    }

    // Controlla se è vuota
    public boolean isVuota() {
        return lista.isEmpty();
    }

    // Numero pacchi
    public int quantiPacchetti() {
        return lista.size();
    }
    
}