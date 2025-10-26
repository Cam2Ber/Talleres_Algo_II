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

        private int Cantidad_de_hijos(){
            int res = 0;
            if (this.izquierda != null){
                res = res+1;
            }
            if (this.derecha != null){
                res = res+1;
            }
            return res;
        }

        private T minimoNodo(){
            Nodo buscador = this;
            while (buscador.izquierda != null){
                buscador = buscador.izquierda;
            }
            return buscador.datos;
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
        Nodo nodoABorrar = this.pertenece_Nodo(elem);
        if (nodoABorrar == null){
            return;
        }
        int Caso = nodoABorrar.Cantidad_de_hijos();
        boolean fatherless = (this.root.datos == nodoABorrar.datos);
        boolean hijoIzquierdo = false;
        if (!fatherless){
            hijoIzquierdo = (nodoABorrar.Padre.datos.compareTo(nodoABorrar.datos) > 0);
        }
        if (Caso == 0){
            this.tamano = this.tamano-1;
            if (!fatherless){
                if (hijoIzquierdo){
                    nodoABorrar.Padre.izquierda = null;
                }
                else{
                    nodoABorrar.Padre.derecha = null;
                }
            }
            nodoABorrar.datos = null;
        }
        if (Caso == 1){
            this.tamano = this.tamano-1;
            if (nodoABorrar.izquierda != null){
                if (!fatherless){
                    nodoABorrar.izquierda.Padre = nodoABorrar.Padre;
                    if (hijoIzquierdo){
                        nodoABorrar.Padre.izquierda = nodoABorrar.izquierda;
                    }
                    else{
                        nodoABorrar.Padre.derecha = nodoABorrar.izquierda;
                    }
                }
                else{
                    nodoABorrar.izquierda.Padre = null;
                    this.root = nodoABorrar.izquierda;
                }
            }
            else{
                if (!fatherless){
                    nodoABorrar.derecha.Padre = nodoABorrar.Padre;
                    if (hijoIzquierdo){
                        nodoABorrar.Padre.izquierda = nodoABorrar.derecha;
                    }
                    else{
                        nodoABorrar.Padre.derecha = nodoABorrar.derecha;
                    }
                }
                else{
                    nodoABorrar.derecha.Padre = null;
                    this.root = nodoABorrar.derecha;
                }
            }
        }
        if (Caso == 2){
            T sucesor = nodoABorrar.derecha.minimoNodo();
            this.eliminar(sucesor);
            nodoABorrar.datos = sucesor;
        }
        
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
