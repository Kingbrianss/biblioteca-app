// BibliotecaApp.java
public class BibliotecaApp {
    public static void main(String[] args) throws Exception {
        Biblioteca servicioBiblioteca = new Biblioteca();
        InterfazUsuario interfazUsuario = new InterfazUsuario(servicioBiblioteca);
        interfazUsuario.iniciar();
    }
}
