package Messenger.Foundation;

import Messenger.Foundation.Contracts.ApplicationContract;
import Messenger.Foundation.System.Console.Console;
import Messenger.GUI.Screens.Screen;
import Messenger.GUI.GraphicThread;
import Messenger.GUI.Frame;
import javax.swing.*;

/**
 * @author Damien MOLINA
 */
abstract public class Application implements ApplicationContract {

    /**
     * Available application modes.
     */
    public enum ApplicationMode {
        DEBUG, PRODUCTION
    }

    /**
     * Environment application mode.
     */
    protected final ApplicationMode mode ;

    /**
     * Graphic application thread.
     */
    protected GraphicThread graphicThread ;

    /**
     * Get the screen instance displayed when
     * the application is started.
     *
     * @return a Screen instance.
     */
    abstract protected Screen getStartingScreen() ;

    /**
     * Make a new instance of the application.
     */
    public Application(ApplicationMode mode) {
        this.mode = mode ;

        if(this.isDebugMode()) {
            this.printConsoleIntro() ;
        }

        Environment.setApplication(this) ;
    }

    /**
     * Print the console introduction.
     */
    private void printConsoleIntro() {
        Console.comment("/========== " + this.getName() + " ==========\\") ;
        Console.comment("/= Version " + this.getVersion()) ;
        Console.comment("/=") ;
        Console.comment("/= Developed by Maud Pennetier and Damien Molina") ;
    }

    /**
     * Get the current application's mode.
     *
     * @return the application mode.
     */
    public ApplicationMode getMode() {
        return this.mode ;
    }

    /**
     * Determine whether the application is in
     * debug mode.
     *
     * @return True if It is, False otherwise.
     */
    public boolean isDebugMode() {
        return this.mode.equals(ApplicationMode.DEBUG) ;
    }

    /**
     * Start the application instance.
     */
    public void start() {
        // Start the graphic components.
        SwingUtilities.invokeLater(() -> {
            Application.this.graphicThread = new GraphicThread() ;
            Application.this.graphicThread.setFrameScreen(
                    Application.this.getStartingScreen()
            ) ;
            Application.this.graphicThread.start() ;
        }) ;
    }

    /**
     * Get the graphic frame instance.
     *
     * @return Frame instance.
     */
    public Frame getGraphicFrame() {
        return this.graphicThread.getFrame() ;
    }

}
