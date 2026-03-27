package Persone;

import Pacchetti.Packet;
import java.util.PriorityQueue;

public class Corriere extends Persona {

    private String matricola;
    private static int contatore = 0;
    private int capacitaMax;
    private PriorityQueue<Packet> pacchiAssegnati;

    // Costruttore
    public Corriere(String nome, String cognome,int capacitaMax) {
        super(nome, cognome);
        pacchiAssegnati = new PriorityQueue<>();
        generaMatricola();
        setCapacitaMax(capacitaMax);
        contatore++;
    }

    //costruttore con capacita a 5
    public Corriere(String nome, String cognome) {
        super(nome, cognome);
        pacchiAssegnati = new PriorityQueue<>();
        generaMatricola();
        this.capacitaMax=5;
        contatore++;
    }

    //set capacita (un corriere puo consegnare massimo 15 pacchetti al giorno)
    public void setCapacitaMax(int capacitaMax) {
        if(capacitaMax>15){
            throw new ErroreNellaPersona("Corriere: la capacita giornaliera massima è di 15");
        }
        this.capacitaMax = capacitaMax;
    }

    // Getter matricola
    public String getMatricola() {
        return matricola;
    }

    // Getter contatore
    public static int getContatore() {
        return contatore;
    }

    // Genera matricola unica
    private void generaMatricola() {
        char[] alfabeto = {
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z'
        };

        int numero = (contatore % 99) + 1;
        int indiceLettere = contatore / 99;

        int primaLettera = indiceLettere / 26;
        int secondaLettera = indiceLettere % 26;

        primaLettera = primaLettera % 26;
        String numeroFormattato = String.format("%02d", numero);

        this.matricola = "" + alfabeto[primaLettera] + alfabeto[secondaLettera] + numeroFormattato;
    }

    // Assegna un pacco
    public void assegnaPacco(Packet p) {
        if(pacchiAssegnati.size() > capacitaMax){
            throw new ErroreNellaPersona("Corriere: troppi pacchi");
        }
        pacchiAssegnati.add(p);
    }

    // Visualizza i pacchi assegnati (opzionale)
    public PriorityQueue<Packet> getPacchiAssegnati() {
        return pacchiAssegnati;
    }
}