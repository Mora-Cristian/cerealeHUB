package Persone;

import java.util.PriorityQueue;
import Pacchetti.Packet;

public class Utente extends Persona {
    private PriorityQueue<Packet> lista;
    private String via;
    private String numeroCivico;

    public Utente(String nome, String cognome,String via,String numeroCivico) {
        super(nome, cognome);
        this.lista = new PriorityQueue<>();
        this.via  = via;
        this.numeroCivico= numeroCivico;
    }

    public void aggiungiPacchetto(Packet p){lista.add(p);}
    public Packet estraiPacchetto(){
        if(isVuota()){
            throw new ErroreNellaPersona("non si puo estrarre un pacchetto se non ci sono");
        }
        return lista.poll();
    }
    public Packet visualizzaPacchetto(){
        if(isVuota()){
            throw new ErroreNellaPersona("non si puo visualizzare un pacchetto se non ci sono");
        }
        return lista.peek();}
    public boolean isVuota(){return lista.isEmpty();}
    public int quantiPacchetti(){return lista.size();}
}
