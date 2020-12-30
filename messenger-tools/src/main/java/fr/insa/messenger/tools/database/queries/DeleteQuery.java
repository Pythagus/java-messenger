package fr.insa.messenger.tools.database.queries;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;
import fr.insa.messenger.tools.database.DatabaseConnection;

/**
 * @author Damien MOLINA
 */
public class DeleteQuery extends WhereQuery<DeleteQuery> {

    /**
     * Make a query instance.
     *
     * @param table : table name.
     */
    public DeleteQuery(DatabaseConnection connection, String table) {
        super(connection, table) ;
    }

    /**
     * Prepare and return the SQL syntax request.
     *
     * @return the SQL syntax request.
     */
    protected String prepareSQL() {
        return "DELETE FROM %s" ;
    }

    /**
     * Execute the delete query.
     * @throws DatabaseException : deletion failed.
     */
    public void execute() throws DatabaseException {
        try {
            PreparedStatement statement = this.prepare() ;
            int status = statement.executeUpdate() ;

            if(status <= 0) {
                throw new DatabaseException("No row deleted") ;
            }
        } catch(SQLException e) {
            if (e instanceof DatabaseException) {
                throw (DatabaseException) e ;
            } else {
                e.printStackTrace() ;
            }
        }
    }

}
