package examen;

import modelo.Producto;
import java.io.Serializable;

public class Carrito implements Serializable{
    private int cantidad;
    private Producto producto;

    public Carrito(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto: ").append(producto).append(System.lineSeparator());
        sb.append("Cantidad=").append(cantidad);
        return sb.toString();
    }
}
