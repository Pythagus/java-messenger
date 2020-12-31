package fr.insa.messenger.tomcat.controllers;

import java.sql.SQLException;
import fr.insa.messenger.tomcat.models.UserStatus;
import fr.insa.messenger.tools.database.DatabaseObject;
import fr.insa.messenger.tools.database.DatabaseInterface;
import fr.insa.messenger.tools.database.DatabaseSelectResult;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;

/**
 * @author Damien MOLINA
 */
final public class UserController {

    /**
     * Set the status of the given user.
     *
     * @param identifier : user string identifier.
     * @param status : new user's status.
     */
    public static void setStatus(String identifier, UserStatus status) throws DatabaseException {
        try {
            DatabaseObject row = DatabaseInterface.select("presence")
                .where("identifier", "=", identifier)
                .first() ;

            if(row == null) {
                // Add a new row.
                DatabaseInterface.insert("presence")
                    .value("identifier", identifier)
                    .value("status", status)
                    .execute() ;
            } else {
                // Update the row.
                DatabaseInterface.update("presence")
                    .where("identifier", "=", identifier)
                    .value("status", status.toString())
                    .execute() ;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage()) ;
        }
    }

    /**
     * Get the status of the user identified by
     * the given string.
     *
     * @param identifier : user identifier.
     * @return the status, null otherwise.
     */
    public static UserStatus getStatus(String identifier) {
        try {
            DatabaseObject row = DatabaseInterface.select("presence")
                .where("identifier", "=", identifier)
                .first() ;

            return row == null ? null : UserStatus.valueOf(row.get("status")) ;
        } catch (SQLException ignored) {
            return null ;
        }
    }

    /**
     * Get all the users stored in the database.
     *
     * @return array list of users.
     */
    public static DatabaseSelectResult getUsers() {
        try {
            return DatabaseInterface.select("presence").get() ;
        } catch (SQLException ignored) {
            return null ;
        }
    }

}
