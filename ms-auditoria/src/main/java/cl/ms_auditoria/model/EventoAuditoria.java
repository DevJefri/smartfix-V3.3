package cl.ms_auditoria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_auditoria")
public class EventoAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servicio_origen", nullable = false)
    private String servicioOrigen;

    @Column(nullable = false)
    private String accion;

    @Column
    private String detalle;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public EventoAuditoria() {}

    public Long getId() { return id; }
    public String getServicioOrigen() { return servicioOrigen; }
    public void setServicioOrigen(String servicioOrigen) { this.servicioOrigen = servicioOrigen; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
