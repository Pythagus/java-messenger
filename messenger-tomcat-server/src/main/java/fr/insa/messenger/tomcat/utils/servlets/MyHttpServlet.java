package fr.insa.messenger.tomcat.utils.servlets;

import java.io.IOException;
import fr.insa.messenger.tools.Config;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Damien MOLINA
 */
abstract public class MyHttpServlet extends HttpServlet {

    /**
     * Make a Servlet instance.
     */
    public MyHttpServlet() {
        try {
            String root = this.getClass().getResource("/").toString();
            root = root.substring("file:".length()) ;

            Config.setRoot(root) ;
        } catch (Exception ignored) {}
    }

    /**
     * Handle the given request.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    abstract protected void handle(HttpServletRequest request, HttpServletResponse response) throws MyServletException ;

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
     * Make a JSON response.
     *
     * @param response : HTTP response instance.
     * @return the JSON response.
     */
    protected JsonResponse json(HttpServletResponse response) {
        return new JsonResponse(response) ;
    }

    /**
     * Make a JSON response.
     *
     * @param response : HTTP response instance.
     * @param code : error code.
     * @param message : error message.
     * @return the JSON response.
     */
    protected JsonResponse jsonError(HttpServletResponse response, int code, String message) {
        return this.json(response).data("error", code).data("message", message) ;
    }

    /**
     * Make a JSON response.
     *
     * @param response : HTTP response instance.
     * @return the JSON response.
     */
    protected JsonResponse jsonSuccess(HttpServletResponse response) {
        return this.json(response).data("error", 0) ;
    }

    /**
     * Get the user's ip address.
     *
     * @param request : HTTP request.
     * @return the user's ip address.
     */
    protected String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if(ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip ;
    }

}
