package fr.insa.messenger.tomcat.utils.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Damien MOLINA
 */
abstract public class GetServlet extends MyHttpServlet {

    /**
     * Make the current servlet a GET
     * servlet declaring the doGet
     * method.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        this.setWriter(response) ;
        this.handle(request, response) ;
    }

}
