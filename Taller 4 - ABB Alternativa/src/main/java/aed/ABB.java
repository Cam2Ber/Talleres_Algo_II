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
        T valorMinimo;
        if (this.izquierda == null){
            valorMinimo = this.root.datos;
        }
        else{
            valorMinimo = this.izquierda.minimo();
        }
        return valorMinimo;
    }

    public T maximo(){
        T valorMaximo;
        if (this.derecha == null){
            valorMaximo = this.root.datos;
        }
        else{
            valorMaximo = this.derecha.maximo();
        }
        return valorMaximo;
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
            ArbolAInsertar.altura = 1;
            ABB<T> Arbol = this;
            while (Arbol.root.datos != elem) {
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
        }
    }

    private ABB<T> perteneceArbol(T elem){
        ABB<T> arbol = this;
        while ((arbol != null) && (arbol.root != null) && (arbol.root.datos != elem)) {
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
        if ((arbol != null) && (arbol.root != null)) {
            res = true;
        }
        return res;
    }

    private boolean esIzquierdo(T elem){
    return (this.root.datos.compareTo(elem) > 0);
    }


    private int cantidad_de_hijos(){
        int res = 0;
        if (this.izquierda != null){
            res = res+1;
        }
        if (this.derecha != null){
            res = res+1;
        }
        return res;
    }

    private ABB<T> reducirAlturaYDevolverArbolConElemento(T elem){
        ABB<T> ArbolAEliminar = this;
        while (ArbolAEliminar.root.datos != elem){
            ArbolAEliminar.altura = ArbolAEliminar.altura-1;
            if (ArbolAEliminar.esIzquierdo(elem)){
                ArbolAEliminar = ArbolAEliminar.izquierda;
            }
            else{
                ArbolAEliminar = ArbolAEliminar.derecha;
            }
        }
        return ArbolAEliminar;
    }

    private void eliminarRoot(){
        int caso = this.cantidad_de_hijos();
        if (caso == 0) {
            if (this.padre == null) {
                this.root = null;
            }
            else{
                if (this.padre.esIzquierdo(this.root.datos)){
                    this.padre.izquierda = null;
                }
                else{
                    this.padre.derecha = null;
                }
            }
        }
        if (caso == 1) {
            if (this.izquierda != null) {
                this.root = this.izquierda.root;
                this.altura = this.izquierda.altura;
                if (this.izquierda.derecha != null){
                    this.derecha = this.izquierda.derecha;
                    this.derecha.padre = this;
                }
                if (this.izquierda.izquierda != null){
                    this.izquierda = this.izquierda.izquierda;
                    this.izquierda.padre = this;
                }
            }
            else{
                this.root = this.derecha.root;
                this.altura = this.derecha.altura;
                if (this.derecha.izquierda != null){
                    this.izquierda = this.derecha.izquierda;
                    this.izquierda.padre = this;
                }
                if (this.derecha.derecha != null){
                    this.derecha = this.derecha.derecha;
                    this.derecha.padre = this;
                }
            }
        }
        if (caso == 2) {
            T sucesor = this.derecha.minimo();
            this.eliminar(sucesor);
            this.root.datos = sucesor;
        }
    }


    public void eliminar(T elem){
        if (this.pertenece(elem)){
            ABB<T> ArbolAEliminar = this.reducirAlturaYDevolverArbolConElemento(elem);
            ArbolAEliminar.eliminarRoot();
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