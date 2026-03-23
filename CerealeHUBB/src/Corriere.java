import Pacchetti.Packet;

import java.lang.reflect.Array;
import java.util.PriorityQueue;
import java.util.Random;

public class Corriere {
    private String nome;
    private String cognome;
    private String matricola;
    private static int contatore = 0;
    private PriorityQueue<Packet> pacchiAssegnati;

    //COSTRUTTORE:

    public Corriere(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
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

            int numero = (contatore % 99) + 1;       // numero da 1 a 99
            int indiceLettere = contatore / 99;      // conta quante volte cambiano le lettere

            int primaLettera = indiceLettere / 26;   // lettera più significativa
            int secondaLettera = indiceLettere % 26; // lettera meno significativa

            // se superiamo le 26*26 matricole, ricominciamo da AA
            primaLettera = primaLettera % 26;

            // numero con due cifre: 01, 02, 10, 11, ...
            String numeroFormattato = String.format("%02d", numero);

            this.matricola = "" + alfabeto[primaLettera] + alfabeto[secondaLettera] + numeroFormattato;
    }


    }
