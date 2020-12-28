package fr.insa.messenger.tools.database.queries;

import java.util.Map;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.sql.PreparedStatement;
import fr.insa.messenger.tools.database.DatabaseException;
import fr.insa.messenger.tools.database.DatabaseConnection;

/**
 * @author Damien MOLINA
 */
public class InsertQuery extends DatabaseQuery {

    /**
     * Inserted values.
     */
    private final LinkedHashMap<String, Object> values ;

    /**
     * Make a query instance.
     *
     * @param table : table name.
     */
    public InsertQuery(DatabaseConnection connection, String table) {
        super(connection, table) ;

        this.values = new LinkedHashMap<>() ;
    }

    /**
     * Prepare and return the SQL syntax request.
     *
     * @return the SQL syntax request.
     */
    protected String prepareSQL() {
        ArrayList<String> keys = new ArrayList<>() ;

        for (Map.Entry<String, Object> entry : this.values.entrySet()) {
            keys.add(entry.getKey()) ;
            this.parameters.add(entry.getValue()) ;
        }

        String values = "" ;

        if(keys.size() > 0) {
            values = "?, ".repeat(keys.size()) ;
            values = values.substring(0, values.length() - 2) ; // Remove the last ", " element.
        }

        return "INSERT INTO %s (" + String.join(", ", keys) + ") VALUES (" + values + ")" ;
    }

    /**
     * Add a value to insert.
     *
     * @param key : value's key.
     * @param value : value to insert.
     * @return this.
     */
    public InsertQuery value(String key, Object value) {
        this.values.put(key, value) ;

        return this ;
    }

    /**
     * Execute the insert query and
     * return the inserted id.
     *
     * @return the inserted id.
     * @throws DatabaseException : insertion failed.
     */
    public int execute() throws DatabaseException {
        try {
            PreparedStatement statement = this.prepare() ;
            int status = statement.executeUpdate() ;

            if(status <= 0) {
                throw new DatabaseException("No row updated") ;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1) ;
                } else {
                    throw new SQLException("No ID obtained");
                }
            }
        } catch(SQLException e) {
            if(e instanceof DatabaseException) {
                throw (DatabaseException) e ;
            } else {
                e.printStackTrace() ;
            }
        }

        return -1 ;
    }

}
