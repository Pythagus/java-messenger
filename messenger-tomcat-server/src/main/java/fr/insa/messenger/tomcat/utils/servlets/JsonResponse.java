package fr.insa.messenger.tomcat.utils.servlets;

import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Damien MOLINA
 */
public class JsonResponse {

    /**
     * Response instance.
     */
    private final HttpServletResponse response ;

    /**
     * JSON object.
     */
    private final JSONObject json ;

    /**
     * Make a JsonResponse instance.
     *
     * @param response : http response instance.
     */
    public JsonResponse(HttpServletResponse response) {
        this.response = response ;
        this.json     = new JSONObject() ;
    }

    /**
     * Add a data to the JSON response.
     *
     * @param key : data key.
     * @param value : data value.
     * @return this.
     */
    public JsonResponse data(String key, Object value) {
        this.json.accumulate(key, value) ;

        return this ;
    }

    /**
     * Send the JSON response.
     *
     * @throws IOException : response input error.
     */
    public void send() throws IOException {
        // Prepare the response's type.
        this.response.setContentType("application/json") ;

        // Print the response.
        PrintWriter out = this.response.getWriter() ;
        out.print(json.toString()) ;
        out.flush() ;
    }

}
