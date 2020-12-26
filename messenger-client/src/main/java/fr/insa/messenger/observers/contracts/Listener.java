package fr.insa.messenger.observers.contracts;

/**
 * @author Damien MOLINA
 */
public interface Listener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    void handle(Object... args) ;

}
