package fr.insa.messenger.client.utils;

import java.io.File;

/**
 * @author Damien MOLINA
 */
final public class FileUtils {

    /**
     * Generate a new file name.
     *
     * @param name : original file name.
     * @return the generated file name.
     */
    public static String formatName(String name) {
        return "Messenger_" + DateUtils.format("dd-MM-yyyy_HH_mm_ss") + "_" + name.trim().replace(" ", "-") ;
    }

    /**
     * return the extension of a file.
     *
     * @param file we need the extension.
     * @return the extension of the file.
     */
    public static String extension(File file) {
        String fileName = file.getName() ;

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1).trim() ;
        }

        return null ;
    }

}
