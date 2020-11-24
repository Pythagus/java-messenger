package Messenger.GUI.Layout.Items;

import java.awt.*;
import javax.swing.*;
import Messenger.Foundation.Providers.ObserverProvider;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.Foundation.Observers.Contracts.Listener;
import Messenger.Foundation.Observers.Contracts.Observable;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionInput extends JTextField implements Observable {

    /**
     * Observers provider.
     */
    private final ObserverProvider observer = new ObserverProvider() ;

    /**
     * Make a new instance of uiDiscussionInput.
     */
    uiDiscussionInput() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setBackground(uiDiscussion.backgroundColor) ;
        this.setBorder(BorderFactory.createEmptyBorder(5,20,5,10)) ;
        this.setPreferredSize(new Dimension(50, 35)) ;
        this.addActionListener(Unused -> this.sendInputContent()) ;
        this.getDocument().addDocumentListener(new DiscussionInputListener(this)) ;
    }

    /**
     * Send the input content.
     */
    public void sendInputContent() {
        String message = this.getText() ;

        if(message.length() > 0) {
            this.observer.notifyAllListeners(message) ;
            this.setText(null) ;
        }
    }

    /**
     * Override the painting method.
     *
     * @param graphics : graphic instance.
     */
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics) ;

        /*
         * Add a placeholder helper only whether
         * the input has no characters.
         */
        if(this.getText().length() <= 0) {
            final Graphics2D g = (Graphics2D) graphics ;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
            g.setColor(this.getDisabledTextColor()) ;
            g.drawString("Write a message...", getInsets().left, 3 * g.getFontMetrics().getMaxAscent() + getInsets().top/2) ;
        }
    }

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    @Override
    public void addListener(Listener listener) {
        this.observer.addListener(listener) ;
    }

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    @Override
    public void removeListener(Listener listener) {
        this.observer.removeListener(listener) ;
    }

}
