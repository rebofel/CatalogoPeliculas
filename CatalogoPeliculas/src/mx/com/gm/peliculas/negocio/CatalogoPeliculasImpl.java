/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.gm.peliculas.negocio;

import mx.com.gm.peliculas.datos.AccesoDatos;
import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

/**
 *
 * @author admin
 */
 public class CatalogoPeliculasImpl implements CatalogoPeliculas {

  private final AccesoDatos datos;
  
  public CatalogoPeliculasImpl(){
      this.datos = new AccesoDatosImpl();
  }
     
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
      try {
          anexar = datos.existe(NOMBRE_RECURSO);
          datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
      } catch (AccesoDatosEx ex) {
          System.out.println("Error Acceso a Datos");
          ex.printStackTrace(System.out);
      }
    }

    @Override
    public void listarPeliculas() {
      try {
          var peliculas = this.datos.listar(NOMBRE_RECURSO);
          for(var pelicula: peliculas){
              System.out.println("pelicula = " + pelicula);
          }
      } catch (LecturaDatosEx ex) {
          System.out.println("Error Acceso a Datos");
          ex.printStackTrace(System.out);
      }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null; 
      try {
          resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
      } catch (LecturaDatosEx ex) {
          System.out.println("Error acceso datos");
          ex.printStackTrace(System.out);
      }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
      try {
          if(this.datos.existe(NOMBRE_RECURSO)){
              datos.borrar(NOMBRE_RECURSO);
              datos.crear(NOMBRE_RECURSO);
          }else{
              datos.crear(NOMBRE_RECURSO);
          }
      } catch (AccesoDatosEx ex) {
          System.out.println("Error al iniciar el catalogo de peliculas");
          ex.printStackTrace(System.out);
      }
    }

  
}
