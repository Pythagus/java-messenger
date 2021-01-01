package fr.insa.messenger.client.http;

import org.json.JSONObject;

/**
 * @author Damien MOLINA
 */
public class PresenceResponse {

    /**
     * JSON response.
     */
    private final JSONObject json ;

    /**
     * Make a new presence response instance.
     *
     * @param json : presence HTTP response.
     */
    public PresenceResponse(JSONObject json) {
        this.json = json ;
    }

    /**
     * Determine whether the response
     * is a success one.
     *
     * @return True or False.
     */
    public boolean isSuccessful() {
        Object error = this.get("error") ;

        return error != null && ((int) error == 0) ;
    }

    /**
     * Get the value identified by the
     * given key.
     *
     * @param key : JSON key.
     * @return the value.
     */
    public Object get(String key) {
        try {
            return this.json.get(key) ;
        } catch (RuntimeException ignored) {
            return null ;
        }
    }

}
