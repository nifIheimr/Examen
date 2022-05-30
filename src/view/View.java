package view;

import controller.Controller;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 * @author Daniel Juanes Sánchez
 * 
 * Aqui figuran todos los metodos que tratan la interacción del usuario con el programa
 */

public class View implements Serializable {
    //Interafacing
    UI ui = new UI();
    Controller c = new Controller();
    
    public void runMenu() {
        Scanner sc = new Scanner(System.in);

        boolean exc, exit;
        int o = 0;
        
        //Leer los datos previamente creados en cada nueva sesión del programa
        try {
            c.leerBiblioteca();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
        do {
            exc = false;
            exit = false;
            try {

                do {
                    ui.menuPrincipal();
                    o = parseInt(sc.next());
                    switch(o) {
                        case 1: addLibroNuevo();
                            break;
                        case 2: eliminarLibro();
                            break;
                        case 3: visualizarLibrosAutor();
                            break;
                        case 4: realizarPrestamo();
                            break;
                        case 0: exit = true;
                            break;
                        default:
                            System.out.printf("OPCIÓN INVÁLIDA \n");
                    }
                } while(!exit);

                System.out.printf("\nSALIENDO...\n");

            } catch(NumberFormatException e) {
                System.err.printf(e.getMessage());
                exc = true;
            }
        } while(exc);
        
        
        //Dumpear toda la biblioteca a un fichero una vez se sale del programa de forma normal
        try {
            c.escribirBiblioteca();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addLibroNuevo() {
        c.addLibroNuevo();
    }

    private void eliminarLibro() {
        c.eliminarLibro();
    }

    private void visualizarLibrosAutor() {
        c.visualizarLibrosAutor();
    }

    private void realizarPrestamo() {
        c.realizarPrestamo();
    }
}
