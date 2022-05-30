package model;

import java.io.Serializable;

/**
 * @author Daniel Juanes Sánchez
 * 
 * Definicion de clase Libro
 */

public class Libro implements Serializable {
    
    //********************
    //*     VARIABLES    *
    //********************
    private String ISBN;
    private String titulo;
    private String autor;
    private boolean prestado;
    
    //***********************
    //*     CONSTRUCTORS    *
    //***********************
    public Libro() {
    
    }
    
    public Libro(String codigo, String tit, String aut, boolean pres) {
        this.ISBN = codigo;
        this.titulo = tit;
        this.autor = aut;
        this.prestado = pres;
    }
    
    //******************
    //*     GETTERS    *
    //******************
    public String getISBN() {
        return ISBN;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public boolean isPrestado() {
        return prestado;
    }
    
    //******************
    //*     SETTERS    *
    //******************
    public void setISBN(String ISBN) {
           this.ISBN = ISBN;
       }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

}
