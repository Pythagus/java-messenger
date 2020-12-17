package Messenger.Network.Tasks.Listeners.Concerns;

import java.io.Closeable;

/**
 * @author Damien MOLINA
 */
abstract public class NetworkBaseListener<T extends Closeable> extends Thread {

    /**
     * Listener socket.
     */
    protected final T listenerSocket;

    /**
     *  File listener socket
     */
    protected T filelistenerSocket;

    /**
     * Make a new listener instance.
     *
     * @param listenerSocket : listening socket.
     */
    public NetworkBaseListener(T listenerSocket) {
        this.listenerSocket = listenerSocket;
    }

    /**
     * Get the socket instance.
     *
     * @return the socket instance.
     */
    public T getListenerSocket() {
        return this.listenerSocket;
    }

    /**
     * Run the listener.
     */
    abstract public void run() ;

}
