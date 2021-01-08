package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import fr.insa.messenger.client.models.Conversation;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.network.models.FilePacket;
import fr.insa.messenger.client.system.assets.Sound;
import fr.insa.messenger.client.ui.screens.DiscussionScreen;

/**
 * @author Maud PENNETIER
 */
public class FileListener extends NetworkBaseListener<ServerSocket> {

    /**
     * Make a new listener instance.
     *
     * @param port : listening port.
     */
    public FileListener(int port) throws IOException {
        super(new ServerSocket(port)) ;
    }

    /**
     * Run the listener.
     */
    public void run() {
        String name = this.getClass().getSimpleName() ;

        while (this.run) {
            try {
                Console.comment("=> " + name + " is waiting for a file") ;

                // listener socket
                Socket socket = this.listenerSocket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream()) ;
                Console.comment("=> " + name + " received a file from " + socket.getInetAddress()) ;

                // receive a filePacket with information about the following file
                FilePacket filePacket = (FilePacket) is.readObject() ;
                FileOutputStream stream = new FileOutputStream(filePacket.getData().getFullTemporaryPath()) ;

                // size of the file
                long size = filePacket.getData().getSize() ;

                //receive all the packets containing the file
                int bytes ;
                byte[] buffer = new byte[4 * 1024] ;
                while (size > 0 && (bytes = is.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                    stream.write(buffer, 0, bytes) ;
                    size -= bytes ;
                }

                stream.close() ;
                this.manageFilePacket(socket, filePacket) ;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace() ;
            }
        }
    }

    /**
     * Manage the received file packet.
     *
     * @param socket : incoming socket.
     * @param packet : incoming packet.
     */
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
