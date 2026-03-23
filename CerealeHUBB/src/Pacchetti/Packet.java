package Pacchetti;

class ErroreNelPacchetto extends RuntimeException {
    public ErroreNelPacchetto(String messaggio) {
        super(messaggio);
        }
    }

public abstract class Packet implements Comparable<Packet>{
    private int ID;
    private float peso;
    private StatoPacket stato;
    private TipoPacket tipo;
    private int contatore;
    private float costo;

    //COSTRUTTORE:
    public Packet(float peso, StatoPacket stato, TipoPacket tipo, int costo) {
        this.ID = contatore;
        setPeso(peso);
        this.stato = stato;
        this.tipo = tipo;
        setCosto(costo);
        contatore++;
    }

    //SETTER:
    private void setCosto(float costo) {
        if (costo < 0)
            throw new ErroreNelPacchetto("Il costo deve essere maggiore di 0");
        if (peso > 50)
            costo += 10;
        if (tipo == TipoPacket.PREMIUM)
            costo -= 5;
        this.costo = costo;
    }

    private void setPeso(float peso){
        if (peso < 0)
            throw new ErroreNelPacchetto("Il peso deve essere maggiore di 0");
        this.peso = peso;
    }


    //GETTER:
    public int getID() {
        return ID;
    }

    public float getPeso() {
        return peso;
    }

    public StatoPacket getStato() {
        return stato;
    }

    public TipoPacket getTipo() {
        return tipo;
    }

    public void cambiaStato(StatoPacket stato){
        this.stato = stato;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override

    //PRIORITA' DEI PACCHI
    public int compareTo(Packet altro) {
        return Integer.compare(getValore(this.tipo), getValore(altro.tipo));
    }

    private int getValore(TipoPacket tipo) {
        if (tipo == TipoPacket.PREMIUM) return 0;
        else return 1;
    }
}
