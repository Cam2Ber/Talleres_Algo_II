package aed;

public class ABB<T extends Comparable<T>> {
    private Nodo root;

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

        private Nodo minimoNodal(){
            Nodo Buscador = this;
            while (Buscador.I != null){
                Buscador = Buscador.I;
            }
            return Buscador;
        }

        private int Cantidad_de_hijos(){
            int res = 0;
            if (this.D != null){
                res = res+1;
            }
            if (this.I != null){
                res = res+1;
            }
            return res;
        }

        private void BorradorNodal(){
            int caso = this.Cantidad_de_hijos();
            if (caso == 0){
                if (this.A != null){
                    if (this.A.valor.compareTo(this.valor) > 0){
                        this.A.I = null;
                    }
                    else{
                        this.A.D = null;
                    }
                    this.A = null;
                }
                this.valor = null;
            }
            if (caso == 1){
                Nodo Hijo = this;
                if (this.I != null){
                    Hijo = this.I;
                }
                else{
                    Hijo = this.D;
                }
                if (this.A != null){
                    Hijo.A = this.A;
                    if (this.A.valor.compareTo(this.valor) > 0){
                        this.A.I = Hijo;
                    }
                    else{
                        this.A.D = Hijo;
                    }
                }
                else{
                    root = Hijo;
                    Hijo.A = null;
                }
            }
            if (caso == 2){
                Nodo minimo = this.D.minimoNodal();
                this.valor = minimo.valor;
                minimo.BorradorNodal();
            }
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

        public void Borrar(){
            this.nodoReferenciado.BorradorNodal();
        }
    }

    public ABB() {
        this.root = null;
    }

    public T minimo(){
        return this.root.minimoNodal().valor;
    }

    public HandleABB insertar(T elem){
        Nodo Buscador = this.root;
        if ((this.root == null) || (this.root.valor == null)){
            Buscador = new Nodo(elem);
            this.root = Buscador;
        }
        else{
            Buscador = Buscador.BuscadorNodal(elem);
            if (Buscador.valor.compareTo(elem) != 0){
                Nodo insertado = new Nodo(elem);
                insertado.A = Buscador;
                if (Buscador.valor.compareTo(elem) > 0){
                    Buscador.I = insertado;
                }
                else{
                    Buscador.D = insertado;
                }
                Buscador = insertado;
            }
        }
        HandleABB Palanca = new HandleABB(Buscador);
        return Palanca;
    }

    public boolean pertenece(T elem){
        Nodo Buscador = this.root.BuscadorNodal(elem);
        boolean res = (Buscador.valor.compareTo(elem) == 0);
        return res;
    }

    public void eliminar(T elem){
        Nodo Buscador = this.root.BuscadorNodal(elem);
        if (Buscador.valor.compareTo(elem) == 0){
            Buscador.BorradorNodal();
        }
    }

    @Override
    public String toString(){
        String texto = "{";
        ABB_Iterador iterador = this.iterador();
        if (iterador.haySiguiente()){
            texto = texto+iterador.siguiente();
        }
        while (iterador.haySiguiente()){
            texto = texto+","+iterador.siguiente();
        }
        texto = texto+"}";
        return texto;
    }

    public class ABB_Iterador {
        private Nodo _actual;

        private ABB_Iterador(){
            if (root == null){
                _actual = null;
            }
            else{
                _actual = root.minimoNodal();
            }
        }

        public boolean haySiguiente() {
            return (this._actual != null);
        }
    
        public T siguiente() {
            T res = this._actual.valor;
            if (this._actual.D != null){
                this._actual = this._actual.D.minimoNodal();
            }
            else{
                Nodo Buscador = this._actual.A;
                boolean Encontrado = false;
                while ((!Encontrado) && (Buscador != null)){
                    if (Buscador.valor.compareTo(this._actual.valor) > 0){
                        Encontrado = true;
                    }
                    else{
                        Buscador = Buscador.A;
                    }
                }
                this._actual = Buscador;
            }
            return res;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
