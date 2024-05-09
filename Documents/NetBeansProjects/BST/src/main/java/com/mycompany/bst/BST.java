/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bst;

import java.util.Iterator;
import java.util.Scanner;

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

    public Nodo min(Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        if (raiz.left == null) {
            return raiz;
        }
        return min(raiz.left);
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
                Nodo temp = raiz;
                raiz = min(temp.right);
                raiz.right = deleteMin(temp.right);
                raiz.left = temp.left;
            } else if (cmp < 0) {
                raiz.left = delete(key, raiz.left);
            } else {
                raiz.right = delete(key, raiz.right);
            }
        }
        return raiz;
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    public boolean contains(Nodo root, Key key) {

        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            return true;
        }
        if (cmp > 0 && root.left != null) {
            return contains(root.left, key);
        }
        if (cmp < 0 && root.right != null) {
            return contains(root.right, key);
        }
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

    public Nodo getNodo(Key key) {
        return getNodo(key, root);
    }

    public Nodo getNodo(Key key, Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return raiz;
        }
        if (cmp < 0) {
            return getNodo(key, raiz.left);
        }
        return getNodo(key, raiz.right);

    }

    public boolean esHoja(Key key) {
        Nodo temp = getNodo(key, root);
        if (temp == null) {
            return false;
        }
        if (temp.left == null && temp.right == null) {
            return true;
        }
        return false;
    }

    public int altura(Key key) {
        if (!contains(key)) {
            return 0;
        }

        return altura(key, root);
    }

    private int altura(Key key, Nodo raiz) {

        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return 1;
        }
        if (cmp < 0) {
            return 1 + altura(key, raiz.left);
        }
        return 1 + altura(key, raiz.right);

    }

    public boolean hayCamino(Key key1, Key key2) {
        if (!contains(key1) || !contains(key2)) {
            System.out.println("Una de las llaves dadas no existe en el arbol");
            return false;
        }
        if (altura(key1) < altura(key2)) {
            return hayCamino(key2, getNodo(key1));
        }
        return hayCamino(key1, getNodo(key2));
    }

    public boolean hayCamino(Key key, Nodo raiz) {

        if (raiz == null) {
            System.out.println("No hay camino");
            return false;
        }
        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return true;
        }

        if (cmp < 0) {
            return hayCamino(key, raiz.left);
        }
        return hayCamino(key, raiz.right);
    }

    public String mostrarCamino(Key key1, Key key2) {
        if (!hayCamino(key1, key2)) {
            return null;
        }
        if (altura(key1) < altura(key2)) {
            Nodo temp = getNodo(key1);
            return mostrarCamino(key2, temp);
        }
        Nodo temp = getNodo(key2);
        return mostrarCamino(key1, temp);
    }

    public String mostrarCamino(Key key, Nodo raiz) {
        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return (String) raiz.value;
        }

        if (cmp < 0) {
            return raiz.value + "-" + mostrarCamino(key, raiz.left);
        }
        return raiz.value+ "-" + mostrarCamino(key, raiz.right);
    }

    public int longitudCamino(Key key1, Key key2) {
        if (!hayCamino(key1, key2)) {
            return 0;
        }
        if (altura(key1) < altura(key2)) {
            Nodo temp = getNodo(key1);
            return longitudCamino(key2, temp);
        }
        Nodo temp = getNodo(key2);
        return longitudCamino(key1, temp);
    }

    public int longitudCamino(Key key, Nodo raiz) {
        int cmp = key.compareTo(raiz.key);
        if (cmp == 0) {
            return 0;
        }

        if (cmp < 0) {
            return 1 + longitudCamino(key, raiz.left);
        }
        return 1 + longitudCamino(key, raiz.right);
    }
    
    
    public boolean esDesendiente(Key key1, Key key2){
        // Compara las alturas de los nodos
        return altura(key1) < altura(key2) && hayCamino(key1, key2);
    }
    
//    public Iterable<Key> keys() {
//        //return keys(min(), max());
//        return null;
//    }
//
//    public Iterable<Key> keys(Key lo, Key hi) {
//        Queue<Key> queue = new Queue<Key>();
//        keys(root, queue, lo, hi);
//        return queue;
//    }
//
//    private void keys(Nodo raiz, Queue<Key> queue, Key lo, Key hi) {
//        if (raiz == null) {
//            return;
//        }
//        int cmplo = lo.compareTo(raiz.key);
//        int cmphi = hi.compareTo(raiz.key);
//        if (cmplo < 0) {
//            keys(raiz.left, queue, lo, hi);
//        }
//        if (cmplo <= 0 && cmphi >= 0) {
//            queue.add(raiz.key);
//        }
//        if (cmphi > 0) {
//            keys(raiz.right, queue, lo, hi);
//        }
//    }
    public static void main(String[] args) {
        BST<Integer, String> rara = new BST();
        System.out.println("Size del arbol vacio: " + rara.size());
        rara.put(2, "2");
        rara.put(3, "3");
        rara.put(0, "0");
        rara.put(1, "1");
        System.out.println(rara.root.value);
        System.out.println(rara.root.right.value);
        System.out.println(rara.root.left.value);
        System.out.println(rara.root.left.right.value);
        System.out.println("Size luego de agregar 4 elementos al arbol: " + rara.size());
        System.out.println("¿Contiene la llave 3?: " + rara.contains(3));
        System.out.println("2 es hoja?" + rara.esHoja(2));
        System.out.println("3 es hoja?" + rara.esHoja(3));
        System.out.println("0 es hoja?" + rara.esHoja(0));
        System.out.println("1 es hoja?" + rara.esHoja(1));
        System.out.println("Hay camino entre 2 y 1?" + rara.hayCamino(2, 1));
        System.out.println("Mostrar camino entre 2 y 1: " + rara.mostrarCamino(2,1));
        System.out.println("Longitud del camino entre 2 y 1: " +  rara.longitudCamino(2, 1));
        System.out.println("1 es de descendiente de 2?: " + rara.esDesendiente(2, 1));
    }
}
