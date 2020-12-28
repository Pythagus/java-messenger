package fr.insa.messenger.tomcat.utils.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Damien MOLINA
 */
abstract public class PostServlet extends MyHttpServlet {

    /**
     * Make the current servlet a POST
     * servlet declaring the doPost
     * method.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        this.handleIncomingRequest(request, response) ;
    }

}
