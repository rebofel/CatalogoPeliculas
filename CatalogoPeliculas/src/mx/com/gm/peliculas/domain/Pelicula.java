package mx.com.gm.peliculas.domain;

/**
 *
 * @author Felipe Rebolledo
 */
public class Pelicula {
    private String nombre;

    public Pelicula() {
    }

    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre; //Para que solo ingrese el nombre de la pelicula 
    }
    
    
}
