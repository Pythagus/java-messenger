package fr.insa.messenger.client.database.clauses;

import java.util.ArrayList;
import fr.insa.messenger.client.database.queries.SelectQuery;

/**
 * @author Damien MOLINA
 */
public class WhereClause extends DatabaseClause {

    /**
     * SQL where connectors.
     */
    private enum Connector {
        AND, OR
    }

    /**
     * Where field.
     */
    private String field ;

    /**
     * Comparator operator.
     */
    private final String operator ;

    /**
     * Field potential value.
     */
    private String value ;

    /**
     * Clause connector.
     */
    private Connector connector ;

    /**
     * Make a where clause instance.
     *
     * @param field : where field.
     * @param operator : comparator operator.
     * @param value : field potential value.
     * @param strConnector : clause connector.
     */
    public WhereClause(String field, String operator, String value, String strConnector) {
        this.field    = field ;
        this.operator = operator ;
        this.value    = value ;

        this.setConnector(strConnector) ;
    }

    /**
     * Start the clause with the
     * given string.
     *
     * @param str : string element.
     */
    public void startWith(String str) {
        this.field = str + this.field ;
    }

    /**
     * End the clause with the
     * given string.
     *
     * @param str : string element.
     */
    public void endWith(String str) {
        this.value += str ;
    }

    /**
     * Set the connector.
     *
     * @param strConnector : clause connector.
     */
    public void setConnector(String strConnector) {
        Connector connector ;

        try {
            connector = Connector.valueOf(strConnector.trim()) ;
        } catch (Exception e) {
            connector = Connector.AND ;
        }

        this.connector = connector ;
    }

    /**
     * Set the clause value.
     *
     * @param value : value to set.
     */
    public void setValue(String value) {
        this.value = value ;
    }

    /**
     * Get the clause value.
     *
     * @return the value.
     */
    public String getValue() {
        return this.value ;
    }

    /**
     * Get the clause connector.
     *
     * @return the clause connector.
     */
    public String getConnector() {
        return this.connector.toString() ;
    }

    /**
     * Get the string clause version.
     *
     * @return clause as a string.
     */
    public String toString() {
        return (this.field + " " + this.operator + " " + this.value).trim() ;
    }

    /**
     * Format the given clauses.
     *
     * @param clauses : where clauses.
     * @return a SQL formatted string.
     */
    public static String format(ArrayList<WhereClause> clauses) {
        if(clauses.size() <= 0) {
            return "" ;
        }

        StringBuilder wheres = new StringBuilder() ;

        boolean first = true ;
        for(WhereClause clause : clauses) {
            String where = clause.toString() ;

            if(first) {
                first = false ;
            } else {
                where = " " + clause.getConnector() + " " + where ;
            }

            wheres.append(where);
        }

        return "WHERE " + wheres ;
    }

    public interface WhereCallable {
        SelectQuery handle(SelectQuery query) ;
    }

}
