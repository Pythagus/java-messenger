package fr.insa.messenger.client.models;

import java.io.File;
import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.utils.DateUtils;
import fr.insa.messenger.tools.database.DatabaseObject;
import fr.insa.messenger.client.exceptions.AppException;
import fr.insa.messenger.tools.database.DatabaseInterface;
import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.tools.database.DatabaseSelectResult;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER, Damien MOLINA
 */
public class Message implements Serializable {

    /**
     * Available message type.
     */
    public enum Type {
        MESSAGE, FILE
    }

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242411L ;

    /**
     * Message text.
     */
    private final String text ;

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
     * @param text : message text.
     */
    public Message(User target, String text) {
        /*
         * When the Message instance is created,
         * the date attribute is set to get the
         * exact sending time.
         */
        this(
            target, text, new Timestamp(System.currentTimeMillis()).getTime()
        ) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param text : message text.
     */
    public Message(User target, String text, long timestamp) {
        this(Env.getUser(), target, text, timestamp) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param text : message text.
     */
    public Message(User sender, User target, String text, long timestamp) {
        this.target    = target ;
        this.text      = text ;
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
     * Get the message text.
     *
     * @return the String message.
     */
    public String getText() {
        return this.text ;
    }

    /**
     * Cats the given object as a
     * current class instance.
     *
     * @param obj : database row.
     * @return a new instance.
     */
    public static Message castFromDatabase(DatabaseObject obj) {
        try {
            Type type   = Type.valueOf(obj.get("type")) ;
            String text = type.equals(Type.MESSAGE) ? obj.get("content") : null ;

            return new Message(
                // Message sender.
                UserController.instance().getFromIdentifier(obj.get("user_sender")),
                // Message receiver.
                UserController.instance().getFromIdentifier(obj.get("user_receiver")),
                // Message data.
                text, DateUtils.timestamp(obj.get("sent_at"))
            ) ;
        } catch (AppException e) {
            e.printStackTrace();
        }

        return null ;
    }

    /**
     * Get the messages sent between both
     * of the given users for each other.
     *
     * @param user1 : first user instance.
     * @param user2 : second user instance.
     * @return the messages list.
     */
    public static DatabaseSelectResult select(User user1, User user2) throws SQLException {
        return DatabaseInterface.select("messages").where(query ->
            query.where("user_sender", "=", user1.getIdentifier()).where("user_receiver", "=", user2.getIdentifier())
        ).orWhere(query ->
            query.where("user_sender", "=", user2.getIdentifier()).where("user_receiver", "=", user1.getIdentifier())
        ).get() ;
    }

    /**
     * Insert the given message into the database.
     *
     * @throws SQLException : sql error.
     */
    public void databaseInsert() throws SQLException {
        this.databaseInsert(Type.MESSAGE, this.getText()) ;
    }

    /**
     * Insert the given file into the database.
     *
     * @param file : file to insert.
     * @throws SQLException : sql error.
     */
    public void databaseInsert(File file) throws SQLException {
        this.databaseInsert(Type.FILE, file.getName()) ;
    }

    /**
     * Insert a new message row.
     *
     * @param type : row type.
     * @param content : row content.
     * @throws SQLException : sql error.
     */
    private void databaseInsert(Type type, String content) throws SQLException {
        DatabaseInterface.insert("messages")
            .value("user_sender", this.getSender().getIdentifier())
            .value("user_receiver", this.getTarget().getIdentifier())
            .value("type", type.toString())
            .value("content", content)
            .execute() ;
    }

}
