package mx.com.gm.peliculas.presentacion;

import java.util.Scanner;
import mx.com.gm.peliculas.negocio.CatalogoPeliculas;
import mx.com.gm.peliculas.negocio.CatalogoPeliculasImpl;

public class CatalogoPeliculasPresentacion {

    public static void main(String[] args) {
        var opcion = -1;
        var scanner = new Scanner(System.in);
        CatalogoPeliculas catalogo = new CatalogoPeliculasImpl(); //Bajo acoplamiento 
        while (opcion != 0) {
            System.out.println("Elige una Opcion: \n"
                    + "1. Crear catalogo películas\n"
                    + "2. Agregar Película\n"
                    + "3. Listar Películas\n"
                    + "4. Buscar Película\n"
                    + "0. Salir\n");

            opcion = Integer.parseInt(scanner.nextLine()); //nextInt no capta el salto de linea 
            switch (opcion) {
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("Introduzca el Nombre de la Película");
                    var nombrePelicula = scanner.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Introduzca el nombre de la Película a buscar");
                    var buscar = scanner.nextLine();
                    catalogo.buscarPelicula(buscar);
                    break;
                case 0:
                    System.out.println("¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Ingrese la opción correcta");

            }
        }
    }
}
