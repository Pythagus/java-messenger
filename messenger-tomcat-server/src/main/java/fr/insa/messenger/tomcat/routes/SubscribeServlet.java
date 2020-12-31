package fr.insa.messenger.tomcat.routes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insa.messenger.tomcat.utils.Validator;
import fr.insa.messenger.tomcat.utils.ValidatedInput;
import fr.insa.messenger.tomcat.controllers.UserController;
import fr.insa.messenger.tomcat.utils.servlets.PostServlet;
import fr.insa.messenger.tomcat.exceptions.InternalException;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;
import fr.insa.messenger.tomcat.exceptions.AlreadyExistUserException;

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
            ValidatedInput data = this.validateInput(request) ;

            this.manage(
                (String) data.get("identifier"), this.getIpAddress(request)
            ) ;

            this.json(response).data("error", 0).send() ;
        } catch (Exception e) {
            this.handleException(e, request, response) ;
        }
    }

    /**
     * Validate the request's inputs.
     *
     * @param request : HTTP request.
     * @return list of inputs.
     * @throws InternalException : input error.
     */
    protected ValidatedInput validateInput(HttpServletRequest request) throws InternalException {
        ValidatedInput data = new ValidatedInput() ;

        // Validate the identifier input.
        String identifier = request.getParameter("identifier") ;

        if(Validator.isNull(identifier)) {
            throw new InternalException("Null identifier") ;
        }

        data.put("identifier", identifier) ;

        return data ;
    }

    /**
     * Manage the user new status.
     *
     * @param identifier : user identifier.
     * @param ip : user's ip address.
     */
    private void manage(String identifier, String ip) throws DatabaseException, AlreadyExistUserException {
        UserController.addUser(identifier, ip) ;
    }

}