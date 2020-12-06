package Messenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import Messenger.Database.DB;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Config;
import Messenger.Database.Models.MessageTable;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Foundation.Models.Messages.MessageData;

/**
 * @author Damien MOLINA
 */
public class LauncherDatabase {

    public static void main(String[] args) {
        Messenger msn = new Messenger() ;
        msn.start() ;

        Config.load();
        // Load the database.
        DB.load() ;

        try {
            LauncherDatabase.readTable() ;
            LauncherDatabase.insertTable() ;
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    private static void readTable() throws SQLException {
        new MessageTable().select(new User(), new User()).forEach((ResultSet r) -> {
            System.out.println(
                r.getString("content")
            ) ;

            return true ;
        }) ;
    }

    private static void insertTable() throws SQLException {
        Message message = new Message(
            new User(), new MessageData("BONJOUR PARIS, le retour 2 !", null)
        ) ;

        new MessageTable().insert(message) ;
    }

}
