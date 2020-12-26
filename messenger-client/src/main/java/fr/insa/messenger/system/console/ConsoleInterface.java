package fr.insa.messenger.system.console;

/**
 * @author Damien MOLINA
 */
public interface ConsoleInterface {

    /**
     * Reset value.
     */
    String RESET = "\u001B[0m";

    /**
     * Text colors.
     */
    String TEXT_BLACK   = "\u001B[30m";
    String TEXT_RED     = "\u001B[31m";
    String TEXT_GREEN   = "\u001B[32m";
    String TEXT_YELLOW  = "\u001B[33m";
    String TEXT_BLUE    = "\u001B[34m";
    String TEXT_PURPLE  = "\u001B[35m";
    String TEXT_CYAN    = "\u001B[36m";
    String TEXT_WHITE   = "\u001B[37m";

    /**
     * Background colors.
     */
    String BG_BLACK  = "\u001B[40m";
    String BG_RED    = "\u001B[41m";
    String BG_GREEN  = "\u001B[42m";
    String BG_YELLOW = "\u001B[43m";
    String BG_BLUE   = "\u001B[44m";
    String BG_PURPLE = "\u001B[45m";
    String BG_CYAN   = "\u001B[46m";
    String BG_WHITE  = "\u001B[47m";

}
