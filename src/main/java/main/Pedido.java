package main;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos;

    public Pedido() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        producto.setStock(producto.getStock() - cantidad);
        productos.add(new Producto(producto.getNombre(), producto.getPrecio(), cantidad));
    }

    public void mostrarPedido() {
        double total = 0;
        System.out.println("📦 Detalle del Pedido:");
        for (Producto p : productos) {
            System.out.printf("- %s (x%d): $%.2f%n", p.getNombre(), p.getStock(), p.getPrecio() * p.getStock());
            total += p.getPrecio() * p.getStock();
        }
        System.out.printf("💰 Total: $%.2f%n", total);
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }
}