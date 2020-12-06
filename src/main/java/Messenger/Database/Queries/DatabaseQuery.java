package Messenger.Database.Queries;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import Messenger.Database.Connectors.DatabaseConnection;

/**
 * @author Damien MOLINA
 */
public class DatabaseQuery {

    /**
     * Database connection executing
     * the building query.
     */
    private final DatabaseConnection connection ;

    /**
     * Query statement.
     */
    private PreparedStatement statement ;

    /**
     * Make a new database connection.
     *
     * @param connection : database connection.
     */
    public DatabaseQuery(DatabaseConnection connection) {
        this.connection = connection ;
    }

    /**
     * Close the query.
     */
    public void close() {
        try {
            if(this.statement != null) {
                this.statement.close() ;
            }

            if(this.connection != null) {
                this.connection.close() ;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Get the statement instance.
     *
     * @return the PreparedStatement instance.
     */
    public PreparedStatement getStatement() {
        return this.statement ;
    }

    /**
     * Prepare the SQL statement.
     *
     * @param sql : sql request.
     * @param values : request's values.
     * @return this
     * @throws SQLException : connection error.
     */
    public DatabaseQuery prepare(String sql, String... values) throws SQLException {
        this.statement = null ;

        if(this.connection == null) {
            throw new SQLException("Connection with the database failed") ;
        }

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql) ;

            /*
             * We are adding all the statement
             * parameters.
             */
            for(int i = 0 ; i < values.length ; i++) {
                statement.setString(i + 1, values[i]);
            }

            this.statement = statement ;
        } catch ( SQLException e ) {
            e.printStackTrace() ;
        }

        return this ;
    }

    /**
     * Execute the query.
     *
     * @return this
     * @throws SQLException : sql error.
     */
    public DatabaseResult executeQuery() throws SQLException {
        return new DatabaseResult(this).setResult(
            this.statement.executeQuery()
        ) ;
    }

    /**
     * Execute on update the given query.
     *
     * @return the update status.
     * @throws SQLException : sql error.
     */
    public int executeUpdate() throws SQLException {
        return this.statement.executeUpdate() ;
    }

}
