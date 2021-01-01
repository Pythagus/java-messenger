package fr.insa.messenger.tomcat.routes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insa.messenger.tomcat.utils.Validator;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.tomcat.utils.ValidatedInput;
import fr.insa.messenger.tomcat.controllers.UserController;
import fr.insa.messenger.tomcat.exceptions.InternalException;
import fr.insa.messenger.tomcat.exceptions.UnknownUserException;
import fr.insa.messenger.tools.database.exceptions.DatabaseException;

/**
 * @author Damien MOLINA
 */
@WebServlet("/publish")
public class PublishServlet extends SubscribeServlet {

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
                (String) data.get("identifier"), (UserStatus) data.get("status")
            ) ;

            this.jsonSuccess(response).send() ;
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
        ValidatedInput data = super.validateInput(request) ;

        // Validate the status.
        String strStatus = request.getParameter("status") ;

        if(Validator.isNull(strStatus)) {
            throw new InternalException("Null status") ;
        }

        /*
         * We try to cast the status. It could generate
         * a cast error. If It happens, then we generate
         * an internal error handled by the previous
         * try-catch block.
         */
        try {
            data.put(
                "status", UserStatus.valueOf(strStatus)
            ) ;
        } catch (Exception e) {
            throw new InternalException("Unknown status " + strStatus) ;
        }

        return data ;
    }

    /**
     * Manage the user new status.
     *
     * @param identifier : user identifier.
     * @param status : user new status.
     */
    private void manage(String identifier, UserStatus status) throws DatabaseException, UnknownUserException {
        UserController.setStatus(identifier, status) ;
    }

}