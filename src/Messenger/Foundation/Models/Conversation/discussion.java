package Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Message;
import java.util.ArrayList;

public class discussion
{
    /**
     * Describe 1 discussion between 2 users
     * @author Maud PENNETIER
     */

    protected User otherUser;
    protected ArrayList<Message> historic = new ArrayList<Message>();


    public void Discussion(User user)
    {
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
