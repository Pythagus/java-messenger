package fr.insa.messenger.client.observers;

import fr.insa.messenger.client.models.User;
import java.util.concurrent.ConcurrentLinkedQueue;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.client.ui.screens.contacts.ContactBar;
import fr.insa.messenger.client.observers.contracts.BaseListener;
import fr.insa.messenger.client.controllers.ConversationController;
import fr.insa.messenger.client.ui.screens.discussions.DiscussionBar;
import fr.insa.messenger.tools.models.UserStatus;

/**
 * @author Damien MOLINA
 */
public class UserListListener extends BaseListener {

    /**
     * List of the users that have not
     * been updated graphically.
     */
    private static final ConcurrentLinkedQueue<WaitingElement> users = new ConcurrentLinkedQueue<>() ;

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    @Override
    public void handle(Object... args) {
        UserListListener.users.add(new WaitingElement(
            (User) args[0], (UserController.UpdateState) args[1], args.length >= 3 ? args[2] : null
        )) ;

        UserListListener.updateUI() ;
    }

    /**
     * Manage the waiting users.
     */
    public static synchronized void updateUI() {
        ContactBar cBar = GraphicInterface.instance().contactBar() ;
        DiscussionBar dBar = GraphicInterface.instance().discussionBar() ;

        /*
         * This is mandatory because the contact
         * bar is not generated when the user is
         * logging in. So, we need to wait until
         * the bar is made.
         */
        if(cBar != null && dBar != null) {
            UserListListener.users.forEach((WaitingElement el) -> {
                User user = el.getUser() ;

                switch(el.getState()) {
                    case ADDED:
                        cBar.addItem(user) ;
                        break ;
                    case REMOVED:
                        ConversationController.instance().stop(user) ;
                        cBar.removeItem(user) ;
                        break ;
                    case UPDATED:
                        cBar.updateUserPseudo(user, (String) el.getData()) ;
                        break ;
                    case STATUS:
                        dBar.updateUserStatus(user, (UserStatus) el.getData()) ;
                        break ;
                }

                UserListListener.users.remove(el) ;
            }) ;
        }
    }

    /**
     * When a new user state comes, it creates a
     * new WaitingElement instance waiting for the
     * graphic interpreter.
     *
     * @author Damien MOLINA
     */
    private static class WaitingElement {

        /**
         * Waiting user instance.
         */
        private final User user ;

        /**
         * Update state.
         */
        private final UserController.UpdateState state ;

        /**
         * Update data.
         */
        private final Object data ;

        /**
         * Make a new waiting element instance.
         *
         * @param user : waiting user instance.
         * @param state : update state.
         * @param data : update data. Could be null.
         */
        WaitingElement(User user, UserController.UpdateState state, Object data) {
            this.user  = user ;
            this.state = state ;
            this.data  = data ;
        }

        /**
         * Get the user instance.
         *
         * @return the user instance.
         */
        public User getUser() {
            return this.user ;
        }

        /**
         * Get the update state.
         *
         * @return the update state.
         */
        public UserController.UpdateState getState() {
            return this.state ;
        }

        /**
         * Get the nullable data.
         *
         * @return the data.
         */
        public Object getData() {
            return this.data ;
        }

    }

}
