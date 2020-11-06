package Messenger.observers;

/**
 * @author Damien MOLINA
 */
abstract public class Listener {

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    abstract public void handle(Event event) ;

}
