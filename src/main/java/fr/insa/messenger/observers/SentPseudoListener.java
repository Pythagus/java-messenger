package fr.insa.messenger.observers;

import javax.swing.*;
import fr.insa.messenger.system.Env;
import fr.insa.messenger.ui.frames.MainFrame;
import fr.insa.messenger.ui.frames.LoginFrame;
import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.network.NetworkInterface;
import fr.insa.messenger.controllers.UserController;
import fr.insa.messenger.exceptions.PseudoException;
import fr.insa.messenger.observers.contracts.Listener;
import fr.insa.messenger.network.models.BroadcastPacket;
import fr.insa.messenger.network.models.basis.BroadcastType;

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

        try {
            pseudo = this.checkedPseudo(pseudo) ;

            if(Env.getUser().getPseudo() == null) {
                this.connect(pseudo) ;
            } else {
                Console.warning("The user already chose a pseudo") ;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                frame, e instanceof PseudoException ? e.getMessage() : "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE
            ) ;
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
    private String checkedPseudo(String p) throws PseudoException {
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

        // Send the broadcast notification.
        NetworkInterface.instance().getEnvoyer().broadcast(
            new BroadcastPacket(BroadcastType.LOGIN)
        ) ;

        // Start the main frame.
        MainFrame.start() ;
    }

}
