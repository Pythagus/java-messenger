package fr.insa.messenger.tools.database;

import java.util.ArrayList;
import fr.insa.messenger.tools.database.clauses.LimitClause;
import fr.insa.messenger.tools.database.clauses.WhereClause;

/**
 * @author Damien MOLINA
 */
public class DatabaseClauseManager {

    /**
     * Where clauses.
     */
    private final ArrayList<WhereClause> where ;

    /**
     * Limit clause.
     */
    private LimitClause limit ;

    /**
     * Make a clause manager instance.
     */
    public DatabaseClauseManager() {
        this.where = new ArrayList<>() ;
    }

    /**
     * Format the whole clauses.
     *
     * @return SQL syntax clauses.
     */
    public String formatClauses() {
        return WhereClause.format(this.where) +
            " " + LimitClause.format(this.limit) ;
    }

    /**
     * Add a where clause instance.
     *
     * @param clause : where clause.
     */
    public void where(WhereClause clause) {
        this.where.add(clause) ;
    }

    /**
     * Add a limit clause instance.
     *
     * @param clause : limit clause.
     */
    public void limit(LimitClause clause) {
        this.limit = clause ;
    }

    /**
     * Get the where clauses list.
     *
     * @return the clauses list.
     */
    public ArrayList<WhereClause> getWhereClauses() {
        return this.where ;
    }

}
