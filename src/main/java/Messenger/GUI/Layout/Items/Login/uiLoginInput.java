package Messenger.GUI.Layout.Items.Login;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Utils.Placeholder;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Listeners.DiscussionInputListener;
import Messenger.Foundation.Exceptions.Pseudo.PseudoException;
import Messenger.Foundation.Exceptions.Pseudo.AlreadyUsedPseudoException;

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
            System.out.println("Pseudo envoyé : " + pseudo) ;

            // TODO : retirer cette ligne. Ceci doit être fait en ayant la réponse du broadcast.
            Env.getUser().setPseudo(pseudo) ;

            try {
                // TODO : Maud -> vérifier si le pseudo n'est pas déjà pris. S'il l'est, générer l'exception AlreadyUsedPseudoException

                if(!pseudo.equals("André")) {
                    throw new AlreadyUsedPseudoException() ;
                }

                Env.getApplication().getGraphicFrame().replaceScreen(
                    new uiWindow()
                ) ;
            } catch(PseudoException exception) {
                // The selected pseudo is already used in the system.
                this.errorLabel.updateText(exception.getMessage()) ;
            }
        } else {
            this.errorLabel.updateText("Vous devez spécifier un pseudo !") ;
        }
    }

}
