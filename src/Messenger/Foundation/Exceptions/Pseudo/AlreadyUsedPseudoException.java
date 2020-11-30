package Messenger.Foundation.Exceptions.Pseudo;

/**
 * @author Damien MOLINA
 */
public class AlreadyUsedPseudoException extends PseudoException {

    /**
     * Make a new Pseudo exception.
     * The pseudp
     */
    public AlreadyUsedPseudoException() {
        super("Le pseudo choisi est déjà utilisé par un autre utilisateur") ;
    }

}
