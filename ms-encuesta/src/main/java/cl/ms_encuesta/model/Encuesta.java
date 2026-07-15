package cl.ms_encuesta.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "encuestas")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reparacion_id", nullable = false, unique = true)
    private Long reparacionId;

    @Column(name = "rut_cliente", nullable = false)
    private String rutCliente;

    @Column(nullable = false)
    private Integer puntuacion;

    @Column
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Encuesta() {}

    public Long getId() { return id; }
    public Long getReparacionId() { return reparacionId; }
    public void setReparacionId(Long reparacionId) { this.reparacionId = reparacionId; }
    public String getRutCliente() { return rutCliente; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }
    public Integer getPuntuacion() { return puntuacion; }
    public void setPuntuacion(Integer puntuacion) { this.puntuacion = puntuacion; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
