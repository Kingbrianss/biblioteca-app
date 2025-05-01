// Prestamo.java
import java.util.Date;

public class Prestamo {
    private int id;
    private int libroId; // Renombrado para mayor claridad
    private int usuarioId; // Renombrado para mayor claridad
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean devuelto;

    public Prestamo(int id, int libroId, int usuarioId, Date fechaPrestamo, Date fechaDevolucion, boolean devuelto) {
        this.id = id;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLibroId() { // Renombrado getter
        return libroId;
    }

    public void setLibroId(int libroId) { // Renombrado setter
        this.libroId = libroId;
    }

    public int getUsuarioId() { // Renombrado getter
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) { // Renombrado setter
        this.usuarioId = usuarioId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}