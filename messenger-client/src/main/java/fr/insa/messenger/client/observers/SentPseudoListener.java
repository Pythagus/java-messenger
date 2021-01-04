package fr.insa.messenger.client.observers;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.ui.frames.LoginFrame;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.client.exceptions.PseudoException;
import fr.insa.messenger.client.observers.contracts.Listener;

/**
 * @author Damien MOLINA
 */
public class SentPseudoListener implements Listener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    public void handle(Object... args) {
        LoginFrame frame = (LoginFrame) args[0] ;
        String pseudo = (String) args[1] ;

        this.managePseudo(frame, pseudo) ;
    }


    /**
     * Manage the given pseudo.
     *
     * @param pseudo : chosen pseudo.
     */
    protected void managePseudo(Component component, String pseudo) {
        try {
            pseudo = this.checkPseudo(pseudo) ;

            this.manageCheckedPseudo(pseudo) ;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                component, e instanceof PseudoException ? e.getMessage() : "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE
            ) ;
        }
    }

    /**
     * Manage the pseudo after checking its validity.
     *
     * @param pseudo : valid pseudo.
     */
    protected void manageCheckedPseudo(String pseudo) {
        if(Env.getUser().getPseudo() == null) {
            this.connect(pseudo) ;
        } else {
            Console.warning("The user already chose a pseudo") ;
        }
    }


    /**
     * Check the given pseudo and return
     * it if it is valid.
     *
     * @param p : pseudo to check.
     * @return the given formatted pseudo.
     * @throws PseudoException : invalid pseudo.
     */
    private String checkPseudo(String p) throws PseudoException {
        String pseudo = p.trim() ;

        if(pseudo.length() > 0) {
            UserController.instance().checkPseudo(pseudo) ;

            return pseudo ;
        }

        throw new PseudoException("Vous devez spécifier un pseudo") ;
    }

    /**
     * Connect the current user
     * with the given pseudo.
     *
     * @param pseudo : chosen pseudo.
     */
    private void connect(String pseudo) {
        // Set the pseudo.
        Env.getUser().setPseudo(pseudo) ;

        // Start the main frame.
        Env.getApplication().getStarter().startLoggedIn() ;
    }

}
