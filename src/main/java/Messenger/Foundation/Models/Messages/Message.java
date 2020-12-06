package Messenger.Foundation.Models.Messages;

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;
import Messenger.Utils.DateUtils;
import java.util.GregorianCalendar;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER
 */
public class Message implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242411L ;

    /**
     * Message data.
     */
    protected MessageData data ;

    /**
     * Send date.
     */
    protected final long timestamp ;

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
        /*
         * When the Message instance is created,
         * the date attribute is set to get the
         * exact sending time.
         */
        this(
            target, data, new Timestamp(System.currentTimeMillis()).getTime()
        ) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param data : sent message.
     */
    public Message(User target, MessageData data, long timestamp) {
        this.target    = target ;
        this.data      = data ;
        this.sender    = Env.getUser() ;
        this.timestamp = timestamp ;
    }

    /**
     * @return date of the message
     */
    public Date getDate() {
        return new Date(this.timestamp) ;
    }

    /**
     * Get the date formatted to be
     * print.
     *
     * @return the formatted date.
     */
    public String getDateForHuman() {
        Date date = this.getDate() ;

        GregorianCalendar calendar = new GregorianCalendar() ;
        calendar.setTime(date) ;

        Date today = DateUtils.today() ;

        return DateUtils.format(
            date, calendar.before(today) ? DateUtils.DATE_TIME_FORMAT : DateUtils.TIME_FORMAT
        ) ;
    }

    /**
     * @return the sender
     */
    public User getSender() {
        return this.sender ;
    }

    /**
     * @return the target
     */
    public User getTarget() {
        return this.target ;
    }

    /**
     * @return the MessageData instance
     */
    public MessageData getData() {
        return this.data ;
    }

}
