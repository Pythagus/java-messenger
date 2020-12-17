package fr.insa.messenger.observers.contracts;

/**
 * @author Damien MOLINA
 */
public interface Observable {

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    void addListener(Listener listener) ;

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    void removeListener(Listener listener) ;

}
