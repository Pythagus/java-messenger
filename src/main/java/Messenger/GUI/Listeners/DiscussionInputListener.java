package Messenger.GUI.Listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Listen for the input value changing.
 *
 * @author Damien MOLINA
 */
public class DiscussionInputListener implements DocumentListener {

    /**
     * Env listened input instance.
     */
    private final JTextField input ;

    /**
     * Make a new listener instance.
     *
     * @param input : input we are listen to.
     */
    public DiscussionInputListener(JTextField input) {
        this.input = input ;
    }

    public void changedUpdate(DocumentEvent e) {
        this.onInputChange() ;
    }
    public void removeUpdate(DocumentEvent e) {
        this.onInputChange() ;
    }
    public void insertUpdate(DocumentEvent e) {
        this.onInputChange() ;
    }

    /**
     * Called when the input text is changing.
     */
    private void onInputChange() {
        /*
         * 1 because after 1, the input doesn't
         * need to be repainted.
         */
        if(this.input.getText().length() <= 1) {
            this.input.repaint() ;
        }
    }

}
