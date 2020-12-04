package Messenger.Foundation.Observers.Listeners;

import Messenger.Foundation.Observers.BaseListener;

/**
 * @author Damien MOLINA
 */
public class UserListUpdated extends BaseListener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    @Override
    public void handle(Object... args) {
        System.out.println("List updated") ;

        // TODO : TO DO !
    }

}
