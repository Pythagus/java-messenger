package Messenger.Database.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import Messenger.Utils.DateUtils;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.Database.Queries.DatabaseResult;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Foundation.Models.Messages.MessageFile;
import Messenger.Foundation.Models.Messages.MessageData;

/**
 * @author Damien MOLINA
 */
public class MessageTable extends DatabaseModel {

    /**
     * Model table name.
     */
    protected String table = "messages" ;

    /**
     * Available message type.
     */
    public enum Type {
        MESSAGE, FILE
    }

    /**
     * Select the messages of the two given users.
     *
     * @param user1 : user 1
     * @param user2 : user 2
     * @return all the messages between the two users.
     * @throws SQLException : sql error.
     */
    public DatabaseResult select(User user1, User user2) throws SQLException {
        String sql = String.format(
            "SELECT * FROM %s " +
                "WHERE (user_sender = ? AND user_receiver = ?) " +
                "OR (user_sender = ? AND user_receiver = ?) " +
                "ORDER BY sent_at ASC", this.table
        ) ;

        return this.query().prepare(
            sql, user1.getIdentifier(), user2.getIdentifier(), user2.getIdentifier(), user1.getIdentifier()
        ).executeQuery() ;
    }

    /**
     * Insert the given message into the database.
     *
     * @param message : message to insert.
     * @throws SQLException : sql error.
     */
    public void insert(Message message) throws SQLException {
        MessageData data = message.getData() ;

        if(data.hasFile()) {
            this.insert(message, Type.FILE, data.getFile().getFullPath()) ;
        }

        if(data.hasText()) {
            this.insert(message, Type.MESSAGE, data.getText()) ;
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
    private void insert(Message message, Type type, String content) throws SQLException {
        String sql = String.format(
            "INSERT INTO %s (user_sender, user_receiver, type, content) VALUES (?, ?, ?, ?);", this.table
        ) ;

        int status = this.query().prepare(
            sql, message.getSender().getIdentifier(), message.getTarget().getIdentifier(), type.toString(), content
        ).executeUpdate() ;

        if(status != 1) {
            throw new SQLException("Insert failed with status " + status) ;
        }
    }

    /**
     * Convert a result to a Message instance.
     *
     * @param result : database query result.
     * @return a Message instance.
     * @throws Exception : not found user.
     */
    public static Message toMessage(ResultSet result) throws Exception {
        UserController controller = Env.userController() ;

        Type type    = Type.valueOf(result.getString("type")) ;
        String value = result.getString("content") ;
        String text  = type.equals(Type.MESSAGE) ? value : null ;
        MessageFile file = null ; // TODO : check if it is a file.

        String sender = result.getString("user_sender") ;
        String receiver = result.getString("user_receiver") ;

        return new Message(
            controller.getFromIdentifier(sender), controller.getFromIdentifier(receiver),
            new MessageData(text, file), DateUtils.timestamp(result.getString("sent_at"))
        ) ;
    }

}
