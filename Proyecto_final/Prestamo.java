package Proyecto_final;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;

    public Prestamo(Libro libroParam, Usuario usuarioParam) {
        libro = libroParam;
        usuario = usuarioParam;
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

        usuario.agregarPrestamo();
        libro.actualizarStock(true);


        System.out.println("Préstamo registrado.");
        return true;

    }
}
