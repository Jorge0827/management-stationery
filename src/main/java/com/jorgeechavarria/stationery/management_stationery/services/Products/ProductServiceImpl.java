package com.jorgeechavarria.stationery.management_stationery.services.Products;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeechavarria.stationery.management_stationery.exceptions.DuplicateResourceException;
import com.jorgeechavarria.stationery.management_stationery.exceptions.IdNotFoundException;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoProducts.ProductRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoProducts.ProductResponse;
import com.jorgeechavarria.stationery.management_stationery.models.entities.Product;
import com.jorgeechavarria.stationery.management_stationery.models.mappers.ProductMapper;
import com.jorgeechavarria.stationery.management_stationery.repository.products.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> getAll() {
        log.info("Buscando todos los productos");
        try {
            var products = productRepository.findAll().stream()
                    .map(productMapper::toResponse)
                    .toList();

            return products;
        } catch (Exception e) {
            log.error("Ocurrió un error al encontrar los productos", e.getMessage());
            throw new RuntimeException("Error interno al buscar los productos");
        }

    }

    @Override
    public ProductResponse create(ProductRequest data) {

        var id = productRepository.findById(data.getId());
        log.info("verificando existencia o duplicidad del id del producto");
        if (id.isPresent()) {
            throw new DuplicateResourceException("El id" + data.getId() + " ya existe");
        }

        log.info("Creando nuevo producto");
        var newProduct = productMapper.toEntity(data);

        productRepository.save(newProduct);
        log.info("Producto creado existosamente");

        return productMapper.toResponse(newProduct);

    }

    @Override
    public ProductResponse update(String id, ProductRequest data) {
        log.info("Iniciando actualización del producto con ID: {}", id);
        
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe"));

        // Si el ID cambió, verificar que el nuevo ID no exista
        if (!id.equals(data.getId())) {
            log.info("Cambiando ID de {} a {}", id, data.getId());
            if (productRepository.existsById(data.getId())) {
                throw new DuplicateResourceException("El nuevo ID " + data.getId() + " ya existe");
            }
            
            // Eliminar el producto con el ID viejo
            productRepository.delete(existingProduct);
            
            // Crear producto con el nuevo ID
            var updatedProduct = new Product();
            updatedProduct.setId(data.getId());
            updatedProduct.setName(data.getName());
            updatedProduct.setDescription(data.getDescription());
            updatedProduct.setUnitPrice(data.getUnitPrice());
            updatedProduct.setCurrentStock(data.getCurrentStock());
            
            productRepository.save(updatedProduct);
            log.info("Producto con nuevo ID {} creado exitosamente", data.getId());
            return productMapper.toResponse(updatedProduct);
        } else {
            // Si el ID no cambió, actualizar normalmente
            log.info("Actualizando producto con ID: {}", id);
            existingProduct.setName(data.getName());
            existingProduct.setDescription(data.getDescription());
            existingProduct.setUnitPrice(data.getUnitPrice());
            existingProduct.setCurrentStock(data.getCurrentStock());
            
            productRepository.save(existingProduct);
            log.info("Producto actualizado exitosamente");
            return productMapper.toResponse(existingProduct);
        }
    }

    @Override
    public void delete(String id) {
        log.info("Verficando existencia del id del producto");
        var existingId = productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + "no existe"));

        productRepository.delete(existingId);
        log.info("Producto eliminado correctamente");
        
        
        
    }

}
