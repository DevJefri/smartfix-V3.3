package cl.ms_cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ms_cliente.dto.CustomerRequestDTO;
import cl.ms_cliente.dto.CustomerResponseDTO;
import cl.ms_cliente.exception.ResourceNotFoundException;
import cl.ms_cliente.exception.DuplicateRutException;
import cl.ms_cliente.model.Customer;
import cl.ms_cliente.repository.CustomerRepository;

// Integra Mockito con JUnit 5: los campos @Mock se inicializan solos antes de cada test.
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    // Mock: reemplaza el repository real por un doble de prueba controlable.
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer_shouldSaveAndReturnDto() {
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        CustomerRequestDTO dto = new CustomerRequestDTO("12345678-9", "Juan Perez", "+56912345678", "juan@mail.com");

        when(customerRepository.existsByRut("12345678-9")).thenReturn(false);
        // Simulamos el comportamiento de JPA al guardar la entidad.
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResponseDTO result = customerService.saveCustomer(dto);

        assertEquals("12345678-9", result.rut());
        assertEquals("Juan Perez", result.name());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void saveCustomer_shouldThrowWhenDuplicateRut() {
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        CustomerRequestDTO dto = new CustomerRequestDTO("12345678-9", "Juan Perez", "+56912345678", "juan@mail.com");

        when(customerRepository.existsByRut("12345678-9")).thenReturn(true);

        assertThrows(DuplicateRutException.class, () -> customerService.saveCustomer(dto));

        // El cliente no debe guardarse si el RUT ya existe.
        verify(customerRepository, never()).save(any());
    }

    @Test
    void getByRut_shouldThrowWhenNotFound() {
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        when(customerRepository.findByRut("99999999-9")).thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> customerService.getByRut("99999999-9")
        );
    }
}
