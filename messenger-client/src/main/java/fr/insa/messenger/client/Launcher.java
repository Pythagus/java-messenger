package fr.insa.messenger.client;

/**
 * Application launcher.
 *
 * @author Damien MOLINA
 */
public class Launcher {

    /**
     * Start the application.
     *
     * @param args : command line arguments.
     */
    public static void main(String[] args) {
        /*try {
            RequestPost post = new RequestPost("https://httpbin.org/post") ;
            post.formParameter("username", "abc") ;
            post.formParameter("password", "123") ;
            post.execute() ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }

        try {
            RequestGet get = new RequestGet("https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java") ;
            get.execute() ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }*/

        Application messenger = new Application() ;
        messenger.start() ;
    }

}
