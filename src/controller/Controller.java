package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import model.Model;
import util.Paths;
/**
 * @author Daniel Juanes Sánchez
 */

public class Controller implements Serializable {
    Model m = new Model();
    Paths p = new Paths();
    
    public void addLibroNuevo() {
        m.addLibroNuevo();
    }

    public void eliminarLibro() {
        m.eliminarLibro();
    }

    public void visualizarLibrosAutor() {
        m.visualizarLibrosAutor();
    }

    public void realizarPrestamo() {
        m.realizarPrestamo();
    }
    
    public void leerBiblioteca() throws IOException, FileNotFoundException, ClassNotFoundException {
        m.leerBiblioteca(p.PATH_BIBLIOTECA_BIN);
    }
    public void escribirBiblioteca() throws IOException, FileNotFoundException, ClassNotFoundException {
        m.escribirBiblioteca(p.PATH_BIBLIOTECA_BIN);
    }
    
}
