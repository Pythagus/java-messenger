package Messenger.GUI.Layout.Items.Discussion;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Screens.uiWindow;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Utils.Placeholder;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.Foundation.Providers.ObserverProvider;
import Messenger.Foundation.Models.Messages.MessageFile;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Foundation.Observers.Contracts.Listener;
import Messenger.Foundation.Observers.Contracts.Observable;
import Messenger.Foundation.Controllers.ConversationController;
import Messenger.GUI.Exceptions.ConversationItemNotFoundException;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionInput extends JTextField implements Observable {

    /**
     * Observers provider.
     */
    private final ObserverProvider observer = new ObserverProvider() ;

    /**
     * Placeholder instance.
     */
    private final Placeholder placeholder ;

    /**
     * Make a new instance of uiDiscussionInput.
     */
    uiDiscussionInput() {
        this.placeholder = new Placeholder(this) ;
        this.placeholder.setAlpha(3, 2, 1) ;

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
        MessageFile file = null ; // TODO : regarder si un fichier a été envoyé
        String text      = this.getText().trim() ;

        // Conversation controller instance.
        ConversationController controller = (ConversationController) Env.getController(ConversationController.class) ;

        if(controller.isValidTest(text)) { /* && (file == null)*/
            uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;

            try {
                User target = uiWindow.getDiscussionBar().getActiveItem().getConversation().getTarget() ;

                Message message = new Message(target, new MessageData(text, file)) ;

                this.observer.notifyAllListeners(message) ;

                this.setText(null) ;
            } catch (ConversationItemNotFoundException e) {
                e.printStackTrace();
            }
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
