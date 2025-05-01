
import java.util.Scanner;

public class InterfazUsuario {
    private Biblioteca servicioBiblioteca;
    private Scanner scanner = new Scanner(System.in);

    public InterfazUsuario(Biblioteca servicioBiblioteca) {
        this.servicioBiblioteca = servicioBiblioteca;
    }

    public void iniciar() throws Exception {
        servicioBiblioteca.inicializarDatos(); // Trasladamos la inicialización al servicio

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            ejecutarOpcion(opcion);
            salir = (opcion == 9);
        }
        scanner.close();
        System.out.println("¡Sistema de biblioteca finalizado!");
    }

    private void mostrarMenu() {
        System.out.println("--- SISTEMA DE BIBLIOTECA ---");
        System.out.println("1. Registrar nuevo libro");
        System.out.println("2. Registrar nuevo usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Buscar libros");
        System.out.println("6. Ver todos los libros");
        System.out.println("7. Ver todos los usuarios");
        System.out.println("8. Ver préstamos activos");
        System.out.println("9. Salir");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarLibro();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                prestarLibro();
                break;
            case 4:
                devolverLibro();
                break;
            case 5:
                buscarLibros();
                break;
            case 6:
                mostrarLibros();
                break;
            case 7:
                mostrarUsuarios();
                break;
            case 8:
                mostrarPrestamosActivos();
                break;
            case 9:
                break; // La condición de salida se maneja en el bucle principal
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void registrarLibro() {
        System.out.println("--- REGISTRAR NUEVO LIBRO ---");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        servicioBiblioteca.registrarLibro(id, titulo, autor, anio, genero);
        System.out.println("Libro registrado con éxito.");
    }

    private void registrarUsuario() {
        System.out.println("--- REGISTRAR NUEVO USUARIO ---");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        servicioBiblioteca.registrarUsuario(id, nombre, email, telefono);
        System.out.println("Usuario registrado con éxito.");
    }

    private void prestarLibro() {
        System.out.println("--- PRESTAR LIBRO ---");
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        servicioBiblioteca.prestarLibro(idLibro, idUsuario);
    }

    private void devolverLibro() {
        System.out.println("--- DEVOLVER LIBRO ---");
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();
        servicioBiblioteca.devolverLibro(idLibro);
    }

    private void buscarLibros() {
        System.out.println("--- BUSCAR LIBROS ---");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por género");
        System.out.print("Seleccione una opción: ");
        int opcionBusqueda = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese término de búsqueda: ");
        String termino = scanner.nextLine().toLowerCase();
        servicioBiblioteca.buscarLibros(opcionBusqueda, termino);
    }

    private void mostrarLibros() {
        System.out.println("--- LISTADO DE LIBROS ---");
        servicioBiblioteca.mostrarLibros();
    }

    private void mostrarUsuarios() {
        System.out.println("--- LISTADO DE USUARIOS ---");
        servicioBiblioteca.mostrarUsuarios();
    }

    private void mostrarPrestamosActivos() {
        System.out.println("--- PRÉSTAMOS ACTIVOS ---");
        servicioBiblioteca.mostrarPrestamosActivos();
    }
}

