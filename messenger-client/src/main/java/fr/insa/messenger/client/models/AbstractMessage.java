package fr.insa.messenger.client.models;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.GregorianCalendar;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.utils.DateUtils;
import fr.insa.messenger.tools.database.DatabaseInterface;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER, Damien MOLINA
 */
abstract public class AbstractMessage<T> implements Serializable {

    /**
     * Available message type.
     */
    public enum Type {
        MESSAGE, FILE
    }

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242409L ;

    /**
     * Message text.
     */
    protected final T content ;

    /**
     * Send date.
     */
    protected final long timestamp ;

    /**
     * Sender of the message.
     */
    protected final User sender ;

    /**
     * Targeted user.
     */
    protected final User target ;

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param content : message content.
     */
    public AbstractMessage(User target, T content) {
        /*
         * When the Message instance is created,
         * the date attribute is set to get the
         * exact sending time.
         */
        this(
            target, content, new Timestamp(System.currentTimeMillis()).getTime()
        ) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param content : message content.
     */
    public AbstractMessage(User target, T content, long timestamp) {
        this(Env.getUser(), target, content, timestamp) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param sender : sender user.
     * @param target : targeted user.
     * @param content : message text.
     * @param timestamp : date time.
     */
    public AbstractMessage(User sender, User target, T content, long timestamp) {
        this.target    = target ;
        this.content   = content ;
        this.sender    = sender ;
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
     * Get the message content.
     *
     * @return the message content.
     */
    public String getContent() {
        return this.content.toString() ;
    }

    /**
     * Insert a new message row.
     *
     * @param type : row type.
     * @param content : row content.
     * @throws SQLException : sql error.
     */
    protected void databaseInsert(Type type, String content) throws SQLException {
        DatabaseInterface.insert("messages")
            .value("user_sender", this.getSender().getIdentifier())
            .value("user_receiver", this.getTarget().getIdentifier())
            .value("type", type.toString())
            .value("content", content)
            .execute() ;
    }

}
