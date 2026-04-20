package Persone;

import java.util.InputMismatchException;

public class ErroreNellaPersona extends RuntimeException {
    public ErroreNellaPersona(String message) {
        super(message);
    }
}

