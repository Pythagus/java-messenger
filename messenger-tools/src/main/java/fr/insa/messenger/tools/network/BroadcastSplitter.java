package fr.insa.messenger.tools.network;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author Damien MOLINA
 */
final public class BroadcastSplitter {

    /**
     * String delimiter.
     */
    public static final String DELIMITER = "#" ;

    /**
     * Split the given broadcast response.
     *
     * @param str : broadcast response.
     * @return parameters array.
     */
    public static ArrayList<String> split(String str) {
        String[] data = str.trim().split(BroadcastSplitter.DELIMITER) ;

        ArrayList<String> arr = new ArrayList<>(
            Arrays.asList(data)
        ) ;
        arr.removeIf(
            item -> item == null || "".equals(item)
        ) ;

        return arr ;
    }

    /**
     * Join the given string array into one single
     * string.
     *
     * @param str : strings to join together.
     * @return a joined string.
     */
    public static String join(String... str) {
        return BroadcastSplitter.DELIMITER +
            String.join(BroadcastSplitter.DELIMITER, str) +
            BroadcastSplitter.DELIMITER ;
    }

}
