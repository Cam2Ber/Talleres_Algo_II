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

        private T maximoNodo(){
            Nodo buscador = this;
            while (buscador.derecha != null){
                buscador = buscador.derecha;
            }
            return buscador.datos;
        }

        private boolean EsMenor(T elem){
            return (this.datos.compareTo(elem) > 0);
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
        return this.root.minimoNodo();
    }

    public T maximo(){
        return this.root.maximoNodo();
    }

    public void insertar(T elem){
        if (this.tamano == 0) {
            this.root = new Nodo(elem);
            this.tamano = this.tamano+1;
        }
        else {
            this.BuscarMeter(elem);
        }
    }

    private void BuscarMeter(T elem){
        Nodo nodoActual = this.root;
        while (nodoActual.datos != elem) {
                if (nodoActual.EsMenor(elem)) {
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

    private Nodo pertenece_Nodo(T elem){
        Nodo nodoActual = this.root;
        while ((nodoActual != null) && (nodoActual.datos != elem)) {
            if (nodoActual.EsMenor(elem)) {
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
        if (Caso == 0){
            this.tamano = this.tamano-1;
            if (this.root.datos != nodoABorrar.datos){
                if (nodoABorrar.Padre.EsMenor(nodoABorrar.datos)){
                    nodoABorrar.Padre.izquierda = null;
                }
                else{
                    nodoABorrar.Padre.derecha = null;
                }
            }
            else{
                this.root.datos = null;
            }
        }
        if (Caso == 1){
            this.tamano = this.tamano-1;
            Caso1(nodoABorrar);
        }
        if (Caso == 2){
            T sucesor = nodoABorrar.derecha.minimoNodo();
            this.eliminar(sucesor);
            nodoABorrar.datos = sucesor;
        }
        
    }

    private void Caso1(Nodo nodoABorrar){
            if (nodoABorrar.izquierda != null){
                nodoABorrar.datos = nodoABorrar.izquierda.datos;
                if (nodoABorrar.izquierda.derecha != null){
                    nodoABorrar.derecha = nodoABorrar.izquierda.derecha;
                }
                nodoABorrar.izquierda = nodoABorrar.izquierda.izquierda;
            }
            else{
                nodoABorrar.datos = nodoABorrar.derecha.datos;
                if (nodoABorrar.derecha.izquierda != null){
                    nodoABorrar.izquierda = nodoABorrar.derecha.izquierda;
                }
                nodoABorrar.derecha = nodoABorrar.derecha.derecha;
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
