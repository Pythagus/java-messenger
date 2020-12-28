package fr.insa.messenger.tomcat.utils.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Damien MOLINA
 */
abstract public class MyHttpServlet extends HttpServlet {

    /**
     * Writer instance.
     */
    protected PrintWriter writer ;

    /**
     * Handle the given request.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    abstract protected void handle(HttpServletRequest request, HttpServletResponse response) throws MyServletException ;

    /**
     * Handle an incoming request.
     *
     * @param request : incoming request.
     * @param response : generated response.
     * @throws MyServletException : handle error.
     */
    protected void handleIncomingRequest(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        this.setWriter(response) ;
        this.handle(request, response) ;
    }

    /**
     * Forward the request with the given jsp.
     *
     * @param jsp : JSP file path.
     * @param request : incoming request.
     * @param response : generated response.
     */
    protected void forwardJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        try {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/" + jsp).forward(request, response) ;
        } catch (ServletException | IOException e) {
            throw new MyServletException(e) ;
        }
    }

    /**
     * Set the writer instance.
     *
     * @param response : generated response.
     */
    protected void setWriter(HttpServletResponse response) {
        try {
            this.writer = response.getWriter() ;
        } catch (Exception ignored) {}
    }

}
