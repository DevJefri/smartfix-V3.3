package cl.ms_factura.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String folio;

    @Column(name = "reparacion_id", nullable = false)
    private Long reparacionId;

    @Column(name = "rut_cliente", nullable = false)
    private String rutCliente;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    public Factura() {}

    public Long getId() { return id; }
    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }
    public Long getReparacionId() { return reparacionId; }
    public void setReparacionId(Long reparacionId) { this.reparacionId = reparacionId; }
    public String getRutCliente() { return rutCliente; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
}
