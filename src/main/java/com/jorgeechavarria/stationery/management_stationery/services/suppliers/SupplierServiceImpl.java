package com.jorgeechavarria.stationery.management_stationery.services.suppliers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeechavarria.stationery.management_stationery.exceptions.EmailAlreadyExistsException;
import com.jorgeechavarria.stationery.management_stationery.exceptions.IdNotFoundException;
import com.jorgeechavarria.stationery.management_stationery.exceptions.NitAlreadyExistsExcepcion;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierResponse;
import com.jorgeechavarria.stationery.management_stationery.models.mappers.SupplierMapper;
import com.jorgeechavarria.stationery.management_stationery.repository.suppliers.SupplierRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<SupplierResponse> getAll() {
        log.info("Consultando los proveedores en la DB.");

        try {
            var suppliers = supplierRepository.findAll().stream()
                    .map(supplierMapper::toResponse)
                    .toList();
            log.info("Proveedores encontrados exitosamente, Total {}.", suppliers.size());
            return suppliers;
        } catch (Exception e) {
            log.error("Ocurrió un error al consultar los proveedores: {}", e.getMessage());
            throw new RuntimeException("Error interno al obtener los porveedores");
        }

    }

    @Override
    public SupplierResponse create(SupplierRequest request) {

        // Verificar existencia de email
        log.info("verificando exitencia de email");
        var existingEmail = supplierRepository.findByEmail(request.getEmail());
        if (existingEmail.isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Verificar si el nit ya existe o no.
        log.info("Verificando existencia de nit");
        var existingNit = supplierRepository.findByNit(request.getNit());
        if (existingNit.isPresent()) {
            throw new NitAlreadyExistsExcepcion(request.getNit());

        }

        log.info("Creación de nuevo proveedor");
        var newSupplier = supplierMapper.toEntity(request);

        supplierRepository.save(newSupplier);
        log.info("Proveedor guardado exitosamente");

        return supplierMapper.toResponse(newSupplier);

    }

    @Override
    public SupplierResponse update(Integer id, SupplierRequest request) {
        log.info("Verificando existencia de id");
        var existingId = supplierRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        existingId.setName(request.getName());
        existingId.setEmail(request.getEmail());
        existingId.setNit(request.getNit());
        existingId.setPhoneNumber(request.getPhoneNumber());
        existingId.setAddres(request.getAddres());

        supplierRepository.save(existingId);
        log.info("Proveedor modificado exitosamente");

        return supplierMapper.toResponse(existingId);
    }

    @Override
    public SupplierResponse delete(Integer id) {
        log.info("Eliminando proveedor con id" + id);
        var existingId = supplierRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        supplierRepository.delete(existingId);
        log.info("Proveedor eliminado exitosamente" + existingId.getName());

        return supplierMapper.toResponse(existingId);

    }

}
