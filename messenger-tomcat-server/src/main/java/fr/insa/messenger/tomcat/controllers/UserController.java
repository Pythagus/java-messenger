package fr.insa.messenger.tomcat.controllers;

import java.sql.SQLException;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.tools.database.DatabaseObject;
import fr.insa.messenger.tools.database.DatabaseInterface;
import fr.insa.messenger.tomcat.exceptions.UnknownUserException;
import fr.insa.messenger.tomcat.exceptions.AlreadyExistUserException;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;

/**
 * @author Damien MOLINA
 */
final public class UserController {

    /**
     * Get the user row identified by the
     * given identifier.
     *
     * @param identifier : user's identifier.
     * @throws SQLException : database connection error.
     */
    private static DatabaseObject getUser(String identifier) throws SQLException {
        return DatabaseInterface.select("presence")
            .where("identifier", "=", identifier)
            .first() ;
    }

    /**
     * Add a new user into the database.
     * His default status is DISCONNECTED.
     *
     * @param identifier : user's identifier.
     * @param ip : user's ip address.
     */
    public static void addUser(String identifier, String ip) throws DatabaseException, AlreadyExistUserException {
        try {
            DatabaseObject row = UserController.getUser(identifier) ;

            if(row != null) {
                throw new AlreadyExistUserException(identifier) ;
            }

            // Add a new row.
            DatabaseInterface.insert("presence")
                .value("identifier", identifier)
                .value("status", UserStatus.DISCONNECTED.toString())
                .value("address", ip)
                .execute() ;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage()) ;
        }
    }

    /**
     * Set the status of the given user.
     *
     * @param identifier : user string identifier.
     * @param status : new user's status.
     */
    public static void setStatus(String identifier, UserStatus status) throws DatabaseException, UnknownUserException {
        try {
            DatabaseObject row = UserController.getUser(identifier) ;

            if(row == null) {
                throw new UnknownUserException(identifier) ;
            }

            // Update the row.
            DatabaseInterface.update("presence")
                .where("identifier", "=", identifier)
                .value("status", status.toString())
                .execute() ;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage()) ;
        }
    }

}
