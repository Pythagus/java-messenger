package Messenger.GUI.Layout.Items.Login;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Utils.Placeholder;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
import Messenger.Network.Models.Broadcast.BroadcastType;

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

            UserController controller = (UserController) Env.getController(UserController.class) ;

            if(controller.availablePseudo(pseudo) == 1 )
            {
                Env.getUser().setPseudo(pseudo);
                Env.getNetworkInterface().getEnvoyer().broadcast(
                        new BroadcastNotification(BroadcastType.LOGIN)
                );
                Env.getApplication().getGraphicFrame().replaceScreen(new uiWindow());
            } else if (controller.availablePseudo(pseudo) == 2 ){
                this.errorLabel.updateText("Le pseudo choisi contient des caractères interdits, seuls les lettres et les chiffres sont autorisées") ;
            } else {
                this.errorLabel.updateText("Le pseudo choisi est déjà utilisé par un autre utilisateur") ;
            }
        } else {
            this.errorLabel.updateText("Vous devez spécifier un pseudo !") ;
        }
    }

}
