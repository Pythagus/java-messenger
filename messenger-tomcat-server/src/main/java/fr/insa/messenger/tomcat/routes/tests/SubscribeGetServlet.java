package fr.insa.messenger.tomcat.routes.tests;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insa.messenger.tomcat.utils.servlets.GetServlet;
import fr.insa.messenger.tomcat.utils.servlets.MyServletException;
import java.io.IOException;
import java.io.PrintWriter;

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
        /*try {
            PrintWriter out = response.getWriter();
            this.print(out, this.getServletContext().getClass().getResource("/")) ;
            this.print(out, this.getClass().getResource("/")) ;
            this.print(out, this.getClass().getResource(".properties")) ;
            this.print(out, this.getClass().getResource("/.properties")) ;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
*/
        this.forwardJSP("subscribe.jsp", request, response) ;
    }

    private void print(PrintWriter out, Object o) {
        out.println(o) ;
        out.flush() ;
    }

}