package cl.ms_cliente.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.ms_cliente.dto.CustomerRequestDTO;
import cl.ms_cliente.dto.CustomerResponseDTO;
import cl.ms_cliente.exception.ResourceNotFoundException;
import cl.ms_cliente.exception.DuplicateRutException;
import cl.ms_cliente.model.Customer;
import cl.ms_cliente.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO dto) {
        if (customerRepository.existsByRut(dto.rut())) {
            throw new DuplicateRutException("A customer with RUT already exists: " + dto.rut());
        }
        Customer customer = new Customer();
        customer.setRut(dto.rut());
        customer.setName(dto.name());
        customer.setPhone(dto.phone());
        customer.setEmail(dto.email());
        return mapToDto(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO getByRut(String rut) {
        Customer customer = customerRepository.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with RUT: " + rut));
        return mapToDto(customer);
    }

    @Override
    public CustomerResponseDTO updateByRut(String rut, CustomerRequestDTO dto) {
        Customer existing = customerRepository.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with RUT: " + rut));
        existing.setName(dto.name());
        existing.setPhone(dto.phone());
        existing.setEmail(dto.email());
        return mapToDto(customerRepository.save(existing));
    }

    @Override
    public void deleteByRut(String rut) {
        Customer customer = customerRepository.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with RUT: " + rut));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CustomerResponseDTO mapToDto(Customer c) {
        return new CustomerResponseDTO(c.getRut(), c.getName(), c.getPhone(), c.getEmail());
    }
}
