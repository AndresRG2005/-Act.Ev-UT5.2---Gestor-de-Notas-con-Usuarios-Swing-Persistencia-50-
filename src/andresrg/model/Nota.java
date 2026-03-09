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
}
