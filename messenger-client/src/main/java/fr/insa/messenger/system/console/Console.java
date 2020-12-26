package fr.insa.messenger.system.console;

import java.io.PrintStream;
import fr.insa.messenger.system.Env;

/**
 * @author Damien MOLINA
 */
public class Console implements ConsoleInterface {

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
        if(Env.getApplication().isDebugMode()) {
            Console.stream.println(style + msg + RESET) ;
        }
    }

    /**
     * Print a message with the given style.
     *
     * @param msg : message to print.
     * @param styles : message's styles.
     */
    public static void println(String msg, String[] styles) {
        StringBuilder style = new StringBuilder() ;

        for (String s : styles) {
            style.append(s) ;
        }

        Console.println(msg, style.toString()) ;
    }

}
