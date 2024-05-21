package co.edu.unipiloto.estdatos.binarios.interfaz;

import java.io.*;
import java.util.*;

public class ReconstructorArbol implements IReconstructorArbol {

    private Nodo raiz;
    private char[] preorden;
    private char[] inorden;
    private int preIndex = 0;

    // Clase Nodo interna
    public class Nodo {

        public String valor;
        public Nodo izquierdo;
        public Nodo derecho;

        public Nodo(String valor) {
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    // Constructor del árbol binario
    public ReconstructorArbol() {
        this.raiz = null;
    }

    @Override
    public void cargarArchivo(String nombre) throws IOException {
        Properties propiedades = new Properties();
        propiedades.load(new FileReader("data/ejemplo.properties"));
        // Obtener las secuencias preorden e inorden
        String preOrderStr = propiedades.getProperty("preorden");
        String inOrderStr = propiedades.getProperty("inorden");

        // Convertir las secuencias a arreglos de caracteres
        preorden = preOrderStr.replace(",", "").toCharArray();
        inorden = inOrderStr.replace(",", "").toCharArray();
        for (char c : preorden) {
            System.out.println(c);
        }
        
    }

    @Override
    public void reconstruir() {
        int tamano= preorden.length;
        char matriz[] []= new char[tamano+1][tamano+1];
        for (int j = 1; j < tamano+1; j++) {
            matriz[0][j] = inorden[j-1];
        }
    }

    @Override
    public void crearArchivo(String info) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/arbolPlantado.json", "UTF-8");
        writer.println(info);
        writer.close();
    }
}
