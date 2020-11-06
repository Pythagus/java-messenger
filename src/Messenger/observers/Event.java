package Messenger.observers;

/**
 * @author Damien MOLINA
 */
abstract public class Event {

    /**
     * Get the name of the event.
     *
     * @return the event's name.
     */
    public String getName() {
        return Event.getName(this.getClass()) ;
    }

    /**
     * Get the name of the event.
     *
     * @return the event's name.
     */
    public static String getName(Class<? extends Event> c) {
        return c.getCanonicalName() ;
    }

}
