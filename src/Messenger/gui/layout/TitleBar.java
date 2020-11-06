package Messenger.gui.layout;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.function.Consumer;
import java.awt.event.MouseMotionAdapter;
import Messenger.gui.utils.GraphicImage;

/**
 * Main navigation bar with minimize, maximize,
 * close buttons and the frame title.
 *
 * @author Damien MOLINA
 */
public class TitleBar extends JPanel {

    // Main title bar color.
    public static final Color backgroundColor = new Color(50, 27, 140) ;

    /**
     * Frame containing this titleBar.
     * Used to move the window.
     */
    private final JFrame frame ;

    /**
     * Window position.
     */
    private final Point position ;

    /**
     * Make a new instance of the title bar.
     *
     * @param frame : current frame.
     */
    public TitleBar(JFrame frame) {
        this.frame    = frame ;
        this.position = new Point() ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        // Set the background color.
        this.setBackground(TitleBar.backgroundColor) ;

        // Set the dimensions.
        this.setPreferredSize(new Dimension(1024, 30)) ;

        // Add the move-window event listener.
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                listenerBarDragged(evt) ;
            }
        }) ;

        // Add the click event listener.
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                listenerBarClicked(evt);
            }
            public void mousePressed(MouseEvent evt) {
                listenerBarPressed(evt);
            }
        }) ;

        // Set a new layout.
        this.setLayout(new BorderLayout()) ;

        // Add the actions panel.
        this.add(this.graphicActions(), BorderLayout.LINE_END) ;

        // Add the title panel.
        this.add(this.graphicTitle(), BorderLayout.LINE_START) ;
    }

    /**
     * Make the graphic title JPanel.
     *
     * @return the JPanel created instance.
     */
    private JPanel graphicTitle() {
        JPanel title = new JPanel() ;
        title.setBackground(null) ;
        title.setPreferredSize(new Dimension(200, 30)) ;
        title.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8)) ;

        // Generate the title label.
        JLabel label = new JLabel() ;
        label.setForeground(new Color(204, 204, 204)) ;
        label.setText(this.frame.getTitle()) ;

        title.add(label) ;

        return title ;
    }

    /**
     * Make the action bar JPanel instance.
     *
     * @return the JPanel created instance.
     */
    private JPanel graphicActions() {
        JPanel actions = new JPanel() ;
        actions.setBackground(null) ;
        actions.setLayout(new FlowLayout(FlowLayout.RIGHT)) ;

        // Add the buttons.
        actions.add(this.graphicMinimizeButton()) ;
        actions.add(this.graphicMaximizeButton()) ;
        actions.add(this.graphicCloseButton()) ;

        return actions ;
    }

    /**
     * Make the close button instance.
     *
     * @return the JLabel created instance.
     */
    private JLabel graphicCloseButton() {
        return this.graphicButton("close.png", this::listenerClosePressed) ;
    }

    /**
     * Make the minimize button instance.
     *
     * @return the JLabel created instance.
     */
    private JLabel graphicMinimizeButton() {
        return this.graphicButton("minify.png", this::listenerMinimizePressed) ;
    }

    /**
     * Make the maximize button instance.
     *
     * @return the JLabel created instance.
     */
    private JLabel graphicMaximizeButton() {
        return this.graphicButton("maximize.png", this::listenerMaximizePressed) ;
    }

    /**
     * Generate a button instance.
     *
     * @param file : file path.
     * @param callable : callable ran when the button is clicked.
     * @return the JLabel created instance.
     */
    private JLabel graphicButton(String file, Consumer<MouseEvent> callable) {
        JLabel button = new JLabel();

        button.setIcon(
                GraphicImage.asImageIcon("titlebar/" + file)
        ) ;
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                callable.accept(evt) ;
            }
        }) ;

        return button ;
    }

    /**
     * Resize the frame regarding its current
     * size.
     */
    private void resizeFrame() {
        // if the frame is already to its maximum size.
        if (this.frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            this.frame.setExtendedState(JFrame.NORMAL) ;
        } else {
            this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH) ;
        }
    }

    /**
     * Executed when the frame is dragged
     * to be moved.
     *
     * @param evt : moving event.
     */
    private void listenerBarDragged(MouseEvent evt) {
        if (this.frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            this.frame.setExtendedState(JFrame.NORMAL) ;
        }

        this.frame.setLocation(
                evt.getXOnScreen() - this.position.x, evt.getYOnScreen() - this.position.y
        ) ;
    }

    /**
     * Executed when the frame is clicked to
     * resize the page when there's a double-click.
     *
     * @param evt : click event.
     */
    private void listenerBarClicked(MouseEvent evt) {
        // If it is a double click.
        if(evt.getClickCount() == 2 && ! evt.isConsumed()) {
            this.resizeFrame() ;
        }
    }

    /**
     * Executed when the frame has been pressed
     * to save the current location of the frame.
     *
     * @param evt : click event.
     */
    private void listenerBarPressed(MouseEvent evt) {
        this.position.setLocation(evt.getX(), evt.getY()) ;
    }

    /**
     * Executed when the maximize bar button is pressed.
     *
     * @param evt : press event.
     */
    private void listenerMaximizePressed(MouseEvent evt) {
        this.resizeFrame() ;
    }

    /**
     * Executed when the minimize bar button is pressed.
     *
     * @param evt : press event.
     */
    private void listenerMinimizePressed(MouseEvent evt) {
        this.frame.setState(Frame.ICONIFIED) ;
    }

    /**
     * Executed when the close bar button is pressed.
     *
     * @param evt : press event.
     */
    private void listenerClosePressed(MouseEvent evt) {
        System.exit(0) ;
    }

}
