package aed;

public class ListaEnlazada<T> {
    private int longitud;
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo Next;
        Nodo Back;

        public Nodo(T elem) {
            this.valor = elem;
        }
    }

    public ListaEnlazada() {
        this.longitud = 0;
    }

    public int longitud() {
        return this.longitud;
    }

    private boolean agregarPrimero(Nodo elem){
        boolean res = false;
        if (this.longitud == 0){
            this.longitud = this.longitud+1;
            this.primero = elem;
            this.ultimo = this.primero;
            res = true;
        }
        return res;
    }

    private void agregar_BIdireccional(T elem, boolean adelante){ //La primera función del mes del orgullo, en algun lado, A partir de la coma paso una semana, matate.
        Nodo ParaAgregar = new Nodo(elem); //O(1)
        boolean Vacio = agregarPrimero(ParaAgregar);
        if (!Vacio){
            this.longitud = this.longitud+1;
            if (adelante){
                ParaAgregar.Next = this.primero;
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

    public void agregarAdelante(T elem) {
        agregar_BIdireccional(elem, true);
    }

    public void agregarAtras(T elem) {
        agregar_BIdireccional(elem, false);
    }

    private Nodo buscadorNodal(int i){
        Nodo Buscador = this.primero;
        for(int j = 0; j < i; j++){
            Buscador = Buscador.Next;
        }
        return Buscador;
    }

    public T obtener(int i) {
        return buscadorNodal(i).valor;
    }

    public void eliminar(int i) {
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

    public void modificarPosicion(int indice, T elem) {
        buscadorNodal(indice).valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this.longitud = lista.longitud;
        ListaIterador iter = lista.iterador();
        while (iter.haySiguiente()){
            T elem = iter.siguiente();
            this.agregarAtras(elem);
        }
    }
    
    @Override
    public String toString() {
        String res = "[";
        ListaIterador iter = this.iterador();
        if (iter.haySiguiente()){
            res = res+iter.siguiente().toString();
        }
        while (iter.haySiguiente()){
            res = res+", "+iter.siguiente().toString();
        }
        res = res+"]";
        return res;
    }

    public class ListaIterador{
    	private Nodo NodoActual;
        private boolean EstoySobreNull;

        private ListaIterador(Nodo Actual){
            this.NodoActual = Actual;
            this.EstoySobreNull = ((Actual == null) || (Actual.valor == null));
        }

        public boolean haySiguiente() {
	        return (!this.EstoySobreNull);
        }
        
        public boolean hayAnterior() {
	        return (this.NodoActual.Back != null);
        }

        public T siguiente() {
	        T valor = this.NodoActual.valor;
            if (this.NodoActual.Next != null){
                this.NodoActual = this.NodoActual.Next;
            }
            else{
                this.EstoySobreNull = true;
            }
            return valor;
        }
        

        public T anterior() {
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
