package Messenger.gui.layout;

import Messenger.observers.listeners.DiscussionInputListener;
import Messenger.observers.listeners.SendDiscussionMessageListener;
import Messenger.observers.events.SendDiscussionMessageEvent;
import Messenger.managers.EventManager;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionInput extends JTextField {

    /**
     * Make a new instance of uiDiscussionInput.
     */
    uiDiscussionInput() {
        this.initializeComponentGraphics() ;

        EventManager.bind(SendDiscussionMessageEvent.class, new SendDiscussionMessageListener()) ;
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
            //TODO : send the message.
            EventManager.fire(new SendDiscussionMessageEvent(message)) ;

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

        if(this.getText().length() <= 0) {
            // Add a placeholder.
            final Graphics2D g = (Graphics2D) graphics ;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
            g.setColor(this.getDisabledTextColor()) ;
            g.drawString("Write a message...", getInsets().left, 3 * g.getFontMetrics().getMaxAscent() + getInsets().top/2) ;
        }
    }

}
