package Proyecto_final;

public class Usuario {

    private final String id;
    private final String nombre;
    private int prestamosActivos;

    private static final int MAX_PRESTAMOS = 3;

    public Usuario(String idParam, String nombreParam) {
        id = idParam;
        nombre = nombreParam;
        prestamosActivos = 0;
    }

    public boolean puedePedir() {
        return prestamosActivos < MAX_PRESTAMOS;
    }

    public void agregarPrestamo() {
        if (puedePedir()) {
            prestamosActivos++;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}