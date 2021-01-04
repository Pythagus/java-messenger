package fr.insa.messenger.client.network.listeners;

import fr.insa.messenger.client.models.Conversation;
import fr.insa.messenger.client.network.models.MessagePacket;
import fr.insa.messenger.client.system.assets.sounds.Sound;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.ui.screens.DiscussionScreen;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Maud PENNETIER
 */
public class FileReceiveListener extends FileServerListener<MessagePacket>
{

        /**
         * Make a new listener instance.
         *
         * @param port : listening port.
         */
        public FileReceiveListener(int port) throws IOException
        {
            super(port);
        }

    @Override
    protected void manageFilePacket(Socket socket, MessagePacket packet)
    {
        // TODO : make the file received appearing on the gui
        Console.warning("file received ") ;
        // Play a sound.
        Sound.play("new-message-sound.wav") ;
    }


}
