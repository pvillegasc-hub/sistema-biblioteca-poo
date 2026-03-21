package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Biblioteca - Clase principal que gestiona todo
 * Demuestra Composición (contiene listas de libros, usuarios, préstamos)
 */
public class Biblioteca {
    private String nombre;
    private List<Libro> libros;      // Composición
    private List<Usuario> usuarios;  // Composición
    private List<Prestamo> prestamos; // Composición
    
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    // Gestión de Libros
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("✓ Libro agregado: " + libro.getTitulo());
    }
    
    public List<Libro> buscarLibros(String criterio) {
        return libros.stream()
            .filter(l -> l.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                         l.getAutor().toLowerCase().contains(criterio.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public List<Libro> listarLibrosDisponibles() {
        return libros.stream()
            .filter(Libro::isDisponible)
            .collect(Collectors.toList());
    }
    
    // Gestión de Usuarios
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("✓ Usuario registrado: " + usuario.getNombre());
    }
    
    public Usuario buscarUsuario(String id) {
        return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
    
    // Gestión de Préstamos (Asociación)
    public boolean realizarPrestamo(String isbn, String usuarioId) {
        // Buscar libro
        Libro libro = libros.stream()
            .filter(l -> l.getIsbn().equals(isbn) && l.isDisponible())
            .findFirst()
            .orElse(null);
        
        if (libro == null) {
            System.out.println("✗ Libro no disponible");
            return false;
        }
        
        // Buscar usuario
        Usuario usuario = buscarUsuario(usuarioId);
        if (usuario == null) {
            System.out.println("✗ Usuario no encontrado");
            return false;
        }
        
        if (!usuario.puedePedirPrestamo()) {
            System.out.println("✗ Usuario excedió límite de préstamos");
            return false;
        }
        
        // Realizar préstamo
        libro.prestar();
        usuario.incrementarPrestamos();
        Prestamo prestamo = new Prestamo(libro, usuario);
        prestamos.add(prestamo);
        
        System.out.println("✓ Préstamo realizado: " + prestamo);
        return true;
    }
    
    public boolean devolverLibro(int prestamoId) {
        Prestamo prestamo = prestamos.stream()
            .filter(p -> p.getId() == prestamoId && p.getEstado().equals("ACTIVO"))
            .findFirst()
            .orElse(null);
        
        if (prestamo == null) {
            System.out.println("✗ Préstamo no encontrado o ya devuelto");
            return false;
        }
        
        long multa = prestamo.calcularMulta();
        prestamo.devolver();
        
        if (multa > 0) {
            System.out.println("⚠ Multa por retraso: $" + multa);
        }
        
        System.out.println("✓ Libro devuelto: " + prestamo.getLibro().getTitulo());
        return true;
    }
    
    public void mostrarEstado() {
        System.out.println("\n=== " + nombre + " ===");
        System.out.println("Libros totales: " + libros.size());
        System.out.println("Libros disponibles: " + listarLibrosDisponibles().size());
        System.out.println("Usuarios registrados: " + usuarios.size());
        System.out.println("Préstamos activos: " + prestamos.stream().filter(p -> p.getEstado().equals("ACTIVO")).count());
        System.out.println("================\n");
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public List<Libro> getLibros() { return libros; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Prestamo> getPrestamos() { return prestamos; }
}