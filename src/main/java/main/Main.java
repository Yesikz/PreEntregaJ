package main;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        agregarProductosDeEjemplo(productos);
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
            ⭐ Brillo de Luna Nails ⭐
            1. Agregar producto
            2. Listar productos
            3. Buscar
            4. Actualizar producto
            5. Eliminar producto
            6. Crear pedido
            7. Ver pedidos
            8. Salir
            Elija una opción:
            """);

            opcion = entrada.nextInt();
            entrada.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarProducto(productos);
                case 2 -> listarProductos(productos);
                case 3 -> buscarProducto(productos);
                case 4 -> actualizarProducto(productos);
                case 5 -> eliminarProducto(productos);
                case 6 -> crearPedido(productos, pedidos);
                case 7 -> listarPedidos(pedidos);
                case 8 -> System.out.println("👋 Gracias por usar el sistema.");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 8);
    }

    public static void agregarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("🔧 Agregar nuevo producto:");
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Precio: $");
        double precio = entrada.nextDouble();
        System.out.print("Stock inicial: ");
        int stock = entrada.nextInt();

        productos.add(new Producto(nombre, precio, stock));
        System.out.println("✅ Producto agregado correctamente.");
    }

    public static void listarProductos(ArrayList<Producto> productos) {
        System.out.println("📦 Listado de productos disponibles:");
        if (productos.isEmpty()) {
            System.out.println(" ⚠ No hay productos cargados.");
        } else {
            for (Producto p : productos) {
                p.mostrarInfo();
            }
        }
    }


    public static void buscarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("🔍 Ingrese nombre o parte del nombre del producto: ");
        String busqueda = entrada.nextLine();

        boolean encontrado = false;

        for (Producto p : productos) {
            if (p.contieneNombre(busqueda)) {
                p.mostrarInfo();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("⚠ No se encontró ningún producto con ese nombre.");
        }
    }

    public static void actualizarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("🖊 Ingrese ID del producto a actualizar: ");
        int id = entrada.nextInt();
        entrada.nextLine(); // limpiar

        Producto productoEncontrado = null;

        for (Producto p : productos) {
            if (p.getId() == id) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado != null) {
            productoEncontrado.mostrarInfo();
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = entrada.nextLine();
            System.out.print("Nuevo precio: ");
            double nuevoPrecio = entrada.nextDouble();
            System.out.print("Nuevo stock: ");
            int nuevoStock = entrada.nextInt();

            productoEncontrado.setNombre(nuevoNombre);
            productoEncontrado.setPrecio(nuevoPrecio);
            productoEncontrado.setStock(nuevoStock);

            System.out.println("✅ Producto actualizado correctamente.");
        } else {
            System.out.println("❌ No se encontró producto con ese ID.");
        }
    }
    public static void eliminarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int idEliminar = entrada.nextInt();

        Producto productoAEliminar = null;
        for (Producto p : productos) {
            if (p.getId() == idEliminar) {
                productoAEliminar = p;
                break;
            }
        }

        if (productoAEliminar != null) {
            productoAEliminar.mostrarInfo();
            System.out.println("¿Está seguro que desea eliminar este producto? (1-Sí / 2-No)");
            int confirmacion = entrada.nextInt();
            if (confirmacion == 1) {
                productos.remove(productoAEliminar);
                System.out.println("🗑 Producto eliminado correctamente.");
            } else {
                System.out.println("❌ Acción cancelada.");
            }
        } else {
            System.out.println("⚠ No se encontró producto con ese ID.");
        }
    }

    public static void crearPedido(ArrayList<Producto> productos, ArrayList<Pedido> pedidos) {
        Scanner entrada = new Scanner(System.in);
        Pedido nuevoPedido = new Pedido();

        while (true) {
            listarProductos(productos);
            System.out.print("Ingrese el ID del producto a agregar (0 para finalizar): ");
            int idProducto = entrada.nextInt();

            if (idProducto == 0) break;

            Producto productoSeleccionado = null;
            for (Producto p : productos) {
                if (p.getId() == idProducto) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado != null) {
                System.out.print("Cantidad a agregar: ");
                int cantidad = entrada.nextInt();
                if (cantidad > 0 && cantidad <= productoSeleccionado.getStock()) {
                    nuevoPedido.agregarProducto(productoSeleccionado, cantidad);
                    System.out.println("🛒 Producto agregado al pedido.");
                } else {
                    System.out.println("⚠ Cantidad no válida o stock insuficiente.");
                }
            } else {
                System.out.println("⚠ No se encontró el producto.");
            }
        }

        if (!nuevoPedido.estaVacio()) {
            pedidos.add(nuevoPedido);
            System.out.println("✅ Pedido creado exitosamente.");
        } else {
            System.out.println("⚠ Pedido vacío, no se guardó.");
        }
    }

    public static void listarPedidos(ArrayList<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("🚩 No hay pedidos cargados.");
        } else {
            int i = 1;
            for (Pedido pedido : pedidos) {
                System.out.println("📜 Pedido #" + i++);
                pedido.mostrarPedido();
                System.out.println("-----------------------------");
            }
        }
    }

    public static void agregarProductosDeEjemplo(ArrayList<Producto> productos) {
        productos.add(new Producto("Esmalte rojo", 1200, 20));
        productos.add(new Producto("Lima profesional", 800, 30));
        productos.add(new Producto("Removedor de cutículas", 950, 15));
        productos.add(new Producto("Base fortalecedora", 1100, 10));
        productos.add(new Producto("Top coat brillante", 1300, 12));
        productos.add(new Producto("Cepillo limpiador", 700, 18));
        productos.add(new Producto("Aceite para cutículas", 850, 25));
        productos.add(new Producto("Pincel para nail art", 950, 22));
        productos.add(new Producto("Gel para uñas", 1500, 17));
        productos.add(new Producto("Removedor de esmalte sin acetona", 900, 20));
        productos.add(new Producto("Mascarilla para manos", 1250, 14));
        productos.add(new Producto("Guantes exfoliantes", 700, 30));
        productos.add(new Producto("Secador de uñas portátil", 4500, 8));
        productos.add(new Producto("Corrector de esmalte", 650, 18));
        productos.add(new Producto("Kit de decoración de uñas", 2000, 12));
        productos.add(new Producto("Toallas desechables", 500, 40));
        productos.add(new Producto("Alcohol en gel", 1100, 35));
        productos.add(new Producto("Palitos de naranja", 400, 50));
        productos.add(new Producto("Lima eléctrica", 3800, 10));
        productos.add(new Producto("Esmalte mate", 1300, 15));
        productos.add(new Producto("Base endurecedora", 1400, 13));
        productos.add(new Producto("Piedras para uñas", 750, 25));
        productos.add(new Producto("Pintura para nail art", 850, 20));
        productos.add(new Producto("Pegamento para uñas postizas", 950, 15));
        productos.add(new Producto("Esmalte con glitter", 1200, 18));
        productos.add(new Producto("Laca secante rápida", 1100, 22));
        productos.add(new Producto("Removedor de esmalte en gel", 1300, 12));
        productos.add(new Producto("Aceite hidratante para uñas", 900, 28));
        productos.add(new Producto("Set de limas descartables", 700, 35));
        productos.add(new Producto("Cepillo para polvo acrílico", 600, 20));
        productos.add(new Producto("Base para uñas acrílicas", 1400, 14));
    }
}


