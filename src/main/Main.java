package main;

import view.View;

/**
 * This template was created by:
 * @author Daniel Juanes Sánchez
 * 
 * It's an attempt to make an straightforward MVC pattern application template
 * to adapt to any kind of basic data management application based on a OOP
 * paradigm.
 * 
 */

public class Main {

    public static void main(String[] args) {
        View v = new View();
        
        v.runMenu();
    }
    
}
