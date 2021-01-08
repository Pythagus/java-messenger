package fr.insa.messenger.client.factories;

import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.utils.DateUtils;
import fr.insa.messenger.client.models.MessageFile;
import fr.insa.messenger.client.models.AbstractMessage;
import fr.insa.messenger.tools.database.DatabaseObject;
import fr.insa.messenger.client.exceptions.AppException;
import fr.insa.messenger.client.controllers.UserController;

/**
 * @author Damien MOLINA
 */
final public class MessageFactory {

    /**
     * Cast the given database row.
     *
     * @param obj : database row.
     * @return the generated object.
     */
    public static AbstractMessage<?> castFromDatabase(DatabaseObject obj) {
        try {
            AbstractMessage.Type type = AbstractMessage.Type.valueOf(obj.get("type")) ;

            String text    = obj.get("content") ;
            User sender    = UserController.instance().getFromIdentifier(obj.get("user_sender")) ;
            User receiver  = UserController.instance().getFromIdentifier(obj.get("user_receiver")) ;
            long timestamp = DateUtils.timestamp(obj.get("sent_at")) ;

            switch(type) {
                case FILE:
                    return new MessageFile(sender, receiver, text, timestamp) ;
                case MESSAGE:
                    return new Message(sender, receiver, text, timestamp) ;
            }
        } catch (AppException e) {
            e.printStackTrace();
        }

        return null ;
    }

}
