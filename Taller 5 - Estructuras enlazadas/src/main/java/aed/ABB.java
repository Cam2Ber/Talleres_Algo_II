package aed;

public class ABB<T extends Comparable<T>> {
    private Nodo root;
    private int tamano;

    private class Nodo {
        T valor;
        Nodo I; //Izquierda
        Nodo D; //Derecha
        Nodo A; //Arriba

        private Nodo(T elem){
            this.valor = elem;
        }

        private Nodo BuscadorNodal(T elem){
            Nodo Buscador = this;
            boolean noEsta = false;
            while ((!noEsta) && (Buscador.valor.compareTo(elem) != 0)){
                if (Buscador.valor.compareTo(elem) > 0){
                    if (Buscador.I != null){
                        Buscador = Buscador.I;
                    }
                    else{
                        noEsta = true;
                    }
                }
                else{
                    if (Buscador.D != null){
                        Buscador = Buscador.D;
                    }
                    else{
                        noEsta = true;
                    }
                }
            }
            return Buscador;
        }
    }

    public class HandleABB {
        Nodo nodoReferenciado;

        private HandleABB(Nodo referencia){
            this.nodoReferenciado = referencia;
        }

        public T valor(){
            return this.nodoReferenciado.valor;
        }
    }

    public ABB() {
        this.tamano = 0;
    }

    public T minimo(){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public HandleABB insertar(T elem){
        Nodo Buscador = this.root.BuscadorNodal(elem);
        if (Buscador.valor.compareTo(elem) != 0){
            Nodo insertado = new Nodo(elem);
            insertado.D = Buscador;
            if (Buscador.valor.compareTo(elem) > 0){
                Buscador.I = insertado;
            }
            else{
                Buscador.D = insertado;
            }
            Buscador = insertado;
        }
        return new HandleABB(Buscador);
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
