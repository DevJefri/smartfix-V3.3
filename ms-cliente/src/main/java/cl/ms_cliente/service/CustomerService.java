package cl.ms_cliente.service;

import java.util.List;

import cl.ms_cliente.dto.CustomerRequestDTO;
import cl.ms_cliente.dto.CustomerResponseDTO;

public interface CustomerService {

    /** Registra un nuevo cliente, validando que el RUT no este duplicado. */
    CustomerResponseDTO saveCustomer(CustomerRequestDTO dto);

    /** Busca un cliente por su RUT. */
    CustomerResponseDTO getByRut(String rut);

    /** Actualiza los datos de un cliente existente. */
    CustomerResponseDTO updateByRut(String rut, CustomerRequestDTO dto);

    /** Elimina un cliente por su RUT. */
    void deleteByRut(String rut);

    /** Lista todos los clientes registrados. */
    List<CustomerResponseDTO> listCustomers();
}
