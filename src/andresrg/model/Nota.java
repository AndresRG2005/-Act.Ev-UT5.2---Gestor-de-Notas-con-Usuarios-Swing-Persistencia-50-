package andresrg.model;

import java.time.LocalDateTime;

public class Nota {
    private int id;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    public Nota(int id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
        this.fechaModificacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return titulo;
    }

    public String getTitulo() {
        return null;
    }

    public String getContenido() {
        return null;
    }

    public void setTitulo(String text) {

    }

    public boolean getId() {
        return false;
    }
}
