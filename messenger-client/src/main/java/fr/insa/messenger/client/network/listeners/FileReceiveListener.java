package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import fr.insa.messenger.client.models.Conversation;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.network.models.FilePacket;
import fr.insa.messenger.client.system.assets.Sound;
import fr.insa.messenger.client.ui.screens.DiscussionScreen;

/**
 * @author Maud PENNETIER
 */
public class FileReceiveListener extends FileServerListener {

    /**
     * Make a new listener instance.
     *
     * @param port : listening port.
     */
    public FileReceiveListener(int port) throws IOException {
        super(port) ;
    }

    @Override
    protected void manageFilePacket(Socket socket, FilePacket packet) {
        // Discussion screen.
        DiscussionScreen screen = GraphicInterface.instance().discussionScreen() ;

        // Active conversation.
        Conversation activeConversation = screen.getConversation() ;

        /*
         * If there is an active conversation and
         * the conversation is the current displayed,
         * then add the file to the screen.
         */
        if(activeConversation != null && activeConversation.getTarget().equals(packet.getSourceUser())) {
            screen.getList().addItem(packet) ;
        }

        // Play a sound.
        Sound.play("new-message-sound.wav") ;
    }

}
