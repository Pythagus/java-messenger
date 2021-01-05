package fr.insa.messenger.client.network.listeners;

import fr.insa.messenger.client.network.models.basis.FilePacket;
import fr.insa.messenger.client.system.assets.sounds.Sound;
import fr.insa.messenger.client.system.console.Console;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Maud PENNETIER
 */
public class FileReceiveListener extends FileServerListener
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
    protected void manageFilePacket(Socket socket, FilePacket packet)
    {
        // TODO : make the file received appearing on the gui
        Console.warning("file received ") ;
        // Play a sound.
        Sound.play("new-message-sound.wav") ;
    }


}
