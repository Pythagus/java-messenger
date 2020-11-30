package Messenger.Foundation.Models.Messages;

import Messenger.Foundation.Environment;
import Messenger.Foundation.Models.User;
import java.util.Date;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER
 */
public class Message {

    /**
     * Message data.
     */
    protected MessageData data ;

    /**
     * Send date.
     */
    protected final Date date ;

    /**
     * Sender of the message.
     */
    protected User sender ;

    /**
     * Targeted user.
     */
    protected User target ;

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param data : sent message.
     */
    public Message(User target, MessageData data) {
        this.target = target ;
        this.data   = data ;
        this.sender = Environment.getUser() ;

        /*
         * When the Message instance is created,
         * the date attribute is set to get the
         * exact sending time.
         */
        this.date = new Date() ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param text : sent text.
     */
    public Message(User target, String text) {
        this(target, new MessageData(text, null)) ;
    }

}
