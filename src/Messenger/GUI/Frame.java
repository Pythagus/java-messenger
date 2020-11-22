package Messenger.GUI;

import Messenger.GUI.Factories.GraphicImageFactory;
import Messenger.Foundation.Environment;
import Messenger.GUI.Layout.TitleBar;
import Messenger.GUI.Screens.Screen;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class Frame extends JFrame {

    /**
     * Main title bar instance.
     */
    private final JPanel titleBar ;

    /**
     * Current screen.
     */
    private Screen screen ;

    /**
     * Create a graphical instance
     * for the Messenger.
     *
     * @param screen : frame content.
     */
    public Frame(Screen screen) {
        this.initializeComponentGraphics() ;

        this.titleBar = new TitleBar(this) ;

        this.graphicContent(screen) ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
        this.setTitle(Environment.getApplication().getName()) ;
        this.setLocationByPlatform(true) ;
        this.setUndecorated(true) ;
        this.setIconImage(new ImageIcon(GraphicImageFactory.logo()).getImage()) ;
    }

    /**
     * Add the content to the frame.
     *
     * @param screen : JComponent to add.
     */
    private void graphicContent(Screen screen) {
        this.getContentPane().add(this.titleBar, BorderLayout.PAGE_START) ;
        this.addScreen(screen) ;
        this.pack() ;
    }

    /**
     * Replace the current screen by the
     * given one.
     *
     * @param screen : new screen to display.
     */
    public void replaceScreen(Screen screen) {
        Component[] list = this.getContentPane().getComponents() ;

        for(Component c : list) {
            if(c instanceof Screen) {
                this.remove(c) ;
            }
        }

        this.addScreen(screen) ;
        this.revalidate() ;
        this.repaint() ;
    }

    /**
     * Add the given screen.
     *
     * @param screen : screen to add.
     */
    private void addScreen(Screen screen) {
        this.screen = screen ;

        this.add(screen, BorderLayout.LINE_START) ;
    }

    /**
     * Get the current screen.
     *
     * @return the current screen.
     */
    public Screen getScreen() {
        return this.screen ;
    }

}
