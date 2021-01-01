package fr.insa.messenger.client.http;

import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author Damien MOLINA
 */
abstract public class Request<T extends HttpRequestBase> {

    /**
     * URL parameters.
     */
    protected final ArrayList<BasicNameValuePair> urlParameters ;

    /**
     * Form parameters.
     */
    protected final ArrayList<BasicNameValuePair> formParameters ;

    /**
     * Request URL.
     */
    protected final String url ;

    /**
     * Request instance.
     */
    protected final T request ;

    /**
     * Make a new request.
     *
     * @param url : request URL.
     */
    public Request(String url, T request) {
        this.urlParameters  = new ArrayList<>() ;
        this.formParameters = new ArrayList<>() ;
        this.request        = request ;
        this.url            = url ;
    }

    /**
     * Add the given (name, value) tuple
     * in the URL parameters array.
     *
     * @param name : parameter name.
     * @param value : parameter value.
     */
    public void urlParameter(String name, String value) {
        this.urlParameters.add(new BasicNameValuePair(name, value)) ;
    }

    /**
     * Add the given (name, value) tuple
     * in the form parameters array.
     *
     * @param name : parameter name.
     * @param value : parameter value.
     */
    public void formParameter(String name, String value) {
        this.formParameters.add(new BasicNameValuePair(name, value)) ;
    }

    /**
     * If the request need specific preparing,
     * It could be made here.
     */
    protected void afterPreparingRequest() {}

    /**
     * Execute the request.
     *
     * @throws Exception : request error.
     */
    public void execute() throws Exception {
        this.prepare() ;

        try (
            CloseableHttpClient httpClient = HttpClientBuilder.create().build() ;
            CloseableHttpResponse response = httpClient.execute(this.request)
        ) {
            HttpEntity entity = response.getEntity();

            if(entity != null) {
                System.out.println(
                    EntityUtils.toString(response.getEntity())
                ) ;
            }
        }
    }

    /**
     * Prepare the request before executing.
     *
     * @throws Exception : prepare error.
     */
    private void prepare() throws Exception {
        URIBuilder builder = new URIBuilder(this.url) ;

        // Add the URL parameters.
        this.urlParameters.forEach(
            (BasicNameValuePair tuple) -> builder.setParameter(tuple.getName(), tuple.getValue())
        ) ;
        this.request.setURI(builder.build()) ;

        // Add the form parameters.
        this.afterPreparingRequest() ;
    }

}
