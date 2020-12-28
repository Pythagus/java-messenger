package fr.insa.messenger.client.database.clauses;

/**
 * @author Damien MOLINA
 */
public class LimitClause extends DatabaseClause {

    /**
     * Field potential value.
     */
    private final Integer value ;

    /**
     * Make a limit clause instance.
     *
     * @param value : field potential value.
     */
    public LimitClause(Integer value) {
        this.value = value ;
    }

    /**
     * Get the string clause version.
     *
     * @return clause as a string.
     */
    public String toString() {
        return this.value.toString() ;
    }

    /**
     * Format the given clauses.
     *
     * @param clause : limit clause.
     * @return a SQL formatted string.
     */
    public static String format(LimitClause clause) {
        if(clause == null) {
            return "" ;
        }

        return "LIMIT " + clause.toString() ;
    }

}
