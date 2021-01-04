package fr.insa.messenger.client.http.utils;

import org.apache.http.client.methods.HttpGet;

/**
 * @author Damien MOLINA
 */
public class RequestGet extends Request<RequestGet, HttpGet> {

    /**
     * Make a new POST request.
     *
     * @param url : request URL.
     */
    public RequestGet(String url) {
        super(url, new HttpGet()) ;
    }

}
