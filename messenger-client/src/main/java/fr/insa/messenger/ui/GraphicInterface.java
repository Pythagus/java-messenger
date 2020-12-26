package fr.insa.messenger.ui;

import javax.swing.*;
import fr.insa.messenger.ui.frames.Frame;
import fr.insa.messenger.ui.frames.MainFrame;
import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.ui.screens.utils.BarType;
import fr.insa.messenger.ui.screens.DiscussionScreen;
import fr.insa.messenger.ui.screens.utils.ContentType;
import fr.insa.messenger.ui.screens.contacts.ContactBar;
import fr.insa.messenger.ui.screens.discussions.DiscussionBar;

/**
 * @author Damien MOLINA
 */
final public class GraphicInterface {

    /**
     * Current displayed frame.
     */
    private Frame frame ;

    /**
     * Singleton instance.
     */
    private static final GraphicInterface INSTANCE = new GraphicInterface() ;

    /**
     * Get the GraphicInterface singleton instance.
     *
     * @return the singleton instance.
     */
    public static GraphicInterface instance() {
        return GraphicInterface.INSTANCE ;
    }

    /**
     * Listener executed when a frame
     * is started.
     */
    private Runnable listener ;

    /**
     * Start the given frame.
     *
     * @param c : frame class.
     */
    public void start(Class<? extends Frame> c) {
        SwingUtilities.invokeLater(() -> {
            try {
                Frame frame = c.getDeclaredConstructor().newInstance() ;
                frame.render() ;

                if(GraphicInterface.this.frame != null) {
                    GraphicInterface.this.frame.dispose() ;
                }

                GraphicInterface.this.frame = frame ;

                if(GraphicInterface.this.listener != null) {
                    GraphicInterface.this.listener.run() ;
                    GraphicInterface.this.listener = null ;
                }
            } catch (Exception ignored) {
                Console.danger("Impossible to create the " + c.getName() + " instance") ;
            }
        }) ;
    }

    /**
     * Put a runnable to be executed when
     * the frame is rendered.
     *
     * @param runnable : runnable to execute.
     */
    public void notifyWhenRendered(Runnable runnable) {
        this.listener = runnable ;
    }

    /**
     * Get the main frame.
     *
     * @return main frame instance, null otherwise.
     */
    private MainFrame getMainFrame() {
        try {
            return (MainFrame) this.frame ;
        } catch (Exception ignored) {
            return null ;
        }
    }

    /**
     * Get the contact bar.
     *
     * @return the contact bar instance.
     */
    public ContactBar contactBar() {
        try {
            assert this.getMainFrame() != null ;
            return (ContactBar) this.getMainFrame().getBar(BarType.CONTACTS) ;
        } catch (Exception ignored) {
            return null ;
        }
    }

    /**
     * Get the discussion bar.
     *
     * @return the discussion bar instance.
     */
    public DiscussionBar discussionBar() {
        try {
            assert this.getMainFrame() != null ;
            return (DiscussionBar) this.getMainFrame().getBar(BarType.DISCUSSION) ;
        } catch (Exception ignored) {
            return null ;
        }
    }

    /**
     * Get the discussion screen.
     *
     * @return discussion screen instance, null otherwise.
     */
    public DiscussionScreen discussionScreen() {
        try {
            assert this.getMainFrame() != null ;
            return (DiscussionScreen) this.getMainFrame().getContent(ContentType.DISCUSSION) ;
        } catch (Exception ignored) {
            return null ;
        }
    }

}
