package fr.insa.messenger.tools.database.queries;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fr.insa.messenger.tools.database.DatabaseConnection;
import fr.insa.messenger.tools.database.DatabaseClauseManager;

/**
 * @author Damien MOLINA
 */
abstract public class DatabaseQuery implements AutoCloseable {

    /**
     * Table name.
     */
    protected final String table ;

    /**
     * Database connection instance.
     */
    protected final DatabaseConnection connection ;

    /**
     * Query parameters.
     */
    protected final ArrayList<Object> parameters ;

    /**
     * Clauses manager.
     */
    protected final DatabaseClauseManager clauses ;

    /**
     * Make a query instance.
     *
     * @param table : table name.
     * @param connection : database connection instance.
     */
    public DatabaseQuery(DatabaseConnection connection, String table) {
        this.table      = table ;
        this.connection = connection ;
        this.parameters = new ArrayList<>() ;
        this.clauses    = new DatabaseClauseManager() ;
    }

    /**
     * Get the clauses manager.
     *
     * @return clauses manager instance.
     */
    public DatabaseClauseManager getClauses() {
        return this.clauses ;
    }

    /**
     * Prepare and return the SQL syntax request.
     *
     * @return the SQL syntax request.
     */
    abstract protected String prepareSQL() ;

    /**
     * Close the query.
     */
    public void close() {
        try {
            if(this.connection != null) {
                this.connection.close() ;
            }
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Prepare the SQL statement.
     *
     * @return this
     * @throws SQLException : connection error.
     */
    protected PreparedStatement prepare() throws SQLException {
        if(this.connection == null) {
            throw new SQLException("Connection with the database failed") ;
        }

        // format the SQL query.
        String sqlQuery = String.format(this.prepareSQL(), this.table) ;
        sqlQuery += " " + this.clauses.formatClauses() ;

        try {
            PreparedStatement statement = this.connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS) ;

            // We are adding all the statement parameters.
            for(int i = 0 ; i < this.parameters.size() ; i++) {
                Object value = this.parameters.get(i) ;

                if(value instanceof String) {
                    statement.setString(i + 1, (String) value) ;
                } else if (value instanceof Integer) {
                    statement.setInt(i + 1, (Integer) value) ;
                }
            }

            System.out.println(statement.toString()) ;

            return statement ;
        } catch (SQLException e) {
            e.printStackTrace() ;

            return null ;
        }
    }

}
