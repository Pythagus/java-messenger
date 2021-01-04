package fr.insa.messenger.client.network.envoyers;

import java.io.IOException;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.network.Envoyer;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.network.models.MessagePacket;

/**
 * @author Damien MOLINA
 */
public class MessageEnvoyer extends BaseEnvoyer {

    /**
     * Data to send.
     */
    private final Message message ;

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance
     * @param message : message to send.
     */
    public MessageEnvoyer(Envoyer envoyer, Message message) {
        super(envoyer, message.getTarget()) ;

        this.message = message ;
    }

    /**
     * Make the sending.
     */
    @Override
    protected void send() {
        MessagePacket packet = new MessagePacket(
            Env.getUser(), this.user
        ) ;
        packet.setData(this.message) ;

        try {
            this.sendPacket(packet, NetworkInterface.RECEIVING_PORT, true) ;
        } catch (IOException e) {
            e.printStackTrace() ;
        }
    }

}
