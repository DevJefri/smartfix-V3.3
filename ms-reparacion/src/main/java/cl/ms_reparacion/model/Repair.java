package cl.ms_reparacion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reparaciones")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rut_cliente", nullable = false)
    private String customerRut;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    public Repair() {}

    public Long getId() { return id; }
    public String getCustomerRut() { return customerRut; }
    public void setCustomerRut(String customerRut) { this.customerRut = customerRut; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
