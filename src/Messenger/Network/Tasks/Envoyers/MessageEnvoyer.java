package Messenger.Network.Tasks.Envoyers;

import java.net.Socket;
import Messenger.Foundation.Env;
import Messenger.Foundation.Models.User;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MessagePacket;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Network.Models.Datagram.OutputSocketStream;
import Messenger.Network.Tasks.Envoyers.Concerns.BaseEnvoyer;

/**
 * @author Damien MOLINA
 */
public class MessageEnvoyer extends BaseEnvoyer {

    /**
     * Data to send.
     */
    private final MessageData data ;

    /**
     * Make a new message envoyer.
     *
     * @param data : data to send.
     * @param user : user to send the data to.
     */
    public MessageEnvoyer(NetworkEnvoyer envoyer, MessageData data, User user) {
        super(envoyer, user) ;

        this.data = data ;
    }

    /**
     * Make the sending.
     */
    @Override
    public void send() {
        MessagePacket packet = new MessagePacket(
            Env.getUser(), this.user
        ) ;
        packet.setData(this.data) ;

        try {
            Socket socket = new Socket(
                this.user.getAddress(), NetworkInterface.sendingPort
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
