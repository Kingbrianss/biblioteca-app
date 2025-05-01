
// Biblioteca.java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public void inicializarDatos() {
        // Libros de ejemplo para pruebas
        libros.add(new Libro(1, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Ficción", true));
        libros.add(new Libro(2, "Cien años de soledad", "Gabriel García Márquez", 1967, "Novela", true));
        libros.add(new Libro(3, "El principito", "Antoine de Saint-Exupéry", 1943, "Fábula", true));
        libros.add(new Libro(4, "Orgullo y prejuicio", "Jane Austen", 1813, "Romance", true));
        libros.add(new Libro(5, "1984", "George Orwell", 1949, "Ciencia Ficción", false)); // Ejemplo de libro no disponible
        libros.add(new Libro(6, "Harry Potter y la piedra filosofal", "J.K. Rowling", 1997, "Fantasía", true));

        // Usuarios de ejemplo para pruebas
        usuarios.add(new Usuario(101, "Jose Camacho", "jantonio@gmail.com", "123456789"));
        usuarios.add(new Usuario(102, "Patricia Moreno", "patricia@gmail.com", "987654321"));
        usuarios.add(new Usuario(103, "Carlos Pérez", "carlos.perez@example.com", "5551234567"));
    }

    public void registrarLibro(int id, String titulo, String autor, int anio, String genero) {
        Libro nuevoLibro = new Libro(id, titulo, autor, anio, genero, true);
        libros.add(nuevoLibro);
    }

    public void registrarUsuario(int id, String nombre, String email, String telefono) {
        Usuario nuevoUsuario = new Usuario(id, nombre, email, telefono);
        usuarios.add(nuevoUsuario);
    }

    public void prestarLibro(int idLibro, int idUsuario) {
        Libro libro = buscarLibroPorId(idLibro);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            System.out.println("Error: Libro no encontrado.");
            return;
        }
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("Error: El libro no está disponible actualmente.");
            return;
        }
        if (contarLibrosPrestados(idUsuario) >= 3) {
            System.out.println("Error: El usuario ya tiene 3 libros prestados.");
            return;
        }

        Date fechaPrestamo = new Date();
        Prestamo nuevoPrestamo = new Prestamo(generarIdPrestamo(), idLibro, idUsuario, fechaPrestamo, null, false);
        prestamos.add(nuevoPrestamo);
        libro.setDisponible(false);
        System.out.println("Préstamo realizado con éxito.");
    }

    public void devolverLibro(int idLibro) {
        Prestamo prestamo = buscarPrestamoActivoPorLibroId(idLibro);
        if (prestamo == null) {
            System.out.println("Error: No hay préstamos activos para este libro.");
            return;
        }
        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(new Date());
        Libro libro = buscarLibroPorId(idLibro);
        if (libro != null) {
            libro.setDisponible(true);
        }
        System.out.println("Libro devuelto con éxito.");
    }

    public void buscarLibros(int opcionBusqueda, String termino) {
        boolean encontrado = false;
        System.out.println("Resultados:");
        for (Libro libro : libros) {
            if (buscarCoincidenciaLibro(libro, opcionBusqueda, termino)) {
                mostrarDetallesLibro(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        }
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Libro libro : libros) {
            mostrarDetallesLibro(libro);
        }
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nombre: " + usuario.getNombre() +
                    " | Email: " + usuario.getEmail() + " | Teléfono: " + usuario.getTelefono());
        }
    }

    public void mostrarPrestamosActivos() {
        boolean hayPrestamos = false;
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()) {
                Libro libro = buscarLibroPorId(prestamo.getLibroId());
                Usuario usuario = buscarUsuarioPorId(prestamo.getLibroId());
                if (libro != null && usuario != null) {
                    System.out.println("ID Préstamo: " + prestamo.getId() +
                            " | Libro: " + libro.getTitulo() +
                            " | Usuario: " + usuario.getNombre() +
                            " | Fecha: " + prestamo.getFechaPrestamo());
                    hayPrestamos = true;
                }
            }
        }
        if (!hayPrestamos) {
            System.out.println("No hay préstamos activos.");
        }
    }

    // Métodos auxiliares privados
    private Libro buscarLibroPorId(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    private Prestamo buscarPrestamoActivoPorLibroId(int idLibro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibroId() == idLibro && !prestamo.isDevuelto()) {
                return prestamo;
            }
        }
        return null;
    }

    private int contarLibrosPrestados(int idUsuario) {
        int contador = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuarioId() == idUsuario && !prestamo.isDevuelto()) {
                contador++;
            }
        }
        return contador;
    }

    private void mostrarDetallesLibro(Libro libro) {
        System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() +
                " | Autor: " + libro.getAutor() + " | Año: " + libro.getAnio() +
                " | Género: " + libro.getGenero() +
                " | Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
    }

    private boolean buscarCoincidenciaLibro(Libro libro, int opcion, String termino) {
        String terminoLower = termino.toLowerCase();
        switch (opcion) {
            case 1:
                return libro.getTitulo().toLowerCase().contains(terminoLower);
            case 2:
                return libro.getAutor().toLowerCase().contains(terminoLower);
            case 3:
                return libro.getGenero().toLowerCase().contains(terminoLower);
            default:
                System.out.println("Opción de búsqueda no válida.");
                return false;
        }
    }

    private int generarIdPrestamo() {
        return prestamos.isEmpty() ? 1 : prestamos.get(prestamos.size() - 1).getId() + 1;
    }
}