/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bst;

import java.util.Iterator;

/**
 *
 * @author ACER
 */
/**
 *
 * @author USUARIO
 */
public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    //Apuntador o referencia a la raiz
    private Nodo root;

    //Nodo arbol binario
    private class Nodo {

        private Key key;
        private Value value;
        private Nodo left, right;

        //Constructores
        public Nodo() {
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
        }

        public Nodo(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        //Un arbol binario es:
        // null
        // o un nodo con hijo izquierdo e hijo derecho
        //     

    }

    public BST() {
        this.root = null;
    }

    public BST(Nodo root) {
        this.root = root;
    }

    public void put(Key key, Value value) {
        root = put(new Nodo(key, value), root);

    }

    public Nodo put(Nodo nodo, Nodo raiz) {
        if (raiz == null) {
            return nodo;
        }
        int cmp = nodo.key.compareTo(raiz.key);
        if (cmp < 0) {
            raiz.left = put(nodo, raiz.left);
        } else {
            raiz.right = put(nodo, raiz.right);
        }
        return raiz;
    }

    public Value get(Key key) {

        return get(key, root);

    }

    public Value get(Key key, Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return raiz.value;
        }
        if (cmp < 0) {
            return get(key, raiz.left);
        }
        return get(key, raiz.right);

    }

    public void delete(Key key) {

        Nodo actual = root;
        root = delete(key, actual);
    }

    public boolean esHoja(Nodo raiz) {
        if (raiz.left == null && raiz.right == null) {
            return true;
        }
        return false;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public Nodo deleteMin(Nodo raiz) {
        if (raiz.left == null) {
            return raiz.right;
        }
        raiz.left = deleteMin(raiz.left);
        return raiz;

    }

    public Nodo delete(Key key, Nodo raiz) {
        if (raiz == null) {
            System.out.println("Elemento no encontrado");
            raiz = null;
        } else {
            int cmp = key.compareTo(raiz.key);
            if (cmp == 0) {
                //consideracion de eliminacion
                //El nodo es hoja
                System.out.println("Elemento encontrado");
                if (esHoja(raiz)) {
                    System.out.println("Es hoja");
                    raiz = null;
                } else //Uno de los hijos es null
                if (raiz.left == null) {
                    System.out.println("hijo izquierdo vacio");
                    raiz = raiz.right;
                } else if (raiz.right == null) {
                    System.out.println("hijo derecho vacio");
                    raiz = raiz.left;
                }
                //Ambos hijos tienen subarboles

            } else if (cmp < 0) {
                raiz.left = delete(key, raiz.left);
            } else {
                raiz.right = delete(key, raiz.right);
            }
        }
        return raiz;
    }

    public boolean contains(Key key) {
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    public int size(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + size(raiz.left) + size(raiz.right);
    }

    public Key ceiling(Key key) {
        Nodo result = ceiling(root, key);
        if (result == null) {
            return null;
        }
        return result.key;
    }

    private Nodo ceiling(Nodo node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node; // La clave exacta fue encontrada
        } else if (cmp > 0) {
            // Si la clave dada es mayor, el techo debe estar en el subárbol derecho
            return ceiling(node.right, key);
        } else {
            // Si la clave dada es menor, el techo podría ser este nodo o en el subárbol izquierdo
            Nodo leftCeiling = ceiling(node.left, key);
            if (leftCeiling != null) {
                return leftCeiling;
            } else {
                return node;
            }
        }
    }
    
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Nodo node) {
        if (node == null) return 0;
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(key, node.left);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else {
            return size(node.left);
        }
    }

    public Key floor(Key key) {
        Nodo result = floor(root, key);
        if (result == null) {
            return null;
        }
        return result.key;
    }

    private Nodo floor(Nodo node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            // La clave exacta fue encontrada
            return node;
        } else if (cmp < 0) {
            // Si la clave dada es menor, el piso debe estar en el subárbol izquierdo
            return floor(node.left, key);
        } else {
            // Si la clave dada es mayor, el piso podría ser este nodo o en el subárbol derecho
            Nodo rightFloor = floor(node.right, key);
            if (rightFloor != null) {
                return rightFloor;
            } else {
                return node;
            }
        }
    }
    
    public Key max() {
        if (root == null) {
            return null; // o lanzar una excepción si es preferible
        }
        return max(root).key;
    }

    private Nodo max(Nodo node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    private Nodo min(Nodo node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }
    

    //Iterador
    public Iterator<Key> iterator() {
        return new BinaryIterator();
    }

    private class BinaryIterator implements Iterator<Key> {

        private Nodo actual = root;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public Key next() {
            return null;
        }
    }

    public void mostrarDatos(Nodo raiz) {
        if (raiz == null) {
            return;
        }
        mostrarDatos(raiz.left);
        System.out.println("llave " + raiz.key + " valor " + raiz.value);
        mostrarDatos(raiz.right);
    }

    public void mostrarDatos() {
        System.out.println("--------------------");
        mostrarDatos(root);
    }
    
    public static void main(String[] args) {
        // Crear un árbol binario de búsqueda
        BST<Integer, String> bst = new BST<>();

        // Insertar elementos en el árbol
        bst.put(50, "Cincuenta");
        bst.put(30, "Treinta");
        bst.put(70, "Setenta");
        bst.put(20, "Veinte");
        bst.put(40, "Cuarenta");
        bst.put(60, "Sesenta");
        bst.put(80, "Ochenta");

        // Mostrar los datos del árbol
        bst.mostrarDatos();

        // Buscar un elemento en el árbol
        int keyToFind = 40;
        String value = bst.get(keyToFind);
        if (value != null) {
            System.out.println("Valor encontrado para la clave " + keyToFind + ": " + value);
        } else {
            System.out.println("No se encontró ningún valor para la clave " + keyToFind);
        }

        // Eliminar un elemento del árbol
        int keyToDelete = 20;
        bst.delete(keyToDelete);
        System.out.println("Después de eliminar la clave " + keyToDelete + ":");
        bst.mostrarDatos();

        // Obtener el mínimo y el máximo
        System.out.println("El mínimo es: " + bst.min());
        System.out.println("El máximo es: " + bst.max());

        // Obtener el techo y el piso para una clave
        int keyCeilingFloor = 55;
        System.out.println("El techo para la clave " + keyCeilingFloor + " es: " + bst.ceiling(keyCeilingFloor));
        System.out.println("El piso para la clave " + keyCeilingFloor + " es: " + bst.floor(keyCeilingFloor));

        // Obtener el rango de una clave
        int keyRank = 35;
        System.out.println("El rango para la clave " + keyRank + " es: " + bst.rank(keyRank));
    }

}
