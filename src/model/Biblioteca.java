package model;

import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Daniel Juanes Sánchez
 * 
 * Definicion de clase Biblioteca
 */
public class Biblioteca implements Serializable {
    private ArrayList<Libro> listaLibros;
    
    public Biblioteca () {
        listaLibros = new ArrayList<>();
    }

    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }
    
    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    //WORKING
    public void addLibroNuevo() {
        Scanner sc = new Scanner(System.in);
        String isbnTemp, titTemp, autTemp;
        
        System.out.printf("\nINTRODUCE EL ISBN (5 LETRAS) -> ");
        isbnTemp = sc.nextLine();
        System.out.printf("\nINTRODUCE EL TITULO -> ");
        titTemp = sc.nextLine();
        System.out.printf("\nINTRODUCE EL AUTOR -> ");
        autTemp = sc.nextLine();

        listaLibros.add(new Libro(isbnTemp, titTemp, autTemp, false));
        
    }

    //WORKING (puede que haya algo mejorable?)
    public void eliminarLibro() {
        ArrayList<Libro> listaRepetidos = new ArrayList<>(); 
        Scanner sc = new Scanner(System.in);
        //En el peor de los casos serán todos repetidos y como un array no es una estructura de tamaño dinámico le tendré que asignar el mayor tamaño posible para evitar excepciones out of bounds
        int[] repetidos = new int[listaLibros.size()]; 
        int j = 0;
        int aBorrar;
        
        System.out.printf("\nINTRODUCE EL ISBN DEL LIBRO QUE QUIERES BORRAR -> ");
        String isbnBorrar = sc.next();
        
        
        boolean borrar = false;
        boolean indiceCorrecto = true;
        //Creo una lista de repetidos para facilitar la eleccion a la hora de borrarlo y un array con los indices originales para operar sobre la lista original con los indices reducidos
        for(int i = 0; i < listaLibros.size(); i++) {
            if(isbnBorrar.equals(listaLibros.get(i).getISBN())) {
                listaRepetidos.add(listaLibros.get(i));
                repetidos[j] = i;
                j++;
            }
        }
        
        //Si la lista está vacía no se hará nada más
        if(!listaRepetidos.isEmpty()) {
            printRepeatedTableHeader();

            for(int i = 0; i < listaRepetidos.size(); i++) {

                System.out.printf("| %2d - %-2d | %5s - %-15s por %-10s |\n", i, repetidos[i], listaRepetidos.get(i).getISBN(), listaRepetidos.get(i).getTitulo(), listaRepetidos.get(i).getAutor());
            } 

            //DOS CASOS: SOLO UN MATCH O MAS DE UN MATCH
            if(listaRepetidos.size() < 2) {
                System.out.printf("\n¿ESTÁS SEGURO DE QUE QUIERES ELIMINAR ESTA ENTRADA? (y/n) -> ");

                if((borrar = yesNoMenu()) == true) {
                    System.out.printf("\n\nSE ELIMINARÁ LA ENTRADA %d DE LA BIBLIOTECA", repetidos[0]);
                    listaLibros.remove(repetidos[0]);
                } else {
                    System.out.printf("\n\nNO SE HA ELIMIADO NINGUNA ENTRADA DE LA BIBLIOTECA");
                }


            } else {
                do {
                    System.out.printf("\n¿QUÉ ENTRADA QUIERES ELIMINAR (0 - %d)? -> ", listaRepetidos.size() - 1);
                
                    aBorrar = parseInt(sc.next());
                    
                    if(aBorrar > listaRepetidos.size() || aBorrar < 0) {
                        System.out.printf("\nERROR: INDICE INTRODUCIDO INVALIDO");
                        indiceCorrecto = false;
                    }
                    else {
                        indiceCorrecto = true;
                    }
                    
                } while(!indiceCorrecto);
                
                System.out.printf("\n¿ESTÁS SEGURO DE QUE QUIERES ELIMINAR LA ENTRADA %d? (y/n) -> ", aBorrar);

                if((borrar = yesNoMenu()) == true) {
                    System.out.printf("\n\nSE ELIMINARÁ LA ENTRADA %d DE LA BIBLIOTECA", aBorrar);
                    listaLibros.remove(repetidos[aBorrar]);
                } else {
                    System.out.printf("\n\nNO SE HA ELIMIADO NINGUNA ENTRADA DE LA BIBLIOTECA");
                }
                
                
            }
        
        } else {
            System.out.printf("\nNO SE HA ENCONTRADO NINGUNA ENTRADA CON EL ISBN: %s", isbnBorrar);
        }
        
        
    }

    
    //WORKING
    public void visualizarLibrosAutor() {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("\n¿QUÉ AUTOR QUIERES CONSULTAR? ->");
        String autor = sc.nextLine();
        
        printTableHeader();
        for (int i = 0; i < listaLibros.size(); i++) {
            if(autor.equals(listaLibros.get(i).getAutor())) {
                System.out.printf("|%-2d | %5s - %-15s por %-10s |\n", i, listaLibros.get(i).getISBN(), listaLibros.get(i).getTitulo(), listaLibros.get(i).getAutor());
            }  
        }
        System.out.printf("\n");
    }

    //WORKING
    public void realizarPrestamo() {
        ArrayList<Libro> listaRepetidos = new ArrayList<>(); 
        Scanner sc = new Scanner(System.in);
        //Igual que en el anterior
        int[] repetidos = new int[listaLibros.size()]; 
        int j = 0;
        int aPrestar;
        
        boolean tomarPrestado = false;
        boolean indiceCorrecto = true;
        
        printLibros();
        
        System.out.printf("\n¿QUÉ LIBRO QUIERES TOMAR PRESTADO? (TITULO) -> ");
        String libroAPrestar = sc.nextLine();
        
        //Solo listaremos los ejemplares que no hayan sido prestados previamente para evitar complejidad en la logica posterior
        for(int i = 0; i < listaLibros.size(); i++) {
            if(libroAPrestar.equals(listaLibros.get(i).getTitulo()) && listaLibros.get(i).isPrestado() == false) { 
                listaRepetidos.add(listaLibros.get(i));
                repetidos[j] = i;
                j++;
            }
        }
        
        if(!listaRepetidos.isEmpty()) {
            printRepeatedTableHeader();

            for(int i = 0; i < listaRepetidos.size(); i++) {
                System.out.printf("| %2d - %-2d | %5s - %-15s por %-10s |\n", i, repetidos[i], listaRepetidos.get(i).getISBN(), listaRepetidos.get(i).getTitulo(), listaRepetidos.get(i).getAutor());
            } 
            
            
            if(listaRepetidos.size() < 2) {
                System.out.printf("\n¿ESTÁS SEGURO DE QUE QUIERES TOMAR PRESTADO ESTE LIBRO? (y/n) -> ");

                if((tomarPrestado = yesNoMenu()) == true) {
                    System.out.printf("\n\nSE HA TOMADO PRESTADO EL LIBRO %d DE LA BIBLIOTECA", repetidos[0]);
                    listaLibros.get(repetidos[0]).setPrestado(true);
                    
                } else {
                    System.out.printf("\n\n SE HA CANCELADO EL PRESTAMO DEL LIBRO");
                }


            } else {
                do {
                    System.out.printf("\n¿QUÉ LIBRO QUIERES TOMAR PRESTADO (0 - %d)? -> ", listaRepetidos.size() - 1);
                
                    aPrestar = parseInt(sc.next());
                    
                    if(aPrestar > listaRepetidos.size() || aPrestar< 0) {
                        System.out.printf("\nERROR: INDICE INTRODUCIDO INVALIDO");
                        indiceCorrecto = false;
                    }
                    else {
                        indiceCorrecto = true;
                    }
                    
                } while(!indiceCorrecto);
                
                System.out.printf("\n¿ESTÁS SEGURO DE QUE QUIERES TOMAR PRESTADO EL LIBRO CON INDICE %d? (y/n) -> ", aPrestar);

                if((tomarPrestado = yesNoMenu()) == true) {
                    System.out.printf("\n\nSE PRESTARÁ EL LIBRO CON INDICE %d DE LA BIBLIOTECA", aPrestar);
                    listaLibros.remove(repetidos[aPrestar]);
                } else {
                    System.out.printf("\n\nSE HA CANCELADO EL PRESTAMO");
                }
                
                
            }
            
        } else {
            System.out.printf("\nNO SE HA ENCONTRADO NINGUN LIBRO CON EL TITULO '%s'", libroAPrestar);
        }
        
    }
    
    public void printLibros() {
        printTableHeader();
        for (int i = 0; i < listaLibros.size(); i++) {
            System.out.printf("|%-2d | %5s - %-15s por %-10s |\n", i, listaLibros.get(i).getISBN(), listaLibros.get(i).getTitulo(), listaLibros.get(i).getAutor());
        }
        System.out.printf("\n");
    }
    private void printRepeatedTableHeader() {
        System.out.printf("| %3s %3s | %-7s %-19s %-13s |\n", "IDr", "IDn", "ISBN", "Titulo", "Autor");
    }
    private void printTableHeader() {
        System.out.printf("|%3s| %-7s %-19s %-8s   |\n", "IDn", "ISBN", "Titulo", "Autor");
    }
    private boolean yesNoMenu() {
        Scanner sc = new Scanner(System.in);
        String seleccion;
        boolean validInput = false;
        
        do {
            seleccion = sc.next();   
            
            if(seleccion.equals("y") || seleccion.equals("yes") || seleccion.equals("s") || seleccion.equals("si")) {
                return true;
            } else if(seleccion.equals("n") || seleccion.equals("no")) {
                return false;
            }
            else {
                validInput = true;
            }
        } while(!true);
        
        return false;
    }
}
