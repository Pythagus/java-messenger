package Messenger.Database.Queries;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Damien MOLINA
 */
public class DatabaseResult {

    /**
     * Query instance.
     */
    private final DatabaseQuery query ;

    /**
     * Result set.
     */
    private ResultSet result ;

    /**
     * Make a new instance of result manager.
     *
     * @param query : query instance.
     */
    public DatabaseResult(DatabaseQuery query) {
        this.query = query ;
    }

    /**
     * Set the result.
     *
     * @param result : query result.
     * @return this
     */
    public DatabaseResult setResult(ResultSet result) {
        this.result = result ;

        return this ;
    }

    /**
     * Iterate the given manager to each
     * elements.
     *
     * @param manager : iterator.
     * @throws SQLException : sql error.
     */
    public void forEach(ResultCallback manager) throws SQLException {
        while(this.result.next()) {
            if(! manager.manage(this.result)) {
                break ;
            }
        }

        try {
            this.result.close() ;
            this.query.close() ;
        } catch (SQLException e) {
            e.printStackTrace() ;
        }
    }

}
