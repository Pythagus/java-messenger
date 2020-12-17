package fr.insa.messenger.http;

import org.apache.http.client.methods.HttpGet;

/**
 * @author Damien MOLINA
 */
public class RequestGet extends Request<HttpGet> {

    /**
     * Make a new POST request.
     *
     * @param url : request URL.
     */
    public RequestGet(String url) {
        super(url, new HttpGet()) ;
    }

}
