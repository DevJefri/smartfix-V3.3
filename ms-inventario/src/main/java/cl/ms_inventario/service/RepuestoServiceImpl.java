package cl.ms_inventario.service;

import cl.ms_inventario.dto.RepuestoRequestDTO;
import cl.ms_inventario.dto.RepuestoResponseDTO;
import cl.ms_inventario.exception.RepuestoDuplicadoException;
import cl.ms_inventario.exception.RepuestoNotFoundException;
import cl.ms_inventario.exception.StockInsuficienteException;
import cl.ms_inventario.model.Repuesto;
import cl.ms_inventario.repository.RepuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepuestoServiceImpl implements RepuestoService {

    private static final Logger log = LoggerFactory.getLogger(RepuestoServiceImpl.class);

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public RepuestoResponseDTO crear(RepuestoRequestDTO dto) {
        if (repuestoRepository.existsBySku(dto.sku())) {
            throw new RepuestoDuplicadoException(dto.sku());
        }

        Repuesto repuesto = new Repuesto();
        repuesto.setSku(dto.sku());
        repuesto.setNombre(dto.nombre());
        repuesto.setStock(dto.stock());
        repuesto.setPrecioUnitario(dto.precioUnitario());

        return mapToDto(repuestoRepository.save(repuesto));
    }

    @Override
    public List<RepuestoResponseDTO> listarTodos() {
        return repuestoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public RepuestoResponseDTO obtenerPorSku(String sku) {
        return mapToDto(findOrThrow(sku));
    }

    @Override
    public RepuestoResponseDTO actualizar(String sku, RepuestoRequestDTO dto) {
        Repuesto repuesto = findOrThrow(sku);
        repuesto.setNombre(dto.nombre());
        repuesto.setPrecioUnitario(dto.precioUnitario());
        return mapToDto(repuestoRepository.save(repuesto));
    }

    @Override
    public void eliminar(String sku) {
        if (!repuestoRepository.existsBySku(sku)) {
            throw new RepuestoNotFoundException(sku);
        }
        repuestoRepository.deleteBySku(sku);
    }

    @Override
    public RepuestoResponseDTO ajustarStock(String sku, int cantidad) {
        Repuesto repuesto = findOrThrow(sku);
        int nuevoStock = repuesto.getStock() + cantidad;

        try {
            if (nuevoStock < 0) {
                throw new StockInsuficienteException(sku, repuesto.getStock(), Math.abs(cantidad));
            }
            repuesto.setStock(nuevoStock);
            return mapToDto(repuestoRepository.save(repuesto));
        } catch (StockInsuficienteException ex) {
            log.warn("Ajuste de stock rechazado: {}", ex.getMessage());
            throw ex;
        }
    }

    private Repuesto findOrThrow(String sku) {
        return repuestoRepository.findBySku(sku)
                .orElseThrow(() -> new RepuestoNotFoundException(sku));
    }

    private RepuestoResponseDTO mapToDto(Repuesto r) {
        return new RepuestoResponseDTO(r.getId(), r.getSku(), r.getNombre(), r.getStock(), r.getPrecioUnitario());
    }
}
