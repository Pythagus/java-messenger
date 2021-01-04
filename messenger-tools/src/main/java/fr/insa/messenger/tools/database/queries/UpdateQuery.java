package fr.insa.messenger.tools.database.queries;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.insa.messenger.tools.database.DatabaseConnection;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;

/**
 * @author Damien MOLINA
 */
public class UpdateQuery extends WhereQuery<UpdateQuery> {

    /**
     * Updated values.
     */
    private final LinkedHashMap<String, Object> values ;

    /**
     * Make a query instance.
     *
     * @param table : table name.
     */
    public UpdateQuery(DatabaseConnection connection, String table) {
        super(connection, table) ;

        this.values = new LinkedHashMap<>() ;
    }

    /**
     * Prepare and return the SQL syntax request.
     *
     * @return the SQL syntax request.
     */
    protected String prepareSQL() {
        String values = "" ;

        if(! this.values.isEmpty()) {
            StringBuilder builder = new StringBuilder() ;

            int i = 0 ;
            for (Map.Entry<String, Object> entry : this.values.entrySet()) {
                builder.append(entry.getKey()).append(" = ?, ") ;
                this.parameters.add(i, entry.getValue()) ;
                i++ ;
            }

            values = builder.toString() ;
            values = values.substring(0, values.length() - 2) ; // Remove the last ", " element.
        }

        return "UPDATE %s SET " + values.trim() ;
    }

    /**
     * Add a value to insert.
     *
     * @param key : value's key.
     * @param value : value to insert.
     * @return this.
     */
    public UpdateQuery value(String key, Object value) {
        this.values.put(key, value) ;

        return this ;
    }

    /**
     * Execute the insert query and
     * return the inserted id.
     *
     * @throws DatabaseException : insertion failed.
     */
    public void execute() throws DatabaseException {
        int status = 0 ;

        try {
            status = this.prepare().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace() ;
        }

        if(status <= 0) {
            throw new DatabaseException("No row updated") ;
        }
    }

}
