package fr.insa.messenger.client.observers.contracts;

import java.util.ArrayList;

/**
 * @author Damien MOLINA
 */
public class ObserverProvider implements Observable {

    /**
     * Listeners list.
     */
    private final ArrayList<Listener> listeners = new ArrayList<>() ;

    /**
     * Notify all the observers with the given arguments.
     *
     * @param args : notification arguments.
     */
    public void notifyAllListeners(Object... args) {
        this.listeners.forEach(
            (Listener listener) -> listener.handle(args)
        ) ;
    }

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    public void addListener(Listener listener) {
        this.listeners.add(listener) ;
    }

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    public void removeListener(Listener listener) {
        this.listeners.remove(listener) ;
    }

}
