package Messenger.GUI.Layout.Items.Login;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Utils.Placeholder;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.Foundation.Exceptions.PseudoException;
import Messenger.GUI.Layout.Items.Contact.uiContactBar;
import Messenger.GUI.Layout.Items.Contact.uiContactItem;
import Messenger.Network.Models.Broadcast.BroadcastType;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

/**
 * @author Damien MOLINA
 */
public class uiLoginInput extends JTextField {

    /**
     * Placeholder instance.
     */
    private final Placeholder placeholder ;

    /**
     * Screen component.
     */
    private final uiLoginError errorLabel ;

    /**
     * Make a new login input instance.
     *
     * @param errorLabel : error label instance.
     */
    public uiLoginInput(uiLoginError errorLabel) {
        this.errorLabel = errorLabel ;

        this.placeholder = new Placeholder(this) ;
        this.placeholder.setAlpha(2, 0, 1) ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        this.setHorizontalAlignment(JTextField.CENTER) ;
        this.setMinimumSize(new Dimension(200, 50)) ;
        this.setPreferredSize(new Dimension(250, 35)) ;
        this.addActionListener(Unused -> this.sendForm()) ;
        this.getDocument().addDocumentListener(
            new DiscussionInputListener(this, () -> uiLoginInput.this.errorLabel.updateText(""))
        ) ;
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
            this.placeholder.draw("Votre pseudo") ;
        }
    }

    /**
     * Send the input content.
     */
    public void sendForm() {
        String pseudo = this.getText() ;

        if(pseudo.length() > 0) {
            this.errorLabel.updateText("") ;

            try {
                Env.userController().checkPseudo(pseudo) ;

                this.loggedIn(pseudo) ;
            } catch (PseudoException e) {
                this.errorLabel.updateText(e.getMessage()) ;
            }
        } else {
            this.errorLabel.updateText("Vous devez spécifier un pseudo !") ;
        }
    }

    /**
     * Executed when the user is logged in
     * with a valid pseudo.
     *
     * @param pseudo : taken pseudo.
     */
    private void loggedIn(String pseudo) {
        // Set the pseudo.
        Env.getUser().setPseudo(pseudo) ;

        // Send the broadcast notification.
        Env.getNetworkInterface().getEnvoyer().broadcast(
            new BroadcastNotification(BroadcastType.LOGIN)
        ) ;

        // Set the new screen.
        Env.getApplication().getGraphicFrame().replaceScreen(new uiWindow());

        // Add the data into the screen.
        uiWindow window  = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
        uiContactBar bar = (uiContactBar) window.getVerticalBar(VerticalBarType.CONTACT) ;

        for(User user : Env.userController().getUsers()) {
            bar.addItem(new uiContactItem(user)) ;
        }
    }

}
