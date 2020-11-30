package Messenger.Foundation.Controllers;

import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Messages.Message;

/**
 * Handle the multiple discussions created.
 *
 * @author Maud PENNETIER
 */
public class ConversationController {

    /**
     * Start new discussion.
     *
     * @param user : user with whom the conversation is held.
     */
    public void start(User user)
    {
    // TODO
    }

    /**
     * Stop existing discussion.
     *
     * @param user : user with whom the conversation is held.
     */
    public void stop(User user)
    {
        // TODO
    }


    /**
     * Get the older messages.
     *
     * @param user : user with whom the conversation is held.
     */
    public void getHistoric(User user)     /// METTRE UN NBR DE MESSAGES A GET ?
    {
        // TODO
    }


    /**
     * Send message to next user, nexthop network classes.
     *
     * @param user : user with whom the conversation is held.
     * @param message : content of the message.
     */
    public void send(User user, Message message)
    {
        // TODO
        // create  a Message instance (
    }

    private int isValidMessage(Message data)
    {
        // TODO
        return 0;
    }

    protected void saveMessage(Message message) {
        // TODO
    }

}
