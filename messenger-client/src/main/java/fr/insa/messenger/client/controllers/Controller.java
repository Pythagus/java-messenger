package fr.insa.messenger.client.controllers;

import fr.insa.messenger.client.observers.contracts.Listener;
import fr.insa.messenger.client.observers.contracts.Observable;
import fr.insa.messenger.client.observers.contracts.ObserverProvider;

/**
 * @author Damien MOLINA
 */
abstract public class Controller implements Observable {

    /**
     * Observers provider.
     */
    private final ObserverProvider observer = new ObserverProvider() ;

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    @Override
    public void addListener(Listener listener) {
        this.observer.addListener(listener) ;
    }

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    @Override
    public void removeListener(Listener listener) {
        this.observer.removeListener(listener) ;
    }

    /**
     * Notify all the observers with the given arguments.
     *
     * @param args : notification arguments.
     */
    protected void notifyAllListeners(Object... args) {
        this.observer.notifyAllListeners(args) ;
    }

}
