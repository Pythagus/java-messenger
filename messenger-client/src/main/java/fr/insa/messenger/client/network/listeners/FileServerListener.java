package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.models.FilePacket;

/**
 * Server dedicated to the incoming files
 *
 * @author Maud PENNETIER
 */
abstract public class FileServerListener extends NetworkBaseListener<ServerSocket> {

    /**
     * The current listener running
     * state.
     */
    protected boolean run ;

    /**
     * Make a new listener instance.
     *
     * @param port : listening port.
     */
    public FileServerListener(int port) throws IOException {
        super(new ServerSocket(port)) ;

        this.run = true ;
    }

    /**
     * Manage the received file instance
     *
     */
    abstract protected void manageFilePacket(Socket socket, FilePacket packet) ;

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
                FileOutputStream stream = new FileOutputStream(filePacket.getFullPath()) ;

                // size of the file
                long size = filePacket.getSize() ;

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
     * Stop the listener.
     */
    public void close(){
        this.run = false ;
    }

}

