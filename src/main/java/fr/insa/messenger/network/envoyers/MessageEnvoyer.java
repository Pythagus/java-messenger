package fr.insa.messenger.network.envoyers;

import fr.insa.messenger.system.Env;
import fr.insa.messenger.network.Envoyer;
import fr.insa.messenger.models.messages.Message;
import fr.insa.messenger.network.NetworkInterface;
import fr.insa.messenger.network.models.MessagePacket;

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

        this.sendPacket(packet, this.user, NetworkInterface.RECEIVING_PORT) ;
    }

}
