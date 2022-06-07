package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;

/**
 * @author Daniel Juanes Sánchez
 * 
 * El modelo contiene toda la parte de datos del programa y los metodos referentes a ellos (en general), los metodos especificos de cada
 * clase seguiran dentro de la que corresponda, aqui se crean y se destruyen los objetos con los que se va a tratar durante el runtime del programa.
 */
public class Model implements Serializable {
    Biblioteca b = new Biblioteca();

    
    //***********************
    //*    TRATAR DATOS     *
    //***********************
    public void addLibroNuevo() {
        b.addLibroNuevo();
    }

    public void eliminarLibro() {
        b.eliminarLibro();
    }

    public void visualizarLibrosAutor() {
        b.visualizarLibrosAutor();
    }

    public void realizarPrestamo() {
        b.realizarPrestamo();
    }
    
    //****************
    //*    INPUT     *
    //****************
    public void leerBiblioteca(String path) throws IOException, ClassNotFoundException, FileNotFoundException {
        if(!fileExists(path)) throw new FileNotFoundException();
        
        File f = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
        
        b = (Biblioteca) ois.readObject();
        
        b.printLibros();
        
        System.out.printf("\nSE HAN LEIDO CORRECTAMENTE %d LIBROS DE %s\n\n", 1, f);
    }
    
    //****************
    //*    OUTPUT    *
    //****************
    public void escribirBiblioteca(String path) throws FileSystemNotFoundException, FileNotFoundException, IOException {
        File f = new File(path);
        
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        
        oos.writeObject(b);
        oos.close();
        
        System.out.printf("\nSE HAN GUARDADO CORRECTAMENTE %d LIBROS EN %s\n\n", 1, f);
    }
    
    //****************
    //*     UTIL     *
    //****************
    public boolean fileExists(String fileName) {
        boolean foundFile = false;
        
        try {
            File f = new File(fileName);

            if(f.exists() && f.isFile()) foundFile = true;
            
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        return foundFile;
    }
}
