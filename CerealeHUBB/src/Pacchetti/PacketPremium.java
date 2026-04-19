package Pacchetti;

public class PacketPremium extends Packet{
    public PacketPremium(float peso, StatoPacket stato,  int costo, boolean fragile) {
        super(peso, stato, TipoPacket.PREMIUM, costo, fragile);
    }
}

