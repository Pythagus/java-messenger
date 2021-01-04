package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import fr.insa.messenger.client.network.models.basis.Packet;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.system.console.Console;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
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
    abstract protected void manageFilePacket(Socket socket, String packet);


    /**
     * Run the listener.
     */
    @SuppressWarnings("unchecked")
    public void run() {
        String name = this.getClass().getSimpleName() ;

        while(this.run) {
            try {
                Console.comment("=> " + name + " is waiting for a file") ;

                Socket socket        = this.filelistenerSocket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream()) ;

                Console.comment("=> " + name + " received a file from " + socket.getInetAddress()) ;

                String packet = (String) is.readObject() ;

                System.out.println(packet);

                // TODO : do it in a thread

                if(packet.equals("BOIimfCdPSTgspWu34MbJRWzgDRO3OmY4ULRjKdb")) {
                    Console.comment("=> a file has been detected") ;
                    int bytes = 0;

                    // receive file extension
                    String extension = (String) is.readObject();

                    // file name
                    Date date = new Date();
                    SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
                    String fileName = formate.format(date);
                    String pathToTemp = System.getProperty("java.io.tmpdir"); // get the temporary directory
                    FileOutputStream fileOutputStream = new FileOutputStream(pathToTemp + "Messenger" + fileName + extension);

                    // receive file size
                    long size = is.readLong();

                    //receive data
                    byte[] buffer = new byte[4*1024]; // must be coherent with the envoyer
                    while (size > 0 && (bytes = is.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1){
                        fileOutputStream.write(buffer, 0, bytes);
                        size -= bytes ;
                    }
                    fileOutputStream.close();
                    this.manageFilePacket(socket, packet) ;

                } else {
                    Console.warning("File packet not managed") ;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop the listener.
     */
    public void close() {
        this.run = false ;
    }



}
