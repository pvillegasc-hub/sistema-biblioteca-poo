package modelo;

/**
 * Clase Libro - Abstracción de un libro real
 * Encapsulamiento: atributos privados, métodos públicos
 */
public class Libro {
    // Atributos encapsulados
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    
    // Constructor
    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;  // Por defecto disponible
    }
    
    // Métodos públicos
    public boolean prestar() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }
    
    public void devolver() {
        disponible = true;
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public boolean isDisponible() { return disponible; }
    
    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + anioPublicacion + ")";
    }
}