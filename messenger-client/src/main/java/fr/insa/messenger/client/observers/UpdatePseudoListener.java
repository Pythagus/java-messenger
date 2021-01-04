package fr.insa.messenger.client.observers;

import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.ui.screens.SettingsScreen;
import fr.insa.messenger.client.network.models.basis.BroadcastType;

/**
 * @author Damien MOLINA
 */
public class UpdatePseudoListener extends SentPseudoListener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    public void handle(Object... args) {
        SettingsScreen screen = (SettingsScreen) args[0] ;
        String pseudo = (String) args[1] ;

        this.managePseudo(screen, pseudo) ;
    }

    /**
     * Manage the pseudo after checking its validity.
     *
     * @param pseudo : valid pseudo.
     */
    protected void manageCheckedPseudo(String pseudo) {
        if(! Env.getUser().getPseudo().equals(pseudo)) {
            this.change(pseudo) ;
        } else {
            Console.warning("The user already has this pseudo") ;
        }
    }

    /**
     * Update the current user's pseudo.
     *
     * @param pseudo : new user's pseudo.
     */
    private void change(String pseudo) {
        // Set the pseudo.
        Env.getUser().setPseudo(pseudo) ;

        // Send the broadcast notification.
        NetworkInterface.instance().getEnvoyer().broadcast(BroadcastType.CHANGED_PSEUDO) ;
    }

}
