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

        private Nodo BuscadorNodal(T elem){ //Caso Promedio: O(log(n))
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
            tamano = tamano-1;
            int caso = this.Cantidad_de_hijos();
            if (caso == 0){ //O(1), muchas comparaciones y asignaciones
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
            if (caso == 1){ //O(1), muchas comparaciones y asignaciones
                Nodo Hijo = this.D;
                if (this.I != null){
                    Hijo = this.I;
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
            if (caso == 2){ //log(n)
                Nodo minimo = this.D.minimoNodal(); //log(n)
                if (this.D.valor.compareTo(minimo.valor) != 0){
                    minimo.A.I = minimo.D;
                }
                else{
                    minimo.A.D = minimo.D;
                }
                minimo.D = this.D;
                minimo.I = this.I;
                minimo.I.A = minimo;
                minimo.A = this.A;
                if (this.A != null){
                    if (this.A.valor.compareTo(this.valor) > 0){
                        this.A.I = minimo;
                    }
                    else{
                        this.A.D = minimo;
                    }
                }
                else{
                    root = minimo;
                }
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

        public void eliminar(){
            this.nodoReferenciado.BorradorNodal(); //O(log(n)) en el peor de los casos(Se borra uno con dos hijos), en el mejor de los casos es O(1)
        }

        @Override
        public String toString(){
            return this.nodoReferenciado.valor.toString(); //Tuve que hacerlo así porque si no, no imprimía bien el valor, ni idea la complejidad, asumo que lo vamos a interpretar como O(1)
        }
    }

    public ABB() { //O(1)
        this.root = null;
    }

    public int cardinal(){ //O(1)
        return this.tamano;
    }

    public T minimo(){ //O(log(n))
        return this.root.minimoNodal().valor;
    }

    public T maximo(){
        Nodo Buscador = this.root;
        while (Buscador.D != null){
            Buscador = Buscador.D;
        }
        return Buscador.valor;
    }

    public HandleABB insertar(T elem){ //Obj;O(log(n)) res:O(log(n))
        Nodo Buscador = this.root;
        if ((this.root == null) || (this.root.valor == null)){ //Esta ruta tomaría O(1)?
            Buscador = new Nodo(elem);
            this.root = Buscador;
            this.tamano = 1;
        }
        else{ //O(log(n))
            Buscador = Buscador.BuscadorNodal(elem); //O(log(n))
            if (Buscador.valor.compareTo(elem) != 0){
                Nodo insertado = new Nodo(elem); //O(1)
                insertado.A = Buscador;
                if (Buscador.valor.compareTo(elem) > 0){
                    Buscador.I = insertado;
                }
                else{
                    Buscador.D = insertado;
                }
                Buscador = insertado;
                this.tamano = this.tamano+1;
            }
        }
        HandleABB Palanca = new HandleABB(Buscador);
        return Palanca;
    }

    public boolean pertenece(T elem){ //Obj:O(log(n)), res:O(log(n))
        Nodo Buscador = this.root.BuscadorNodal(elem);
        boolean res = (Buscador.valor.compareTo(elem) == 0);
        return res;
    }

    public void eliminar(T elem){ //Obj:O(log(n)), res:O(log(n))+O(log(n))=O(log(n))
        Nodo Buscador = this.root.BuscadorNodal(elem); //O(log(n))
        if (Buscador.valor.compareTo(elem) == 0){
            Buscador.BorradorNodal(); //O(log(n))
        }
    }

    @Override
    public String toString(){ //O(nlog(n)) por definición armar el iterador toma log(n), despues no se como hacer que buscar el siguiente tome tiempo constante, y para cumplir lo pedido tiene que tomar tiempo constante ya que se tiene que repetir n veces para poder conseguir todos los valores del arbol 
        String texto = "{";
        ABB_Iterador iterador = this.iterador(); //O(log(n))
        if (iterador.haySiguiente()){
            texto = texto+iterador.siguiente(); //O(log(n)), pero se repite n veces
        }
        while (iterador.haySiguiente()){
            texto = texto+", "+iterador.siguiente();
        }
        texto = texto+"}";
        return texto;
    }

    public class ABB_Iterador {
        private Nodo _actual;

        private ABB_Iterador(){
            if (root == null){
                _actual = null; //O(1)
            }
            else{
                _actual = root.minimoNodal(); //O(log(n))
            }
        }

        public boolean haySiguiente() {
            return ((this._actual != null) && (this._actual.valor != null));
        }
    
        public T siguiente() {
            T res = this._actual.valor;
            if (this._actual.D != null){
                this._actual = this._actual.D.minimoNodal(); //Pensandolo en seco, esto se siente mejor que un log(n), ya que aun si lo estas haciendo desde el root, seguis dividiendo al ir por la derecha antes de hacer log (lo que sería log(n/2)), pero creo que asintoticamente sigue siendo log(n), así que lo voy a calcular así
            }
            else{
                Nodo Buscador = this._actual.A;
                boolean Encontrado = false;
                while ((!Encontrado) && (Buscador != null)){ //La peor situación en un caso promedio (ie. asumimos que el arbol esta balanceado) es si estas en el maximo, que tenes que recorrer devuelta hasta el root+1, que emocionalmente se siente mejor que log(n) (asumiendo que el arbol esta balanceado), pero creo que asintoticamente sigue siendo log(n)
                    if (Buscador.valor.compareTo(this._actual.valor) > 0){
                        Encontrado = true; //El primer padre que es mayor que el nodo actual va a ser necesariamente el que esta mas cerca por encima al nodo actual, ya que, si asumimos que existe un nodo mayor más cercano al nodo actual, el mismo tendría que estar en ruta directa hacía arriba del nodo actual, ya que si asumimos lo contrario, eso significaría que es menor que un nodo menor al nodo actual (rompiendo la hipotesis de mayor), o mayor a un nodo que es mayor al nodo actual (rompiendo la hipotesis de cercanía), por lo que todo nodo "siguiente" tiene que estar a la derecha del nodo actual, o por arriba de manera directa, en caso de que no haya nodo derecho.
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
