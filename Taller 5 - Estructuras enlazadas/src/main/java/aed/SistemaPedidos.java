package aed;

public class SistemaPedidos {
    private ListaEnlazada<ABB<Pedido>.HandleABB> Lista_Pedidos; //En orden de llegada
    private ABB<Pedido> Arbol_Pedidos; //En orden de ID

    public SistemaPedidos(){
        this.Lista_Pedidos = new ListaEnlazada<ABB<Pedido>.HandleABB>();
        this.Arbol_Pedidos = new ABB<Pedido>();
    }

    public void agregarPedido(Pedido pedido){ //O(log(n))
        ABB<Pedido>.HandleABB Manija = this.Arbol_Pedidos.insertar(pedido);
        this.Lista_Pedidos.agregarAtras(Manija);
    }

    public Pedido proximoPedido(){ //O(log(n))
        ABB<Pedido>.HandleABB manija = Lista_Pedidos.obtener(0); //O(1), ya que estamos accediendo siempre al mismo elemento, el primero de todos, que se puede acceder en O(1)
        Pedido res = manija.valor(); //O(1)
        Lista_Pedidos.eliminar(0); //O(1), ya que estamos accediendo siempre al mismo elemento, el primero de todos, que se puede acceder en O(1)
        manija.eliminar(); //O(log(n))
        return res;
    }

    public Pedido pedidoMenorId(){ //O(log(n))
        return this.Arbol_Pedidos.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){ //O(n)
        return this.Lista_Pedidos.toString();
    }

    public String obtenerPedidosOrdenadosPorId(){ //O(n*log(n))
        return this.Arbol_Pedidos.toString();
    }
}