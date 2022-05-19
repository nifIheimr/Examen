package view;

import controller.Controller;
import java.io.Serializable;

/**
 * @author Daniel Juanes Sánchez
 */
public class View implements Serializable {
    //Interafacing
    UI ui = new UI();
    Controller c = new Controller();
    
    public void runMenu() {
        ui.mainMenu();
    }
}
