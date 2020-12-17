package fr.insa.messenger.utils;

/**
 * @author Damien MOLINA
 */
public class Cleaner {

    /**
     * Clean the given text.
     *
     * @param text : text to clean.
     * @return the clean text.
     */
    public static String clean(String text) {
        // TODO : add injection tests.
        return text.trim() ;
    }

}
