package fr.insa.messenger.tools.database.queries;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import fr.insa.messenger.tools.database.DatabaseObject;
import fr.insa.messenger.tools.database.DatabaseConnection;
import fr.insa.messenger.tools.database.DatabaseSelectResult;
import fr.insa.messenger.tools.database.exceptions.DatabaseNotFoundException;

/**
 * @author Damien MOLINA
 */
public class SelectQuery extends WhereQuery<SelectQuery> {

    /**
     * Selected fields.
     */
    private final String fields ;

    /**
     * Make a query instance.
     *
     * @param table : table name.
     */
    public SelectQuery(DatabaseConnection connection, String table, String[] fields) {
        super(connection, table) ;

        this.fields = fields.length > 0 ? String.join(",", fields) : "*" ;
    }

    /**
     * Prepare and return the SQL syntax request.
     *
     * @return the SQL syntax request.
     */
    protected String prepareSQL() {
        return "SELECT " + this.fields + " FROM %s" ;
    }

    /**
     * Get all the items matching the query.
     *
     * @return list of items.
     */
    public DatabaseSelectResult get() {
        return this.get(null) ;
    }

    /**
     * Get the rows matching the query.
     *
     * @param howMany : how many rows should be get.
     * @return array of results.
     */
    public DatabaseSelectResult get(Integer howMany) {
        DatabaseSelectResult list = null ;

        try {
            if(howMany != null && howMany >= 0) {
                this.limit(howMany) ;
            }

            ResultSet set = this.prepare().executeQuery() ;

            /*
             * Initialize the list only when
             * we are sure the request passed.
             */
            list = new DatabaseSelectResult() ;

            while(set.next()) {
                ResultSetMetaData metaData = set.getMetaData() ;
                DatabaseObject obj = new DatabaseObject() ;

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    obj.put(
                        metaData.getColumnLabel(i), set.getString(i)
                    ) ;
                }

                list.add(obj) ;
            }
        } catch (Exception e) {
            e.printStackTrace() ;
        }

        return list ;
    }

    /**
     * Get the first element matching the query.
     *
     * @return the element, or null.
     */
    public DatabaseObject first() throws DatabaseNotFoundException {
        DatabaseSelectResult list = this.get(1) ;

        if(list == null) {
            throw new DatabaseNotFoundException(this) ;
        }

        return list.stream().findFirst().orElse(null) ;
    }

}
