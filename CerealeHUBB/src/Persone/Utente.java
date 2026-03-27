package Persone;

import java.util.PriorityQueue;
import Pacchetti.Packet;

public class Utente extends Persona {

    private PriorityQueue<Packet> lista;
    private String via;
    private String numeroCivico;

    public Utente(String nome, String cognome, String via, String numeroCivico) {
        super(nome, cognome);
        this.lista = new PriorityQueue<>();
        this.via = via;
        setNumeroCivico(numeroCivico);
    }

    //controllo sul numero civico
    public void setNumeroCivico(String numeroCivico) {
        if(!numeroCivico.matches(".*\\d.*")){
            throw new ErroreNellaPersona("Utente: deve contenere almeno un numero");
        }
        this.numeroCivico = numeroCivico;
    }

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