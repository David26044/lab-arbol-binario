package co.edu.unipiloto.estdatos.binarios.interfaz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface IReconstructorArbol {

	/**
	 * Este es el método que carga el archivo
	 * @param nombre es el nombre del archivo a cargar (con .properties incluido)
	 * @throws IOException si no se puede cargar el archivo
	 */
	public void cargarArchivo(String nombre) throws IOException;


	/**
	 * Este método crea el archivo con el árbol en formato JSON
	 * @param info el método crea el archivo con esta información dentro
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void crearArchivo(String info) throws FileNotFoundException, UnsupportedEncodingException;
	
	/**
	 * Este es el método que reconstruye el árbol.El inorden y postorden son
	 * atributos del mundo.
	 */
	public void reconstruir();

}

