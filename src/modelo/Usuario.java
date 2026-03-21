package modelo;

/**
 * Clase Usuario - Representa a una persona que usa la biblioteca
 */
public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private int prestamosActivos;
    
    public Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.prestamosActivos = 0;
    }
    
    public boolean puedePedirPrestamo() {
        return prestamosActivos < 5;  // Máximo 5 libros
    }
    
    public void incrementarPrestamos() {
        prestamosActivos++;
    }
    
    public void decrementarPrestamos() {
        if (prestamosActivos > 0) {
            prestamosActivos--;
        }
    }
    
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public int getPrestamosActivos() { return prestamosActivos; }
    
    @Override
    public String toString() {
        return nombre + " (ID: " + id + ")";
    }
}