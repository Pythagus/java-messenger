package fr.insa.messenger.client.http;

import fr.insa.messenger.tools.Config;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.client.http.utils.Request;
import fr.insa.messenger.client.http.utils.RequestPost;

/**
 * @author Damien MOLINA
 */
public final class PresenceInterface {

    /**
     * Make a subscribe HTTP request.
     *
     * @return the HTTP response as a JSON object.
     */
    public static PresenceResponse subscribe() {
        RequestPost post = new RequestPost(PresenceInterface.getURL("subscribe")) ;
        post.formParameter("identifier", Env.getUser().getIdentifier()) ;

        return PresenceInterface.makeRequest(post) ;
    }

    /**
     * Make a publish HTTP request.
     *
     * @return the HTTP response as a JSON object.
     */
    public static PresenceResponse publish(UserStatus status) {
        RequestPost post = new RequestPost(PresenceInterface.getURL("publish")) ;
        post.formParameter("identifier", Env.getUser().getIdentifier()) ;
        post.formParameter("status", status.toString()) ;

        return PresenceInterface.makeRequest(post) ;
    }

    /**
     * Get the presence server URL.
     *
     * @param route : specific route.
     * @return URL.
     */
    private static String getURL(String route) {
        String uri = Config.get("PRESENCE_URL") ;

        if(uri == null || uri.trim().length() <= 0) {
            throw new PresenceServerException(route) ;
        }

        return uri.endsWith("/") ? uri + route : uri + "/" + route ;
    }

    /**
     * Execute the given request and prepare
     * a JSON response.
     *
     * @param request : HTTP request instance.
     * @return the request response.
     */
    private static PresenceResponse makeRequest(Request<?, ?> request) {
        try {
            return new PresenceResponse(
                request.execute().json()
            ) ;
        } catch (Exception e) {
            //e.printStackTrace() ;

            return new PresenceResponse(null) ;
        }
    }

}
