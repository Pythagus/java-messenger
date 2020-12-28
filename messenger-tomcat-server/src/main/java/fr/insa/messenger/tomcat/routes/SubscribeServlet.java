package fr.insa.messenger.tomcat.routes;

import java.io.PrintWriter;
import org.json.JSONObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insa.messenger.tomcat.models.UserStatus;
import fr.insa.messenger.tomcat.utils.servlets.PostServlet;
import fr.insa.messenger.tomcat.utils.servlets.InternalException;

/**
 * @author Damien MOLINA
 */
@WebServlet("/subscribe")
public class SubscribeServlet extends PostServlet {

    /**
     * Handle the given request.
     *
     * @param request : incoming request.
     * @param response : generated response.
     */
    protected void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            String identifier = request.getParameter("identifier") ;
            String strStatus  = request.getParameter("status") ;
            UserStatus status ;

            /*
             * We try to cast the status. It could generate
             * a cast error. If It happens, then we generate
             * an internal error handled by the previous
             * try-catch block.
             */
            try {
                status = UserStatus.valueOf(strStatus) ;
            } catch (Exception e) {
                throw new InternalException("Unknown status " + strStatus) ;
            }

            if(identifier == null || identifier.trim().length() <= 0) {
                throw new InternalException("Null identifier") ;
            }

            this.managed(identifier, status) ;
        } catch (Exception e) {
            this.unmanaged(
                request, response, e instanceof InternalException ? e.getMessage() : "Internal server error"
            ) ;
        }
    }

    /**
     * Return a JSON error response.
     *
     * @param request : incoming request.
     * @param response : generated response.
     * @param error : error message.
     */
    private void unmanaged(HttpServletRequest request, HttpServletResponse response, String error) {
        response.setContentType("application/json") ;

        try {
            JSONObject json = new JSONObject() ;
            json.accumulate("code", 400) ;
            json.accumulate("error", error) ;

            PrintWriter out = response.getWriter();
            out.print(json.toString());
            out.flush();
        } catch (Exception e) {
            // TODO : to remove
            this.writer.println(e.getMessage()) ;
        }
    }

    /**
     * Manage the user new status.
     *
     * @param identifier : user identifier.
     * @param status : user new status.
     */
    private void managed(String identifier, UserStatus status) {
        switch(status) {
            case CONNECTED:
                this.writer.println("The user is connected") ;
                break ;
            case IDLE:
                this.writer.println("The user is idle") ;
                break ;
            case DISCONNECTED:
                this.writer.println("The user is disconnected") ;
                break ;
        }

        this.writer.println("identifier = " + identifier) ;
    }

}