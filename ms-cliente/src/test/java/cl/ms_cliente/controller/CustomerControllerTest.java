package cl.ms_cliente.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.ms_cliente.dto.CustomerRequestDTO;
import cl.ms_cliente.dto.CustomerResponseDTO;
import cl.ms_cliente.service.CustomerService;

// Gracias a esta anotacion, los campos con @Mock se crean automaticamente antes de cada test.
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Test
    void registerCustomer_shouldReturnCreatedResponse() {
        CustomerController customerController = new CustomerController(customerService);
        CustomerResponseDTO responseDto = new CustomerResponseDTO(
            "12345678-9", "Juan Perez", "+56912345678", "juan@mail.com");
        when(customerService.saveCustomer(any())).thenReturn(responseDto);

        ResponseEntity<CustomerResponseDTO> result = customerController.registerCustomer(
            new CustomerRequestDTO("12345678-9", "Juan Perez", "+56912345678", "juan@mail.com")
        );

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Juan Perez", result.getBody().name());
    }

    @Test
    void getByRut_shouldReturnOkResponse() {
        CustomerController customerController = new CustomerController(customerService);
        CustomerResponseDTO responseDto = new CustomerResponseDTO(
            "12345678-9", "Juan Perez", "+56912345678", "juan@mail.com");
        when(customerService.getByRut("12345678-9")).thenReturn(responseDto);

        ResponseEntity<CustomerResponseDTO> result = customerController.getByRut("12345678-9");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("12345678-9", result.getBody().rut());
    }
}
