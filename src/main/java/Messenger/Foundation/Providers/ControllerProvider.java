package Messenger.Foundation.Providers;

import java.util.ArrayList;
import Messenger.Foundation.Controllers.Controller;

/**
 * @author Damien MOLINA
 */
public class ControllerProvider {

    /**
     * Controllers list.
     */
    private final ArrayList<Controller> controllers ;

    /**
     * Make a new Controller provider instance.
     */
    public ControllerProvider() {
        this.controllers = new ArrayList<>() ;
    }

    /**
     * Add a controller to the controllers list.
     *
     * @param controller : controller to add.
     */
    public void add(Controller controller) {
        this.controllers.add(controller) ;
    }

    /**
     * Get the controller identified by the given
     * class instance.
     *
     * @param controller : controller class.
     * @return the controller instance, null otherwise.
     */
    public Controller get(Class<? extends Controller> controller) {
        for (Controller c : this.controllers) {
            if(c.getClass().equals(controller)) {
                return c ;
            }
        }

        return null ;
    }

}
