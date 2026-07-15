package cl.ms_cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ms_cliente.dto.CustomerRequestDTO;
import cl.ms_cliente.dto.CustomerResponseDTO;
import cl.ms_cliente.service.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> registerCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(dto));
    }

    @GetMapping
    public ResponseEntity<?> listCustomers() {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{rut}")
    public ResponseEntity<CustomerResponseDTO> getByRut(@PathVariable String rut) {
        return ResponseEntity.ok(customerService.getByRut(rut));
    }

    @PutMapping("/{rut}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable String rut,
            @Valid @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(customerService.updateByRut(rut, dto));
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String rut) {
        customerService.deleteByRut(rut);
        return ResponseEntity.noContent().build();
    }
}
