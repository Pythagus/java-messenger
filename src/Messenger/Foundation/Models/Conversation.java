package Messenger.Foundation.Models;

import Messenger.Foundation.Models.Messages.Message;
import java.util.ArrayList;

/**
 * Describe 1 discussion between 2 users.
 *
 * @author Maud PENNETIER
 */
public class Conversation {

    /**
     * Targeted user.
     */
    protected User otherUser;

    /**
     * Messages list.
     */
    protected final ArrayList<Message> historic = new ArrayList<>();


    public Conversation(User user) {
        this.otherUser = user;
        //TODO
    }


    /**
     * return the user of a conversation
     *
     */
    public User getOtherUser()
    {
        return this.otherUser;
    }

}
