package Messenger.Network.Tasks.Listeners.Concerns;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import Messenger.Foundation.Environment;
import Messenger.Network.Models.Concerns.Packet;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;

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
     * Determine whether the packet should
     * be managed.
     *
     * @param packet : packet to manage.
     * @return True if the packet should be managed,
     *          False otherwise.
     */
    abstract protected boolean shouldManagePacket(T packet) ;

    /**
     * Run the listener.
     */
    @SuppressWarnings("unchecked")
    public void run() {
        String name = this.getClass().getSimpleName() ;

        while(this.run) {
            try {
                if(Environment.getApplication().isDebugMode()) {
                    Console.comment("=> " + name + " is waiting") ;
                }

                Socket socket        = this.listenerSocket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream()) ;

                if(Environment.getApplication().isDebugMode()) {
                    Console.comment("=> " + name + " received a packet from " + socket.getInetAddress()) ;
                }

                T packet = (T) is.readObject() ;

                if(this.shouldManagePacket(packet)) {
                    this.manageReceivedPacket(socket, packet) ;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    protected UserController getUserController() {
        return (UserController) Environment.getController(UserController.class) ;
    }

    /**
     * Stop the listener.
     */
    public void close() {
        this.run = false ;
    }

}
