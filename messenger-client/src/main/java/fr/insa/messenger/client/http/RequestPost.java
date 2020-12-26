package fr.insa.messenger.client.http;

import org.apache.http.Consts;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 * @author Damien MOLINA
 */
public class RequestPost extends Request<HttpPost> {

    /**
     * Make a new POST request.
     *
     * @param url : request URL.
     */
    public RequestPost(String url) {
        super(url, new HttpPost()) ;
    }

    /**
     * If the request need specific preparing,
     * It could be made here.
     */
    protected void afterPreparingRequest() {
        // Add the form parameters.
        this.request.setEntity(
            new UrlEncodedFormEntity(this.formParameters, Consts.UTF_8)
        ) ;
    }

}
