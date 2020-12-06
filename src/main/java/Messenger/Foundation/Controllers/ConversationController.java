package Messenger.Foundation.Controllers;

import java.sql.SQLException;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.Database.Models.MessageTable;
import Messenger.Network.Utils.BroadcastSplitter;
import Messenger.Foundation.Models.Messages.Message;

/**
 * Handle the multiple discussions created.
 *
 * @author Maud PENNETIER, Damien MOLINA
 */
public class ConversationController extends Controller {

    /**
     * Start new discussion.
     *
     * @param user : user with whom the conversation is held.
     */
    public void start(User user) {
        Env.getNetworkInterface().getEnvoyer().sendRequestMeeting(user) ;
    }

    /**
     * Stop existing discussion.
     *
     * @param user : user with whom the conversation is held.
     */
    public void stop(User user) {
        // TODO
    }


    /**
     * Get the older messages.
     *
     * @param user : user with whom the conversation is held.
     */
    public Message[] getHistoric(User user) {
        // TODO

        return new Message[0] ;
    }


    /**
     * Send message to next user, nexthop network classes.
     *
     * @param message : content of the message.
     */
    public void send(Message message) {
        Env.getNetworkInterface().getEnvoyer().sendMessage(message) ;

        try {
            new MessageTable().insert(message) ;
        } catch(SQLException e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Verify if the message is not too long (i.e. it fits only one socket).
     *
     * @param msg : a string to send to the other user
     * @return a boolean, 1 if the message is valid, 0 otherwise
     */
    private boolean isValidMessage(Message msg) {
        // TODO
        return true ;
    }

    /**
     * Determine whether the given text
     * is valid.
     *
     * @param text : submitted text.
     * @return True or False.
     */
    public boolean isValidTest(String text) {
        return text.length() > 0 && ! text.contains(BroadcastSplitter.DELIMITER) ;
    }

}
