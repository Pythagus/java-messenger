package fr.insa.messenger.ui.screens.discussions;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.utils.Cleaner;
import fr.insa.messenger.ui.utils.Placeholder;
import fr.insa.messenger.models.messages.Message;
import fr.insa.messenger.models.messages.MessageData;
import fr.insa.messenger.ui.screens.DiscussionScreen;
import fr.insa.messenger.observers.contracts.Listener;
import fr.insa.messenger.observers.contracts.Observable;
import fr.insa.messenger.observers.DiscussionInputListener;
import fr.insa.messenger.controllers.ConversationController;
import fr.insa.messenger.observers.contracts.ObserverProvider;

/**
 * @author Damien MOLINA
 */
public class DiscussionInput extends JTextField implements Observable {

    /**
     * Placeholder instance.
     */
    private final Placeholder placeholder ;

    /**
     * Observers provider.
     */
    private final ObserverProvider observer = new ObserverProvider() ;

    /**
     * Discussion screen instance.
     */
    private final DiscussionScreen screen ;

    /**
     * Make a new instance of DiscussionInput.
     */
    public DiscussionInput(DiscussionScreen screen) {
        this.screen = screen ;

        this.placeholder = new Placeholder(this) ;
        this.placeholder.setAlpha(2, 3, 2) ;

        this.initializeGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeGraphics() {
        this.setPreferredSize(new Dimension(50, 35)) ;
        this.addActionListener(Unused -> this.sendText()) ;
        this.getDocument().addDocumentListener(new DiscussionInputListener(this)) ;
    }

    /**
     * When a text has been sent.
     */
    public void sendText() {
        String text = Cleaner.clean(this.getText()) ;

        if(ConversationController.instance().isValidText(text)) {
            this.setText(null) ;

            // TODO : add file.
            this.observer.notifyAllListeners(this.screen.getConversation(),
                new Message(this.screen.getConversation().getTarget(), new MessageData(text, null))
            ) ;
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
        if(this.placeholder.shouldDraw()) {
            this.placeholder.setGraphic(graphics) ;
            this.placeholder.draw("Écrivez un message...") ;
        }
    }

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    public void addListener(Listener listener) {
        this.observer.addListener(listener) ;
    }

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    public void removeListener(Listener listener) {
        this.observer.removeListener(listener) ;
    }

}