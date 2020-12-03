package Messenger.Network.Tasks.Listeners;

import java.net.Socket;
import java.io.IOException;
import Messenger.Network.Models.MessagePacket;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Network.Tasks.Listeners.Concerns.ServerListener;

/**
 * @author Damien MOLINA
 */
public class ReceiveListener extends ServerListener<MessagePacket> {

    /**
     * Make a new listener instance.
     *
     * @param port : listening port.
     */
    public ReceiveListener(int port) throws IOException {
        super(port) ;
    }

    /**
     * Determine whether the packet should
     * be managed.
     *
     * @param packet : packet to manage.
     * @return True if the packet should be managed,
     * False otherwise.
     */
    protected boolean shouldManagePacket(MessagePacket packet) {
        return this.getUserController().hasUser(packet.getSourceUser()) ;
    }

    /**
     * Manage the received packet instance.
     *
     * @param socket : packet's socket.
     * @param packet : received packet.
     */
    protected void manageReceivedPacket(Socket socket, MessagePacket packet) {
        MessageData data = packet.getData() ;

        System.out.println(data.getText()) ;
    }

}
