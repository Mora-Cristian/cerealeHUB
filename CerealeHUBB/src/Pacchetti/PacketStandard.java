package Pacchetti;

public class PacketStandard extends Packet{
    public PacketStandard(float peso, StatoPacket stato,  int costo, boolean fragile) {
        super(peso, stato, TipoPacket.STANDARD, costo, fragile);
    }
}
