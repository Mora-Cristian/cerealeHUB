package Persone;

import Pacchetti.Packet;

import java.util.PriorityQueue;

public class Corriere extends Persona {

    private String matricola;
    private static int contatore = 0;
    private PriorityQueue<Packet> pacchiAssegnati;

    //COSTRUTTORE:

    public Corriere(String nome, String cognome) {
        super(nome, cognome);
        generaMatricola();
        this.pacchiAssegnati = new PriorityQueue<>();
        contatore++;
    }

    public void generaMatricola() {
            char[] alfabeto = {
                    'A','B','C','D','E','F','G','H','I','J',
                    'K','L','M','N','O','P','Q','R','S','T',
                    'U','V','W','X','Y','Z'
            };

            int numero = (contatore % 99) + 1;
            Integer numeroInteger = (Integer) numero;// numero da 1 a 99
            int indiceLettere = contatore / 99;      // conta quante volte cambiano le lettere

            int primaLettera = indiceLettere / 26;   // lettera più significativa
            int secondaLettera = indiceLettere % 26; // lettera meno significativa

            // se superiamo le 26*26 matricole, ricominciamo da AA
            primaLettera = primaLettera % 26;

            // numero con due cifre: 01, 02, 10, 11, ...
            String numeroFormattato = String.format("%02d", numeroInteger);

            this.matricola = "" + alfabeto[primaLettera] + alfabeto[secondaLettera] + numeroFormattato;
    }


    }
