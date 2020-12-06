package Messenger.Network.Tasks.Envoyers.Concerns;

import Messenger.Foundation.System.Env;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MessagePacket;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;

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

        this.sendPacket(packet, this.user, NetworkInterface.receivingPort) ;
    }

}
