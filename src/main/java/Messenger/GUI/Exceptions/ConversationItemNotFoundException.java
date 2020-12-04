package Messenger.GUI.Exceptions;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class ConversationItemNotFoundException extends UiException {

    public enum Type {
        /**
         * The active conversation item
         * was not found.
         */
        ACTIVE,
    }

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param user : targeted user instance.
     */
    public ConversationItemNotFoundException(User user) {
        super(
            "Conversation with " + user.getPseudo() + " not found"
        ) ;
    }

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param type : exception type.
     */
    public ConversationItemNotFoundException(Type type) {
        super(
            "Conversation '" + type + "' not found"
        ) ;
    }

}
