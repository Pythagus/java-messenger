package Messenger.Network.Tasks.Envoyers.Concerns;

import Messenger.Network.NetworkInterface;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;
import Messenger.Network.Models.Broadcast.BroadcastResponsePacket;

/**
 * @author Damien MOLINA
 */
public class BroadcastResponseEnvoyer extends BaseEnvoyer {

    /**
     * Packet to send.
     */
    private final BroadcastResponsePacket packet ;

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance
     * @param packet : packet to send.
     */
    public BroadcastResponseEnvoyer(Envoyer envoyer, BroadcastResponsePacket packet) {
        super(envoyer, packet.getUser()) ;

        this.packet = packet ;
    }

    /**
     * Make the sending.
     */
    protected void send() {
        this.sendPacket(
            this.packet, this.packet.getTarget(), NetworkInterface.broadcastListeningPort
        ) ;
    }

}
