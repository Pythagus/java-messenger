package fr.insa.messenger.tomcat.routes.tests;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insa.messenger.tomcat.utils.servlets.GetServlet;
import fr.insa.messenger.tomcat.utils.servlets.MyServletException;

/**
 * @author Damien MOLINA
 */
@WebServlet("/subscribe-get")
public class SubscribeGetServlet extends GetServlet {

    /**
     * Handle the given request.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        this.forwardJSP("subscribe.jsp", request, response) ;
    }

}