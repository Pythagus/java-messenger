package fr.insa.messenger.client.models;

import java.io.Serializable;
import java.sql.SQLException;
import fr.insa.messenger.tools.database.DatabaseInterface;
import fr.insa.messenger.tools.database.DatabaseSelectResult;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER, Damien MOLINA
 */
public class Message extends AbstractMessage<String> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242411L ;

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param text : message text.
     */
    public Message(User target, String text) {
        super(target, text) ;
    }

    /**
     * Make a new Message instance.
     *
     * @param sender : sender user.
     * @param target : targeted user.
     * @param content : message text.
     * @param timestamp : date time.
     */
    public Message(User sender, User target, String content, long timestamp) {
        super(sender, target, content, timestamp) ;
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
        this.databaseInsert(Type.MESSAGE, this.getContent()) ;
    }

}
