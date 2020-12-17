package fr.insa.messenger.network.listeners;

import java.net.Socket;
import java.io.IOException;
import fr.insa.messenger.ui.GraphicInterface;
import fr.insa.messenger.models.Conversation;
import fr.insa.messenger.controllers.UserController;
import fr.insa.messenger.system.assets.sounds.Sound;
import fr.insa.messenger.ui.screens.DiscussionScreen;
import fr.insa.messenger.network.models.MessagePacket;

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

        // Play a sound.
        Sound.play("new-message-sound.wav") ;
    }

}
