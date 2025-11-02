package aed;

public class SistemaPedidos {
    private ListaEnlazada<ABB<Pedido>.HandleABB> Lista_Pedidos; //En orden de llegada
    private ABB<Pedido> Arbol_Pedidos; //En orden de ID

    public SistemaPedidos(){
        this.Lista_Pedidos = new ListaEnlazada<ABB<Pedido>.HandleABB>();
        this.Arbol_Pedidos = new ABB<Pedido>();
    }

    public void agregarPedido(Pedido pedido){
        ABB<Pedido>.HandleABB Manija = this.Arbol_Pedidos.insertar(pedido);
        this.Lista_Pedidos.agregarAtras(Manija);
    }

    public Pedido proximoPedido(){
        ABB<Pedido>.HandleABB manija = Lista_Pedidos.obtener(0);
        Pedido res = manija.valor();
        Lista_Pedidos.eliminar(0);
        manija.eliminar();
        return res;
    }

    public Pedido pedidoMenorId(){
        return this.Arbol_Pedidos.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        return this.Lista_Pedidos.toString();
    }

    public String obtenerPedidosOrdenadosPorId(){
        return this.Arbol_Pedidos.toString();
    }
}