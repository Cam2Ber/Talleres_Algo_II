package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private int altura;
    private Nodo root;
    private ABB<T> padre;
    private ABB<T> izquierda;
    private ABB<T> derecha; 
    // Agregar atributos privados del Conjunto

    private class Nodo {
        T datos;
        // Agregar atributos privados del Nodo

        private Nodo(T valor) {
            this.datos = valor;
        }
        // Crear Constructor del nodo
    }

    public ABB() {
        this.altura = 0;
        this.root = null;
    }

    public int cardinal() {
        return this.altura;
    }

    public T minimo(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T maximo(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void insertar(T elem){
        if (this.root == null){
            this.insertar_en_Vacio(elem);
        }
        else {
            this.insertar_en_Arbol(elem);
        }
    }

    private void insertar_en_Vacio(T elem){
        this.root = new Nodo(elem);
        this.altura = this.altura+1;
    }

    private void insertar_en_Arbol(T elem){
        if (!this.pertenece(elem)){
            ABB<T> ArbolAInsertar = new ABB<T>();
            ArbolAInsertar.root = new Nodo(elem);
            ABB<T> Arbol = this;
            while (Arbol.root.datos != elem) {
                Arbol = comparar_e_insertar(elem, Arbol);
            }
        }
    }

    private ABB<T> comparar_e_insertar(T elem, ABB<T> Arbol){
        Arbol.altura = Arbol.altura+1;
        if (Arbol.root.datos.compareTo(elem) > 0) {
            if (Arbol.izquierda == null) {
                Arbol.izquierda = ArbolAInsertar;
                ArbolAInsertar.padre = Arbol;
            }
            Arbol = Arbol.izquierda;
        }
        else {
            if (Arbol.derecha == null) {
                Arbol.derecha = ArbolAInsertar;
                ArbolAInsertar.padre = Arbol;
            }
            Arbol = Arbol.derecha;
        }
    }

    private ABB<T> perteneceArbol(T elem){
        ABB<T> arbol = this;
        while ((arbol.root != null) && (arbol.root.datos != elem)) {
            Nodo nodoActual = arbol.root;
            if (nodoActual.datos.compareTo(elem) > 0) {
                arbol = arbol.izquierda;
            }
            else {
                arbol = arbol.derecha;
            }
        }
        return arbol;
    }

    public boolean pertenece(T elem){
        boolean res = false;
        ABB<T> arbol = this.perteneceArbol(elem);
        if (arbol.root != null) {
            res = true;
        }
        return res;
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
