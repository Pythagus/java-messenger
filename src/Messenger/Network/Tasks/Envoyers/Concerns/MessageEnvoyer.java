package Messenger.Network.Tasks.Envoyers.Concerns;

import java.net.Socket;
import Messenger.Foundation.Env;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MessagePacket;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;
import Messenger.Network.Models.Datagram.OutputSocketStream;

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
            Socket socket = new Socket(
                this.user.getAddress(), NetworkInterface.receivingPort
            ) ;

            OutputSocketStream exchanger = new OutputSocketStream(socket) ;
            exchanger.send(packet) ;

            exchanger.close() ;
            socket.close() ;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
