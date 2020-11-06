package Messenger.utils;

import java.io.PrintStream;

/**
 * @author Damien MOLINA
 */
public class Console {

    /**
     * Reset value.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Text colors.
     */
    public static final String TEXT_BLACK   = "\u001B[30m";
    public static final String TEXT_RED     = "\u001B[31m";
    public static final String TEXT_GREEN   = "\u001B[32m";
    public static final String TEXT_YELLOW  = "\u001B[33m";
    public static final String TEXT_BLUE    = "\u001B[34m";
    public static final String TEXT_PURPLE  = "\u001B[35m";
    public static final String TEXT_CYAN    = "\u001B[36m";
    public static final String TEXT_WHITE   = "\u001B[37m";

    /**
     * Background colors.
     */
    public static final String BG_BLACK  = "\u001B[40m";
    public static final String BG_RED    = "\u001B[41m";
    public static final String BG_GREEN  = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE   = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN   = "\u001B[46m";
    public static final String BG_WHITE  = "\u001B[47m";

    /**
     * Stream to print in.
     */
    private static final PrintStream stream = System.out ;

    /**
     * Make a green text.
     *
     * @param msg : message to print.
     */
    public static void comment(String msg) {
        Console.println(msg, TEXT_GREEN) ;
    }

    /**
     * Make a red text.
     *
     * @param msg : message to print.
     */
    public static void danger(String msg) {
        Console.println(msg, TEXT_RED) ;
    }

    /**
     * Make a yellow text.
     *
     * @param msg : message to print.
     */
    public static void warning(String msg) {
        Console.println(msg, TEXT_YELLOW) ;
    }

    /**
     * Print a message with the given style.
     *
     * @param msg : message to print.
     * @param style : message's style.
     */
    public static void println(String msg, String style) {
        Console.stream.println(style + msg + RESET) ;
    }

    /**
     * Print a message with the given style.
     *
     * @param msg : message to print.
     * @param styles : message's styles.
     */
    public static void println(String msg, String[] styles) {
        StringBuilder style = new StringBuilder();

        for (String s : styles) {
            style.append(s);
        }

        Console.println(msg, style.toString()) ;
    }

}
