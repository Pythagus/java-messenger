package Messenger.GUI.Layout.Items;

import Messenger.Foundation.Observers.Listeners.ManageDiscussionMessageSent;
import Messenger.Foundation.Observers.Listeners.SendDiscussionMessage;
import Messenger.Foundation.Observers.Events.DiscussionMessageSent;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.GUI.Events.DiscussionMessageEntered;
import Messenger.Foundation.Managers.EventManager;
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

        EventManager.bind(DiscussionMessageEntered.class, new SendDiscussionMessage()) ;
        EventManager.bind(DiscussionMessageSent.class, new ManageDiscussionMessageSent()) ;
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
            EventManager.fire(new DiscussionMessageEntered(message)) ;

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

}
