package Proyecto_final;
import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaSalida;
    private boolean activo;

    public Prestamo(Libro libroParam, Usuario usuarioParam) {
        libro = libroParam;
        usuario = usuarioParam;
        activo = false;
    }

    public boolean registrar() {

        if (!usuario.puedePedir()) {
            System.out.println("El usuario no puede pedir más libros.");
            return false;
        }

        if (!libro.estaDisponible()) {
            System.out.println("Libro no disponible.");
            return false;
        }

        fechaSalida = LocalDate.now();
        usuario.agregarPrestamo();
        libro.actualizarStock(true);

        activo = true;

        System.out.println("Préstamo registrado.");
        return true;
    }

    public void finProceso() {

        if (!activo) {
            System.out.println("El préstamo ya está finalizado.");
            return;
        }

        libro.actualizarStock(false);
        usuario.quitarPrestamo();
        activo = false;

        System.out.println("Préstamo finalizado.");
    }
}
