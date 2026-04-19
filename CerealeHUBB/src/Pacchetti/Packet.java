package Pacchetti;

public abstract class Packet implements Comparable<Packet> {

    private static int contatoreGlobale = 0;

    private int ID;
    private float peso;
    private StatoPacket stato;
    private TipoPacket tipo;
    private float costo;
    private boolean fragile;

    // Costruttore
    public Packet(float peso, StatoPacket stato, TipoPacket tipo, float costo,boolean fragile) {
        this.ID = contatoreGlobale++;
        setPeso(peso);
        setCosto(costo, tipo, peso);
        this.stato = stato;
        this.tipo = tipo;
        this.fragile = fragile;
    }

    //costruttore con fragile false
    public Packet(float peso, StatoPacket stato, TipoPacket tipo, float costo) {
        this.ID = contatoreGlobale++;
        setPeso(peso);
        setCosto(costo, tipo, peso);
        this.stato = stato;
        this.tipo = tipo;
        this.fragile = false;
    }


    // Setter
    private void setPeso(float peso) {
        if (peso < 0) throw new ErroreNelPacchetto("Il peso deve essere maggiore di 0");
        this.peso = peso;
    }

    private void setCosto(float costo, TipoPacket tipo, float peso) {
        if (costo < 0) throw new ErroreNelPacchetto("Il costo deve essere maggiore di 0");
        if (peso > 50) costo += 10;
        if (tipo == TipoPacket.PREMIUM) costo -= 5;
        this.costo = costo;
    }

    // Getter
    public int getID() { return ID; }
    public float getPeso() { return peso; }
    public StatoPacket getStato() { return stato; }
    public TipoPacket getTipo() { return tipo; }
    public float getCosto() { return costo; }
    public boolean isFragile() {return fragile;}

    // Cambia stato
    public void cambiaStato(StatoPacket stato) { this.stato = stato; }

    @Override
    public String toString() {
        return "Packet{" +
                "ID=" + ID +
                ", peso=" + peso +
                ", tipo=" + tipo +
                ", costo=" + costo +
                ", stato=" + stato +
                ", è fragile "+ fragile +
                '}';
    }
    private String isFragile(){
        if (this.fragile)
            return  "si";
        return "no";
    }
    // Priorità per PriorityQueue
    @Override
    public int compareTo(Packet altro) {
        return Integer.compare(getValore(this.tipo), getValore(altro.tipo));
    }

    private int getValore(TipoPacket tipo) {
        return tipo == TipoPacket.PREMIUM ? 0 : 1;
    }
}