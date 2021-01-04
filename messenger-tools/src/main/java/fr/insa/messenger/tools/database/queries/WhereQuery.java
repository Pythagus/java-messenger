package fr.insa.messenger.tools.database.queries;

import java.util.ArrayList;
import fr.insa.messenger.tools.database.DatabaseConnection;
import fr.insa.messenger.tools.database.clauses.LimitClause;
import fr.insa.messenger.tools.database.clauses.WhereClause;

/**
 * @author Damien MOLINA
 */
@SuppressWarnings("unchecked")
abstract public class WhereQuery<T extends WhereQuery<?>> extends DatabaseQuery {

    /**
     * Make a query instance.
     *
     * @param connection : database connection instance.
     * @param table      : table name.
     */
    public WhereQuery(DatabaseConnection connection, String table) {
        super(connection, table);
    }

    /**
     * Take maximum howMany rows in the
     * datatable.
     *
     * @param howMany : how many rows.
     */
    public void limit(int howMany) {
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
    public T where(String field, String operator, Object value) {
        return this.where(field, operator, value, "AND") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @return this
     */
    public T where(WhereClause.WhereCallable callable) {
        return this.where(callable, "AND") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @return this
     */
    public T orWhere(WhereClause.WhereCallable callable) {
        return this.where(callable, "OR") ;
    }

    /**
     * Add a where clause.
     *
     * @param callable : callable to make precise where clause.
     * @param connector : callable connector to the rest of the query.
     * @return this
     */
    private T where(WhereClause.WhereCallable callable, String connector) {
        DeleteQuery query = (DeleteQuery) callable.handle(
            new DeleteQuery(this.connection, this.table)
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

        return (T) this ;
    }

    /**
     * Add a where clause to the query.
     *
     * @param field : where field.
     * @param operator : comparator operator.
     * @param value : field potential value.
     * @return this
     */
    public T orWhere(String field, String operator, Object value) {
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
    private T where(String field, String operator, Object value, String connector) {
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
    private T where(WhereClause clause, Object value) {
        this.clauses.where(clause) ;
        this.parameters.add(value.toString()) ;

        return (T) this ;
    }

}
