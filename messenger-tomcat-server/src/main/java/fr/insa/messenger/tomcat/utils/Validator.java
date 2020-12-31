package fr.insa.messenger.tomcat.utils;

/**
 * @author Damien MOLINA
 */
public class Validator {

    /**
     * Determine whether the given object is null.
     *
     * @param o : object to test.
     * @return True if o is null, false otherwise.
     */
    public static boolean isNull(Object o) {
        if(o == null) {
            return true ;
        }

        if(o instanceof String) {
            return ((String) o).trim().length() <= 0 ;
        }

        return false ;
    }

}
