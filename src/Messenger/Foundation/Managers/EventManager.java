package Messenger.Foundation.Managers;

import java.util.HashMap;
import java.util.ArrayList;
import Messenger.Foundation.Observers.Event;
import Messenger.Foundation.Observers.Listener;

/**
 * @author Damien MOLINA
 */
public class EventManager {

    /**
     * The event listener mappings for the
     * application.
     */
    protected final static HashMap<String, ArrayList<Listener>> listeners = new HashMap<>() ;

    /**
     * Fire the given event.
     *
     * @param event : event to fire.
     */
    public static void fire(Event event) {
        EventManager
                .getListeners(event.getName())
                .forEach(listener -> listener.handle(event)) ;
    }

    /**
     * Add a listener to an event.
     *
     * @param c : event class to listen.
     * @param listener : listener instance.
     */
    public static void bind(Class<? extends Event> c, Listener listener) {
        EventManager.bind(Event.getName(c), listener) ;
    }

    /**
     * Add a listener to an event.
     *
     * @param event : event class name to listen.
     * @param listener : listener instance.
     */
    public static void bind(String event, Listener listener) {
        ArrayList<Listener> list = EventManager.getListeners(event) ;

        if(list == null) {
            list = new ArrayList<>() ;
        }

        list.add(listener) ;

        EventManager.listeners.put(event, list) ;
    }

    /**
     * Get the listeners of a particular event.
     *
     * @param event : array key
     * @return the value (a list)
     */
    private static ArrayList<Listener> getListeners(String event) {
        return EventManager.listeners.get(event) ;
    }

}
