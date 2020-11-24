package Messenger.Foundation.Observers.Contracts;

/**
 * @author Damien MOLINA
 */
public interface Listener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    void notify(Object... args) ;

}
