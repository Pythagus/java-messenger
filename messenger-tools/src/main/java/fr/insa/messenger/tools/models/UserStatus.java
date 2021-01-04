package fr.insa.messenger.tools.models;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Damien MOLINA
 */
public enum UserStatus {

    /**
     * Status when the user is
     * connected to the application.
     */
    CONNECTED,

    /**
     * The user is not connected
     * to the application.
     */
    DISCONNECTED,

    /**
     * The user is connected to the
     * application, but he is idle.
     */
    IDLE ;

    /**
     * Get the translations of the current
     * enum type.
     *
     * @return associative list.
     */
    public static HashMap<String, String> translations() {
        HashMap<String, String> translations = new HashMap<>() ;
        translations.put(CONNECTED.toString(), "Connecté") ;
        translations.put(DISCONNECTED.toString(), "Déconnecté") ;
        translations.put(IDLE.toString(), "Absent") ;

        return translations ;
    }

    /**
     * Get all the possible user status.
     *
     * @return array of possible status.
     */
    public static String[] allKeys() {
        return UserStatus.translations().keySet().toArray(new String[] {}) ;
    }

    /**
     * Get all the possible user status.
     *
     * @return array of possible status.
     */
    public static String[] allTranslatedKeys() {
        return UserStatus.translations().values().toArray(new String[] {}) ;
    }

    /**
     * Get the translation of the given
     * UserStatus value.
     *
     * @return French status translation.
     */
    public static String translate(UserStatus status) {
        return UserStatus.translations().get(status.toString()) ;
    }

    /**
     * Get the UserStatus value of the
     * given French status.
     *
     * @return UserStatus translation.
     */
    public static UserStatus translate(String frStatus) {
        String status = UserStatus.translations().entrySet().stream()
            .filter(entry -> entry.getValue().equals(frStatus))
            .map(Map.Entry::getKey)
            .findFirst().orElse(null) ;

        if(status == null || status.length() <= 0) {
            return null ;
        }

        return UserStatus.valueOf(status) ;
    }

}
