package fr.insa.messenger.client.models.messages;

import java.io.Serializable;

/**
 * Message content : string, file, etc.
 *
 * @author Damien MOLINA
 */
public class MessageData implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242410L ;

    /**
     * Message text.
     */
    private final String text ;

    /**
     * Sent file.
     */
    private final MessageFile file ;

    /**
     * Make a new MessageData instance.
     *
     * @param text : message text.
     * @param file : message file.
     */
    public MessageData(String text, MessageFile file) {
        this.text = text ;
        this.file = file ;
    }

    /**
     * Get the message text.
     *
     * @return the text message.
     */
    public String getText() {
        return this.text ;
    }

    /**
     * Get the message text.
     *
     * @return the text message.
     */
    public MessageFile getFile() {
        return this.file ;
    }

    /**
     * Determine whether the current data has
     * a sent file.
     *
     * @return True or False
     */
    public boolean hasFile() {
        return this.file != null ;
    }

    /**
     * Determine whether the current data has
     * a text.
     *
     * @return True or False
     */
    public boolean hasText() {
        return this.text != null ;
    }

    /**
     * Get the message to print.
     *
     * @return print message.
     */
    public String messageToPrint() {
        if(this.hasFile()) {
            return "Vous avez envoyé un fichier" ;
        }

        return this.getText() ;
    }

}
