package fr.insa.messenger.exceptions;

import fr.insa.messenger.system.Env;

/**
 * @author Damien MOLINA
 */
public class AppException extends Exception {

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public AppException(String msg) {
        super(msg) ;
    }

    /**
     * Get the exception message.
     *
     * @return the message.
     */
    public String getMessage() {
        if(this instanceof UserException) {
            return super.getMessage() ;
        }

        String appName = this.getAppName() ;

        if(appName.length() <= 0) {
            return super.getMessage() ;
        }

        return appName + " : " + super.getMessage() ;
    }

    /**
     * Try to get the application name.
     *
     * @return the application name or an empty string.
     */
    private String getAppName() {
        try {
            return Env.getApplication().getName() ;
        } catch (Throwable throwable) {
            return "" ;
        }
    }

}
