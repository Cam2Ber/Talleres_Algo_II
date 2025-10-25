package aed;

import java.util.*;

import javax.swing.plaf.TreeUI;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private Nodo root;
    private int tamano;

    private class Nodo {
        T datos;
        Nodo izquierda;
        Nodo derecha;
        Nodo Padre;

        private Nodo(T valor) {
            this.datos = valor;
        }
    }

    public ABB() {
        this.root = null;
        this.tamano = 0;
    }

    public int cardinal() {
        return this.tamano;
    }

    public T minimo(){
        Nodo nodoActual = this.root;
        while (nodoActual.izquierda != null) {
            nodoActual = nodoActual.izquierda;
        }
        return nodoActual.datos;
    }

    public T maximo(){
        Nodo nodoActual = this.root;
        while (nodoActual.derecha != null) {
            nodoActual = nodoActual.derecha;
        }
        return nodoActual.datos;
    }

    public void insertar(T elem){
        if (this.tamano == 0) {
            this.root = new Nodo(elem);
            this.tamano = this.tamano+1;
        }
        else {
            Nodo nodoActual = this.root;
            while (nodoActual.datos != elem) {
                if (nodoActual.datos.compareTo(elem) > 0) {
                    if (nodoActual.izquierda == null) {
                        nodoActual.izquierda = new Nodo(elem);
                        nodoActual.izquierda.Padre = nodoActual;
                        this.tamano = this.tamano+1;
                    }
                    nodoActual = nodoActual.izquierda;
                }
                else {
                    if (nodoActual.derecha == null) {
                        nodoActual.derecha = new Nodo(elem);
                        nodoActual.derecha.Padre = nodoActual;
                        this.tamano = this.tamano+1;
                    }
                    nodoActual = nodoActual.derecha;
                }
            }
        }
    }

    private Nodo pertenece_Nodo(T elem){
        Nodo nodoActual = this.root;
        while ((nodoActual != null) && (nodoActual.datos != elem)) {
            if (nodoActual.datos.compareTo(elem) > 0) {
                nodoActual = nodoActual.izquierda;
            }
            else {
                nodoActual = nodoActual.derecha;
            }
        }
        return nodoActual;
    }

    public boolean pertenece(T elem){
        boolean res = false;
        Nodo nodoActual = this.pertenece_Nodo(elem);
        if (nodoActual != null) {
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
