package servicios;

import modelo.Usuario;

/**
 * Clase de servicio externo para enviar notificaciones
 * DEMUESTRA DEPENDENCIA: La biblioteca depende de este servicio
 * 
 * ¿Qué es dependencia?
 * Cuando una clase (Biblioteca) necesita usar otra clase (Notificador)
 * para cumplir su función, existe una dependencia.
 */
public class Notificador {
    
    /**
     * Método que simula el envío de un correo electrónico
     * @param usuario El usuario que recibirá el correo
     * @param mensaje El mensaje a enviar
     */
    public void enviarCorreo(Usuario usuario, String mensaje) {
        System.out.println("📧 Enviando correo a: " + usuario.getEmail());
        System.out.println("   Asunto: Notificación Biblioteca");
        System.out.println("   Mensaje: " + mensaje);
        System.out.println("   ✓ Correo enviado correctamente");
    }
    
    /**
     * Método que simula el envío de un SMS
     * @param usuario El usuario que recibirá el SMS
     * @param mensaje El mensaje a enviar
     */
    public void enviarSMS(Usuario usuario, String mensaje) {
        System.out.println("📱 Enviando SMS a: " + usuario.getTelefono());
        System.out.println("   Mensaje: " + mensaje);
        System.out.println("   ✓ SMS enviado correctamente");
    }
    
    /**
     * Método específico para notificar cuando se realiza un préstamo
     * @param usuario El usuario que solicita el préstamo
     * @param libroTitulo El título del libro prestado
     */
    public void notificarPrestamo(Usuario usuario, String libroTitulo) {
        String mensaje = "Hola " + usuario.getNombre() + ", has solicitado el libro: " 
                         + libroTitulo + ". Por favor devuélvelo en 15 días.";
        
        System.out.println("\n--- NOTIFICACIÓN DE PRÉSTAMO ---");
        enviarCorreo(usuario, mensaje);
        System.out.println("--------------------------------\n");
    }
    
    /**
     * Método específico para notificar cuando hay una multa
     * @param usuario El usuario con multa
     * @param multa El valor de la multa
     */
    public void notificarMulta(Usuario usuario, long multa) {
        String mensaje = "Hola " + usuario.getNombre() + ", tienes una multa pendiente de $" 
                         + multa + " por retraso en la devolución de un libro.";
        
        System.out.println("\n--- NOTIFICACIÓN DE MULTA ---");
        enviarCorreo(usuario, mensaje);
        enviarSMS(usuario, mensaje);
        System.out.println("-----------------------------\n");
    }
}