package aed;

public class ABB<T extends Comparable<T>> {
    private Nodo root;
    private int cantidad;

    private class Nodo {
        T valor;
        Nodo I; //Izquierda
        Nodo D; //Derecha
        Nodo A; //Arriba
    }

    public class HandleABB {
        private 
    }

    public ABB() {
        throw new UnsupportedOperationException("No implementado aún");
    }

    public T minimo(){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public HandleABB insertar(T elem){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public boolean pertenece(T elem){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementado aún");
    }

    @Override
    public String toString(){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public class ABB_Iterador {
        /* ¡COMPLETAR! */
    }

    public ABB_Iterador iterador() {
        throw new UnsupportedOperationException("No implementado aún");
    }

}
