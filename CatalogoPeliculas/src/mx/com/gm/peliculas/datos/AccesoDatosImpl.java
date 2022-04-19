package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;

/**
 *
 * @author Felipe Rebolledo
 */
public class AccesoDatosImpl implements AccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {

        File archivo = new File(nombreRecurso); //Se crea variable archivo
        return archivo.exists(); //Se consulta si el archivo existe en booleano

    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>(); //Recibe objetos de tipo Pelicula- Se inicializa lista de Generico Pelicula
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); //Variable de Entrada: Leemos el Archivo. 
            String linea = null; //Contador de Líneas 
            linea = entrada.readLine();
            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea); //Regresa lista de objetos de tipo pelicula
                peliculas.add(pelicula); //Agregamos el objeto de tipo pelicula a la linea
                linea = entrada.readLine(); //Leemos la linea para ver si tenemos una nueva linea
            }
            entrada.close(); //Se cierra el flujo

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage()); //Se propaga la excepción con un mensaje. 
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        }
        return peliculas; //Retornamos el listado de peliculas 
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha agregado la Película " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepción al Escribir peliculas: " + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            int indice = 1; //Inicializamos el indice y va incrementando
            while (linea != null) {
                if (buscar != null && buscar.equals(linea)) {
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepción al Buscar pelicula " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepción al Buscar pelicula " + ex.getMessage());
        }
        return resultado;

    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el Archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepción al crear archivo " + ex.getMessage());

        }
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File (nombreRecurso);
        if(archivo.exists())
            archivo.delete();
        System.out.println("Se ha eliminado el Archivo");
    }

}
