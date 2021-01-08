package fr.insa.messenger.client.network.listeners;

import java.io.Closeable;

/**
 * @author Damien MOLINA
 */
abstract public class NetworkBaseListener<T extends Closeable> extends Thread {

    /**
     * Listener socket.
     */
    protected final T listenerSocket ;

    /**
     * The current listener running
     * state.
     */
    protected boolean run ;

    /**
     * Make a new listener instance.
     *
     * @param listenerSocket : listening socket.
     */
    public NetworkBaseListener(T listenerSocket) {
        this.listenerSocket = listenerSocket ;
        this.run            = true ;
    }

    /**
     * Run the listener.
     */
    abstract public void run() ;

    /**
     * Stop the listener.
     */
    public void close() {
        this.run = false ;
    }

}
