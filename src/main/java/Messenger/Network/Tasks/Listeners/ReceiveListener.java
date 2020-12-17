package Messenger.Network.Tasks.Listeners;

import java.net.Socket;
import java.io.IOException;
import Messenger.GUI.GraphicInterface;
import Messenger.Network.Models.MessagePacket;
import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Frames.Screens.DiscussionScreen;
import Messenger.Foundation.Controllers.UserController;
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
        return UserController.instance().hasUser(packet.getSourceUser()) ;
    }

    /**
     * Manage the received packet instance.
     *
     * @param socket : packet's socket.
     * @param packet : received packet.
     */
    protected void manageReceivedPacket(Socket socket, MessagePacket packet) {
        // Discussion screen.
        DiscussionScreen screen = GraphicInterface.instance().discussionScreen() ;

        // Active conversation.
        Conversation activeConversation = screen.getConversation() ;

        /*
         * If there is an active conversation and
         * the conversation is the current displayed,
         * then add the message to the screen.
         */
        if(activeConversation != null && activeConversation.getTarget().equals(packet.getSourceUser())) {
            screen.getList().addItem(packet.getData()) ;
        }
    }

}
