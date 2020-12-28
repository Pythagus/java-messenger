package fr.insa.messenger.client.models.messages;

import java.util.Date;
import java.sql.Timestamp;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.utils.DateUtils;
import fr.insa.messenger.client.database.DatabaseObject;
import fr.insa.messenger.client.exceptions.AppException;
import fr.insa.messenger.client.database.DatabaseInterface;
import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.client.database.DatabaseSelectResult;

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
     * Message data.
     */
    protected final MessageData data ;

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
        this(Env.getUser(), target, data, timestamp) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param data : sent message.
     */
    public Message(User sender, User target, MessageData data, long timestamp) {
        this.target    = target ;
        this.data      = data ;
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
     * @return the MessageData instance
     */
    public MessageData getData() {
        return this.data ;
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
            Message.Type type = Message.Type.valueOf(obj.get("type")) ;
            String text       = type.equals(Message.Type.MESSAGE) ? obj.get("content") : null ;
            MessageFile file  = null ; // TODO : check if it is a file.

            return new Message(
                // Message sender.
                UserController.instance().getFromIdentifier(obj.get("user_sender")),
                // Message receiver.
                UserController.instance().getFromIdentifier(obj.get("user_receiver")),
                // Message data.
                new MessageData(text, file), DateUtils.timestamp(obj.get("sent_at"))
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
        return DatabaseInterface.query("messages").select().where(query ->
            query.where("user_sender", "=", user1.getIdentifier()).where("user_receiver", "=", user2.getIdentifier())
        ).orWhere(query ->
            query.where("user_sender", "=", user2.getIdentifier()).where("user_receiver", "=", user1.getIdentifier())
        ).get() ;
    }

    /**
     * Insert the given message into the database.
     *
     * @param message : message to insert.
     * @throws SQLException : sql error.
     */
    public static void insert(Message message) throws SQLException {
        MessageData data = message.getData() ;

        if(data.hasFile()) {
            Message.insert(message, Message.Type.FILE, data.getFile().getFullPath()) ;
        }

        if(data.hasText()) {
            Message.insert(message, Message.Type.MESSAGE, data.getText()) ;
        }
    }

    /**
     * Insert the given message into the database.
     *
     * @param message : message to insert.
     * @param type : data type.
     * @param content : data content.
     * @throws SQLException : sql error.
     */
    private static void insert(Message message, Message.Type type, String content) throws SQLException {
        DatabaseInterface.query("messages").insert()
            .value("user_sender", message.getSender().getIdentifier())
            .value("user_receiver", message.getTarget().getIdentifier())
            .value("type", type.toString())
            .value("content", content)
            .execute() ;
    }

}
