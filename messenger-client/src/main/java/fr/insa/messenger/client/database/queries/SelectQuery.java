package fr.insa.messenger.client.database.queries;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import fr.insa.messenger.client.database.DatabaseObject;
import fr.insa.messenger.client.database.DatabaseConnection;
import fr.insa.messenger.client.database.clauses.LimitClause;
import fr.insa.messenger.client.database.clauses.WhereClause;
import fr.insa.messenger.client.database.DatabaseSelectResult;

/**
 * @author Damien MOLINA
 */
public class SelectQuery extends DatabaseQuery {

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
    public DatabaseObject first() {
        DatabaseSelectResult list = this.get(1) ;

        // No element found.
        if(list == null) {
            return null ;
        }

        return list.stream().findFirst().orElse(null) ;
    }

    /**
     * Take maximum howMany rows in the
     * datatable.
     *
     * @param howMany : how many rows.
     */
    private void limit(int howMany) {
        this.clauses.limit(new LimitClause(howMany)) ;
    }

    /**
     * Add a where clause to the query.
     *
     * @param field : where field.
     * @param operator : comparator operator.
     * @param value : field potential value.
     * @return this
     */
    public SelectQuery where(String field, String operator, Object value) {
        return this.where(field, operator, value, "AND") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @return this
     */
    public SelectQuery where(WhereClause.WhereCallable callable) {
        return this.where(callable, "AND") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @return this
     */
    public SelectQuery orWhere(WhereClause.WhereCallable callable) {
        return this.where(callable, "OR") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @param connector : callable connector to the rest of the query.
     * @return this
     */
    private SelectQuery where(WhereClause.WhereCallable callable, String connector) {
        SelectQuery query = callable.handle(
            new SelectQuery(this.connection, this.table, new String[] {})
        ) ;

        ArrayList<WhereClause> clauses = query.getClauses().getWhereClauses() ;
        int i = 1 ;
        for(WhereClause clause : clauses) {
            clause.setValue("?") ;

            if(i == 1) {
                clause.startWith("(") ;
                clause.setConnector(connector) ;
            }

            if(i == clauses.size()) {
                clause.endWith(") ") ;
            }

            this.where(clause, query.parameters.get(i - 1)) ;

            i++ ;
        }

        return this ;
    }

    /**
     * Add a where clause to the query.
     *
     * @param field : where field.
     * @param operator : comparator operator.
     * @param value : field potential value.
     * @return this
     */
    public SelectQuery orWhere(String field, String operator, Object value) {
        return this.where(field, operator, value, "OR") ;
    }

    /**
     * Add a where clause to the query.
     *
     * @param field : where field.
     * @param operator : comparator operator.
     * @param value : field potential value.
     * @param connector : clause connector.
     * @return this
     */
    private SelectQuery where(String field, String operator, Object value, String connector) {
        return this.where(
            new WhereClause(field, operator, "?", connector), value
        ) ;
    }

    /**
     * Add a where clause to the query.
     *
     * @param clause : where clause.
     * @param value : field potential value.
     * @return this.
     */
    private SelectQuery where(WhereClause clause, Object value) {
        this.clauses.where(clause) ;
        this.parameters.add(value.toString()) ;

        return this ;
    }

}
