package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;

import fr.insa.messenger.client.network.models.basis.Packet;
import fr.insa.messenger.client.system.console.Console;

/**
 * @author Damien MOLINA
 */
abstract public class ServerListener<T extends Packet<?>> extends NetworkBaseListener<ServerSocket> {

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
    public ServerListener(int port) throws IOException {
        super(new ServerSocket(port)) ;

        this.run = true ;
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    abstract protected void manageReceivedPacket(Socket socket, T packet) ;

    /**
     * Manage the received file instance
     *
     */
    // TODO
    //abstract protected void manageFilePacket(Socket socket, T packet);

    /**
     * Determine whether the packet should
     * be managed.
     *
     * @param packet : packet to manage.
     * @return True if the packet should be managed,
     *          False otherwise.
     */
    abstract protected boolean shouldManagePacket(T packet) ;

    /**
     *
     * @param packet : packet to manage.
     * @return True if the packet is a file,
     *          False otherwise.
     */
    // TODO
    //abstract protected boolean shouldManageFile(T packet);

    /**
     * Run the listener.
     */
    @SuppressWarnings("unchecked")
    public void run() {
        String name = this.getClass().getSimpleName() ;

        while(this.run) {
            try {
                Console.comment("=> " + name + " is waiting") ;
                Socket socket        = this.listenerSocket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream()) ;
                Console.comment("=> " + name + " received a packet from " + socket.getInetAddress()) ;

                T packet = (T) is.readObject() ;

                // TODO : do it in a thread

                if(this.shouldManagePacket(packet)) {
                    this.manageReceivedPacket(socket, packet) ;
                } else {
                    Console.warning("Received packet not managed") ;
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
