package Messenger.Network.Tasks.Listeners.Concerns;

import java.io.Closeable;

/**
 * @author Damien MOLINA
 */
abstract public class NetworkBaseListener<T extends Closeable> extends Thread {

    /**
     * Listener socket.
     */
    protected T socket ;

    /**
     * Make a new listener instance.
     *
     * @param socket : listening socket.
     */
    public NetworkBaseListener(T socket) {
        this.socket = socket ;
    }

    /**
     * Get the socket instance.
     *
     * @return the socket instance.
     */
    public T getSocket() {
        return this.socket ;
    }

    /**
     * Run the listener.
     */
    abstract public void run() ;

}
