package aed;

public class ListaEnlazada<T> {
    private int longitud;
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo Next;
        Nodo Back;

        public Nodo(T elem) {//O(1)
            this.valor = elem;
        }
    }

    public ListaEnlazada() { //Obj: O(1), Res: O(1)
        this.longitud = 0;
    }

    public int longitud() { //Obj: O(1), Res:O(1)
        return this.longitud;
    }

    private boolean agregarPrimero(Nodo elem){ //O(1) son todas asignaciones y comparaciones
        boolean res = false;
        if (this.longitud == 0){
            this.longitud = this.longitud+1;
            this.primero = elem;
            this.ultimo = this.primero;
            res = true;
        }
        return res;
    }

    private void agregar_BIdireccional(T elem, boolean adelante){ //La primera función del mes del orgullo, en algun lado.
        Nodo ParaAgregar = new Nodo(elem); //O(1)
        boolean Vacio = agregarPrimero(ParaAgregar);
        if (!Vacio){
            this.longitud = this.longitud+1;
            if (adelante){
                ParaAgregar.Next = this.primero; //Como me guardo una referencia al primero, esto es O(1)
                this.primero.Back = ParaAgregar;
                this.primero = ParaAgregar;
            }
            else{
                ParaAgregar.Back = this.ultimo; //Como me guardo una referencia al ultimo, esto es O(1)
                this.ultimo.Next = ParaAgregar;
                this.ultimo = ParaAgregar;
            }
        }

    }

    public void agregarAdelante(T elem) { //Obj: O(1), res:O(1)
        agregar_BIdireccional(elem, true);
    }

    public void agregarAtras(T elem) { //Obj: O(1), res:O(1)
        agregar_BIdireccional(elem, false);
    }

    private Nodo buscadorNodal(int i){
        Nodo Buscador = this.primero;
        for(int j = 0; j < i; j++){ //Peor de los casos, tiene que recorrer la lista entera, es O(n)
            Buscador = Buscador.Next;
        }
        return Buscador;
    }

    public T obtener(int i) { //Obj: O(n), res:O(n)
        return buscadorNodal(i).valor;
    }

    public void eliminar(int i) { //Obj: O(n), res:O(n), que viene principalmente de la busqueda, el resto son asignaciones y comparaciones
        Nodo nodoABorrar = buscadorNodal(i);
        if (nodoABorrar.Back != null){
            nodoABorrar.Back.Next = nodoABorrar.Next;
        }
        else{ //Si este Nodo no tiene un Anterior, significa que es el primero
            this.primero = nodoABorrar.Next;
        }
        if (nodoABorrar.Next != null){
            nodoABorrar.Next.Back = nodoABorrar.Back;
        }
        else{ //De la misma manera, si este Nodo no tiene un siguiente, significa que es el ultimo
            this.ultimo = nodoABorrar.Back;
        }
        this.longitud = this.longitud-1;
    }

    public void modificarPosicion(int indice, T elem) { //Obj:O(n), res:O(n)
        buscadorNodal(indice).valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {//Se insertan n elementos, insertar es O(1) => O(n)
        this.longitud = lista.longitud;
        ListaIterador iter = lista.iterador();
        while (iter.haySiguiente()){
            T elem = iter.siguiente();
            this.agregarAtras(elem);
        }
    }
    
    @Override
    public String toString() { //O(n)
        String res = "[";
        ListaIterador iter = this.iterador();
        if (iter.haySiguiente()){
            res = res+iter.siguiente().toString();
        }
        while (iter.haySiguiente()){
            res = res+", "+iter.siguiente().toString(); //O(1), n veces
        }
        res = res+"]";
        return res;
    }

    public class ListaIterador{
    	private Nodo NodoActual;
        private boolean EstoySobreNull;

        private ListaIterador(Nodo Actual){ //O(1)
            this.NodoActual = Actual;
            this.EstoySobreNull = ((Actual == null) || (Actual.valor == null));
        }

        public boolean haySiguiente() { //O(1)
	        return (!this.EstoySobreNull);
        }
        
        public boolean hayAnterior() { //O(1)
	        return (this.NodoActual.Back != null);
        }

        public T siguiente() { //O(1)
	        T valor = this.NodoActual.valor;
            if (this.NodoActual.Next != null){
                this.NodoActual = this.NodoActual.Next;
            }
            else{
                this.EstoySobreNull = true;
            }
            return valor;
        }
        

        public T anterior() { //O(1)
            T valor = this.NodoActual.Back.valor;
	        if (this.EstoySobreNull){
                valor = this.NodoActual.valor;
                this.EstoySobreNull = false;
            }
            this.NodoActual = this.NodoActual.Back;
            return valor;
        }
    }

    public ListaIterador iterador() {
        ListaIterador res = new ListaIterador(this.primero);
        return res;
    }

}
