package Pacchetti;

public class ErroreNelPacchetto extends RuntimeException {
    public ErroreNelPacchetto(String messaggio) {
        super(messaggio);
    }
}
