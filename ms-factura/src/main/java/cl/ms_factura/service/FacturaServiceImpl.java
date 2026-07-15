package cl.ms_factura.service;

import cl.ms_factura.dto.FacturaRequestDTO;
import cl.ms_factura.dto.FacturaResponseDTO;
import cl.ms_factura.exception.FacturaNotFoundException;
import cl.ms_factura.exception.FacturaYaAnuladaException;
import cl.ms_factura.model.Factura;
import cl.ms_factura.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Emite facturas simples asociadas a una reparacion. El folio se genera
 * de forma correlativa a partir de la cantidad de facturas ya emitidas,
 * lo que es suficiente para el volumen de un proyecto academico.
 */
@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public FacturaResponseDTO emitir(FacturaRequestDTO dto) {
        Factura factura = new Factura();
        factura.setFolio(generarFolio());
        factura.setReparacionId(dto.reparacionId());
        factura.setRutCliente(dto.rutCliente());
        factura.setMonto(dto.monto());
        factura.setEstado("EMITIDA");
        factura.setFechaEmision(LocalDateTime.now());

        return mapToDto(facturaRepository.save(factura));
    }

    @Override
    public FacturaResponseDTO obtenerPorFolio(String folio) {
        return mapToDto(findOrThrow(folio));
    }

    @Override
    public List<FacturaResponseDTO> listarPorCliente(String rutCliente) {
        return facturaRepository.findByRutCliente(rutCliente)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public FacturaResponseDTO anular(String folio) {
        Factura factura = findOrThrow(folio);
        try {
            if ("ANULADA".equals(factura.getEstado())) {
                throw new FacturaYaAnuladaException(folio);
            }
            factura.setEstado("ANULADA");
            return mapToDto(facturaRepository.save(factura));
        } catch (FacturaYaAnuladaException ex) {
            throw ex;
        }
    }

    private Factura findOrThrow(String folio) {
        return facturaRepository.findByFolio(folio)
                .orElseThrow(() -> new FacturaNotFoundException(folio));
    }

    private String generarFolio() {
        long siguiente = facturaRepository.count() + 1;
        return String.format("F-%06d", siguiente);
    }

    private FacturaResponseDTO mapToDto(Factura f) {
        return new FacturaResponseDTO(
                f.getFolio(), f.getReparacionId(), f.getRutCliente(),
                f.getMonto(), f.getEstado(), f.getFechaEmision());
    }
}
