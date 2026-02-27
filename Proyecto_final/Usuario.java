package Proyecto_final;

public class Usuario {

    private String id;
    private String nombre;
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

    public void quitarPrestamo() {
        if (prestamosActivos > 0) {
            prestamosActivos--;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}