package main;

public class Producto {
    private static int SIGUIENTE_ID = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = SIGUIENTE_ID++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void mostrarInfo(){
        System.out.printf("""
                ID: %d
                Nombre: %s
                Precio: $%.2f
                Stock: %d unidades
                ------------------------
                """, id, nombre, precio, stock);
    }

    public boolean contieneNombre(String busqueda){
        return nombre.toLowerCase().contains(busqueda.toLowerCase());
    }

    // Getters y Setters
    public int getId() {
        return id; }
    public String getNombre() {
        return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    public double getPrecio() {
        return precio; }
    public void setPrecio(double precio) {
        this.precio = precio; }
    public int getStock() {
        return stock; }
    public void setStock(int stock) {
        this.stock = stock; }
}
