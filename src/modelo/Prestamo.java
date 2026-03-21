package modelo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Clase Prestamo - Relación entre Usuario y Libro
 * Demuestra Agregación (el libro y usuario existen independientemente)
 */
public class Prestamo {
    private static int contador = 0;
    private int id;
    private Libro libro;      // Agregación
    private Usuario usuario;  // Agregación
    private Date fechaPrestamo;
    private Date fechaDevolucionLimite;
    private String estado;    // "ACTIVO", "DEVUELTO"
    
    public Prestamo(Libro libro, Usuario usuario) {
        this.id = ++contador;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = new Date();
        this.estado = "ACTIVO";
        
        // Fecha límite: 15 días después
        this.fechaDevolucionLimite = new Date(fechaPrestamo.getTime() + (15L * 24 * 60 * 60 * 1000));
    }
    
    public long calcularDiasRetraso() {
        Date hoy = new Date();
        if (hoy.after(fechaDevolucionLimite)) {
            long diff = hoy.getTime() - fechaDevolucionLimite.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }
    
    public long calcularMulta() {
        long diasRetraso = calcularDiasRetraso();
        return diasRetraso * 500;  // $500 por día
    }
    
    public void devolver() {
        this.estado = "DEVUELTO";
        this.libro.devolver();
        this.usuario.decrementarPrestamos();
    }
    
    // Getters
    public int getId() { return id; }
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public Date getFechaDevolucionLimite() { return fechaDevolucionLimite; }
    public String getEstado() { return estado; }
    
    @Override
    public String toString() {
        return "Préstamo #" + id + ": " + libro.getTitulo() + " → " + usuario.getNombre();
    }
}