package Pacchetti;

public class PacketPremium extends Packet{
    public PacketPremium(float peso, StatoPacket stato, int costo) {
        super(peso, stato, TipoPacket.PREMIUM, costo);
    }


}

