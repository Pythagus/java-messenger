package fr.insa.messenger.network.utils;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author Damien MOLINA
 */
public class BroadcastSplitter {

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

}
