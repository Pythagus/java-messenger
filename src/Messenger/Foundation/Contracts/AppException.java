package Messenger.Foundation.Contracts;

import Messenger.Foundation.Environment;

/**
 * @author Damien MOLINA
 */
public class AppException extends Exception {

    /**
     * Get the exception message.
     *
     * @return the message.
     */
    public String getMessage() {
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
            return Environment.getApplication().getName() ;
        } catch (Throwable throwable) {
            return "" ;
        }
    }

}
