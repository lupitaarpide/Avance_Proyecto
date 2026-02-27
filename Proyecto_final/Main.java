package Proyecto_final;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /// Para guardar libros, y mas adelnate se generen reportes
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static ArrayList<Libro> listaLibros = new ArrayList<>();
    static ArrayList<Prestamo> listaPrestamos = new ArrayList<>();

    public static String leerNombre(Scanner sc) {
        String nombre;

        while (true) {
            nombre = sc.nextLine().trim();

            if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Vuelve a intentarlo SOLO CON LETRAS. PONTE PILAS!!!!");
                System.out.print("Intenta OTRA VEZ: ");
            }
        }
    }

    public static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("TE ESTOY PIDIENDO NÚMEROS. LEE BIEN PORFA!!");
            sc.next();
            System.out.print("Intenta OTRA VEZ: ");
        }
        return sc.nextInt();
    }

    public static int leerEnteroPositivo(Scanner sc) {
        int valor;
        do {
            valor = leerEntero(sc);
            if (valor <= 0) {
                System.out.println("El stock debe ser mayor a 0.");
                System.out.print("Intenta OTRA VEZ: ");
            }
        } while (valor <= 0);
        return valor;
    }

    public static String leerTexto(Scanner sc) {
        String texto;
        do {
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("DEBES ESCRIBIR ALGO!! no puedes dejarlo vacío!!");
                System.out.print("Intenta OTRA VEZ, hazlo bien: ");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== BIBLIOTECA MASTER =====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar libro");
            System.out.println("3. Registrar préstamo");
            System.out.println("4. Consultar libros");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Reporte");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción (número):  ");

            opcion = leerEntero(sc);
            sc.nextLine();

            switch (opcion) {

                case 1: {
                    System.out.print("Matricula: ");
                    String id = leerTexto(sc);

                    System.out.print("Nombre: ");
                    String nombre = leerNombre(sc);

                    listaUsuarios.add(new Usuario(id, nombre));
                    System.out.println("Usuario registrado...");
                    break;
                }

                case 2: {
                    System.out.print("Título: ");
                    String titulo = leerTexto(sc);

                    System.out.print("Autor: ");
                    String autor = leerTexto(sc);

                    System.out.print("Stock: ");
                    int stock = leerEnteroPositivo(sc);
                    sc.nextLine();

                    listaLibros.add(new Libro(titulo, autor, stock));
                    System.out.println("Libro registrado!");
                    break;
                }

                case 3: {
                    if (listaUsuarios.isEmpty() || listaLibros.isEmpty()) {
                        System.out.println("Debe haber usuarios y libros registrados.");
                        break;
                    }

                    System.out.print("Ingrese matrícula del usuario: ");
                    String idBuscar = leerTexto(sc);

                    System.out.print("Ingrese título del libro: ");
                    String tituloBuscar = leerTexto(sc);

                    Usuario usuarioEncontrado = null;
                    Libro libroEncontrado = null;

                    for (Usuario u : listaUsuarios) {
                        if (u.getId().equalsIgnoreCase(idBuscar)) {
                            usuarioEncontrado = u;
                            break;
                        }
                    }

                    for (Libro l : listaLibros) {
                        if (l.getTitulo().equalsIgnoreCase(tituloBuscar)) {
                            libroEncontrado = l;
                            break;
                        }
                    }

                    if (usuarioEncontrado == null || libroEncontrado == null) {
                        System.out.println("Usuario o libro no encontrado.");
                        break;
                    }

                    Prestamo prestamo = new Prestamo(libroEncontrado, usuarioEncontrado);

                    if (prestamo.registrar()) {
                        listaPrestamos.add(prestamo);

                        System.out.println("\n===== PRÉSTAMO REGISTRADO =====");
                        System.out.println("Usuario: " + usuarioEncontrado.getNombre());
                        System.out.println("Libro: " + libroEncontrado.getTitulo());
                        System.out.println("Fecha: " + LocalDate.now());
                        System.out.println("Unidades restantes: " + libroEncontrado.getStock());
                    }

                    break;
                }

                case 4:
                    if (listaLibros.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        System.out.println("\n===== LISTA DE LIBROS =====");
                        for (Libro l : listaLibros) {
                            System.out.println("Título: " + l.getTitulo());
                            System.out.println("Autor: " + l.getAutor());
                            System.out.println("Unidades disponibles: " + l.getStock());
                            System.out.println("----------------------------");
                        }
                    }
                    break;

                case 5:
                    System.out.print("Ingrese TITULO del libro a eliminar: ");
                    String eliminar = leerTexto(sc);

                    boolean eliminado = listaLibros.removeIf(
                            l -> l.getTitulo().equalsIgnoreCase(eliminar));

                    if (eliminado) {
                        System.out.println("Libro eliminado.");
                    } else {
                        System.out.println("No se encontró el libro.");
                    }
                    break;

                case 6:
                    System.out.println("\n===== REPORTE GENERAL =====");
                    System.out.println("Usuarios registrados: " + listaUsuarios.size());
                    System.out.println("Libros registrados: " + listaLibros.size());
                    System.out.println("Préstamos realizados: " + listaPrestamos.size());
                    System.out.println("Fecha de reporte: " + LocalDate.now());
                    break;

                case 7:
                    System.out.println("Adios amiguibi...");
                    break;

                default:
                    System.out.println("NO ES VÁLIDO");
            }

        } while (opcion != 7);

        sc.close();
    }
}

