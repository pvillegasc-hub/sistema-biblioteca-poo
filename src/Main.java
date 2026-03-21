import modelo.*;
import servicios.Notificador;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE BIBLIOTECA");
        System.out.println("========================================\n");
        
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        Notificador notificador = new Notificador();
        
        System.out.println("--- AGREGANDO LIBROS ---");
        Libro libro1 = new Libro("978-84-376-0494-7", "Cien años de soledad", "Gabriel García Márquez", 1967);
        Libro libro2 = new Libro("978-84-204-6428-0", "El amor en los tiempos del cólera", "Gabriel García Márquez", 1985);
        Libro libro3 = new Libro("978-84-397-1387-7", "Rayuela", "Julio Cortázar", 1963);
        Libro libro4 = new Libro("978-84-376-0494-7", "Don Quijote de la Mancha", "Miguel de Cervantes", 1605);
        
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        biblioteca.agregarLibro(libro4);
        
        System.out.println("\n--- REGISTRANDO USUARIOS ---");
        Usuario usuario1 = new Usuario("U001", "Ana García", "ana@email.com", "555-1234");
        Usuario usuario2 = new Usuario("U002", "Luis Pérez", "luis@email.com", "555-5678");
        
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);
        
        biblioteca.mostrarEstado();
        
        System.out.println("\n--- REALIZANDO PRÉSTAMOS ---");
        biblioteca.realizarPrestamo("978-84-376-0494-7", "U001");
        notificador.notificarPrestamo(usuario1, "Cien años de soledad");
        
        biblioteca.realizarPrestamo("978-84-204-6428-0", "U002");
        notificador.notificarPrestamo(usuario2, "El amor en los tiempos del cólera");
        
        System.out.println("\n--- BUSCANDO LIBROS ---");
        List<Libro> librosGarcia = biblioteca.buscarLibros("García Márquez");
        System.out.println("Libros de García Márquez:");
        for (Libro l : librosGarcia) {
            System.out.println("  • " + l);
        }
        
        System.out.println("\n--- LIBROS DISPONIBLES ---");
        for (Libro l : biblioteca.listarLibrosDisponibles()) {
            System.out.println("  • " + l);
        }
        
        System.out.println("\n--- DEVOLVIENDO LIBROS ---");
        biblioteca.devolverLibro(1);
        
        biblioteca.mostrarEstado();
        
        System.out.println("\n========================================");
        System.out.println("        PROGRAMA EJECUTADO CON ÉXITO");
        System.out.println("========================================");
    }
}