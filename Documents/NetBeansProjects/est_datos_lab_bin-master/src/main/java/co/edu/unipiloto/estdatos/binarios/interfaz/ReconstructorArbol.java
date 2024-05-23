package co.edu.unipiloto.estdatos.binarios.interfaz;

import java.io.*;
import java.util.*;

public class ReconstructorArbol implements IReconstructorArbol {

    private Nodo root;
    private char[] preorden;
    private char[] inorden;
    private String[][] matriz;

    // Clase Nodo interna
    public class Nodo {

        public String value;
        public Nodo left;
        public Nodo right;

        public Nodo(String valor) {
            this.value = valor;
            this.left = null;
            this.right = null;
        }
    }

    // Constructor del árbol binario
    public ReconstructorArbol() {
        this.root = null;
    }
    
    public Nodo get(String value,Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        int cmp = value.compareTo(raiz.value);
        if (cmp == 0) {
            return raiz;
        }
        if (cmp < 0) {
            return get(value, raiz.left);
        }
        return get(value, raiz.right);

    }
    
    
    public boolean contieneSubArbol(Nodo principal, Nodo subArbol){
        if (subArbol == null) {
            return true;
        }
        Nodo temp = get(subArbol.value, root);
        if (temp == null) {
            return false;
        }
        return(sonIguales(temp, subArbol));
    }
    
    public boolean sonIguales(Nodo raiz1, Nodo raiz2) {
        // Si ambos nodos son nulos, son iguales
        if (raiz1 == null && raiz2 == null) {
            return true;
        }
        // Si solo uno de los nodos es nulo, los árboles no son iguales
        if (raiz1 == null || raiz2 == null) {
            return false;
        }
        // Compara las claves de los nodos actuales usando compareTo
        int cmp = raiz1.value.compareTo(raiz2.value);
        if (cmp != 0) {
            return false;
        }
        // Recursivamente verifica los subárboles izquierdo y derecho
        return sonIguales(raiz1.left, raiz2.left) && sonIguales(raiz1.right, raiz2.right);
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
        for (char c : inorden) {
            System.out.println(c);
        }

    }

    @Override
    public void reconstruir() {
        int tamano = preorden.length;
        inicializarMatriz();  //se inicializa la matriz que se usará
        armarMatriz(); //se colocan los valores en la matriz
        llenarMatriz(); // se colocan las X en donde se encuentran las matrices
        int columna = 0;

        for (int j = 1; j <= tamano; j++) {
            if (matriz[1][j] == "X") {
                columna = j;
                break;
            }
        }
        Nodo temp = new Nodo(matriz[0][columna]);
        root = temp;
        System.out.println("La raiz es: + " + root.value + "en la columna " + columna);
        reconstruir(root, 1, columna, tamano + 1); //el nodo root, la columna de este nodo y la columna del padre para que no se pase de ahi
        temp = new Nodo("I");
        temp.left= new Nodo("H");
        System.out.println(contieneSubArbol(root, temp));
    }
    //nodo root, la fila desde la que se sigue, la columna del padre

    public void reconstruir(Nodo raiz, int fila, int columna, int columnaP) {
        int tamano = preorden.length;
        boolean izq = false;
        boolean der = false;
        for (int i = fila; i <= tamano; i++) {
            for (int j = 1; j <= tamano; j++) {  //si esta la x, si la columna de este es menor a la del padre
                //
                /*
                Si se encuentra un posible hijo 
                y la columna de este es menor a la de la root
                y la root no tiene hijos a la izquierda 
                y la columna es menor a la del padre de la root:
                                              
                Se enlaza a la izquierda
                 */
                if (matriz[i][j].equals("X") && j < columna && izq == false && j < columnaP) {
                    izq = true;
                    Nodo temp = new Nodo(matriz[i][0]);
                    raiz.left = temp;
                    System.out.println(raiz.left.value + " se enlazo a la izq de " + raiz.value);
                    System.out.println("j: " + j + ", columna: " + columna + ", columnaP: " + columnaP);
                    //se le da la nueva root, la columna de esta y la columna del padre, ya que para enlazar a la izquierda
                    //la columna debe ser menor a la del padre
                    reconstruir(raiz.left, i, j, columna);
                }
                /*
                Si se encuentra un posible hijo 
                y la columna de este es mayor que la de la root
                y la root no tiene enlace a la derecha
                y la columna del hijo es menor a la columna del padre
                 */
                if (matriz[i][j].equals("X") && j > columna && der == false && j < columnaP) {
                    der = true;
                    Nodo temp = new Nodo(matriz[i][0]);
                    raiz.right = temp;
                    System.out.println(raiz.right.value + " se enlazo a la der de " + raiz.value);
                    System.out.println("j: " + j + ", columna: " + columna + ", columnaP: " + columnaP);
                    //Se le da la nueva root, la columna de esta y la columna del padre del padre de esta
                    //ya que para enlazar a la derecha la nueva columna el hijo puede ser > al padre pero
                    // <a la del padre del padre
                    reconstruir(raiz.right, i, j, columnaP);

                }
            }
        }
    }

    public void inicializarMatriz() {
        int tamano = preorden.length;
        matriz = new String[tamano + 1][tamano + 1];
        for (int i = 0; i < tamano + 1; i++) {
            for (int j = 0; j < tamano + 1; j++) {
                matriz[i][j] = " ";
            }
        }
    }

    private void armarMatriz() {
        int tamano = preorden.length;

        for (int i = 1; i < tamano + 1; i++) {
            matriz[i][0] = String.valueOf(preorden[i - 1]);
            System.out.println(preorden[i - 1] + "se almaceno en: " + "[" + i + "][0]");
        }

        for (int j = 1; j < tamano + 1; j++) {
            matriz[0][j] = String.valueOf(inorden[j - 1]);
            System.out.println(inorden[j - 1] + "se almaceno en: " + "[0],[" + j + "]");
        }
        mostrarMatriz();
        //Hasta el momento solo he guardadpo los valores en la matriz, esto esta correcto, el espacio [0][0]
        //no lo tengo en cuenta.
    }

    private void llenarMatriz() {
        int tamano = preorden.length;
        for (int i = 1; i < tamano + 1; i++) {
            for (int j = 1; j < tamano + 1; j++) {
                if (matriz[i][0].equals(matriz[0][j])) {
                    System.out.println(matriz[i][0] + " es igual a: " + matriz[0][j] + " y se agrego en: [" + i + "][" + j + "]");
                    matriz[i][j] = "X";
                    break;
                }
            }
        }
        mostrarMatriz();
    }

    public void mostrarMatriz() {
        int tamano = preorden.length;
        for (int i = 0; i < tamano + 1; i++) {
            for (int j = 0; j < tamano + 1; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    @Override
    public void crearArchivo(String info) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/arbolPlantado.json", "UTF-8");
        writer.println(info);
        writer.close();
    }
}
