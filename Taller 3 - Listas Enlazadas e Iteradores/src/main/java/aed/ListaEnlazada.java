package aed;

public class ListaEnlazada<T> {
    // Completar atributos privados
    private int longitud;
    private Nodo Primero;
    private Nodo Ultimo;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
        // Completar
        public Nodo(T elem) {
            this.valor = elem;
        }
    }

    public ListaEnlazada() {
        this.longitud = 0;
        return;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        if (this.longitud == 0) {
            //Si esta vacio, agrega primer elemento
            this.Primero = new Nodo(elem);
            this.Ultimo = this.Primero;
        }
        else {
            //Si no, agrega un nuevo 'Primer Nodo'
            this.Primero.anterior = new Nodo(elem);
            this.Primero.anterior.siguiente = this.Primero;
            this.Primero = this.Primero.anterior;
        }
        this.longitud = this.longitud+1;
    }

    public void agregarAtras(T elem) {
        if (this.longitud == 0) {
            //Si esta vacio, agrega primer elemento
            this.Primero = new Nodo(elem);
            this.Ultimo = this.Primero;
        }
        else {
        //Si no, agrega un nuevo 'Ultimo Nodo'
        this.Ultimo.siguiente = new Nodo(elem);
        this.Ultimo.siguiente.anterior = this.Ultimo;
        this.Ultimo = this.Ultimo.siguiente;
        }
        this.longitud = this.longitud+1;
    }

    private Nodo NodoEnPos(int i) {
        int posicion = 0;
        Nodo nodoActual = this.Primero;
        while(posicion<this.longitud && posicion < i) {
            nodoActual = nodoActual.siguiente;
            posicion = posicion+1;
        }
        return nodoActual;
    }

    public T obtener(int i) {
        return this.NodoEnPos(i).valor;
    }

    public void eliminar(int i) {
        //Llego al elemento
        Nodo nodoActual = this.NodoEnPos(i);
        //Lo borro
        if (nodoActual.anterior != null) {
            //Que hacer con el anterior
            nodoActual.anterior.siguiente = nodoActual.siguiente;
        }
        else {
            this.Primero = nodoActual.siguiente;
        }
        if (nodoActual.siguiente != null) {
            //Que hacer con el siguiente
            nodoActual.siguiente.anterior = nodoActual.anterior;
        }
        else {
            this.Ultimo = nodoActual.anterior;
        }
        this.longitud = this.longitud-1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoActual = this.NodoEnPos(indice);
        nodoActual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        for(int indice = 0; indice<(lista.longitud); indice++) {
            this.agregarAtras(lista.obtener(indice));
        }
    }
    
    @Override
    public String toString() {
        String lista = "[";
        for (int indice = 0; indice<this.longitud; indice++) {
            if (indice+1 == this.longitud) {
                lista = lista + this.obtener(indice);
            }
            else {
            lista = lista + this.obtener(indice) + ", ";
            }
        }
        return lista + "]";
    }

    public class ListaIterador{
    	private int posicion;

        public boolean haySiguiente() {
	        boolean hay = false;
            if (longitud >= posicion+1) {
                hay = true;
            }
            return hay;
        }
        
        public boolean hayAnterior() {
	        boolean hay = false;
            if (posicion-1 >= 0) {
                hay = true;
            }
            return hay;
        }

        public T siguiente() {
            T elem = obtener(this.posicion);
            this.posicion = this.posicion+1;
	        return elem;
        }
        

        public T anterior() {
            this.posicion = this.posicion-1;
            T elem = obtener(this.posicion);
	        return elem;
        }
    }

    public ListaIterador iterador() {
	    ListaIterador NuevoIterador = new ListaIterador();
        NuevoIterador.posicion = 0;
        return NuevoIterador;
    }

}
