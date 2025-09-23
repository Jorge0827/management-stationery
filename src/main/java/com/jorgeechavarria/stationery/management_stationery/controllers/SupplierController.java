package com.jorgeechavarria.stationery.management_stationery.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierResponse;
import com.jorgeechavarria.stationery.management_stationery.services.suppliers.SupplierService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        var suppliers = supplierService.getAll();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierRequest data) {
        
        var createSupplier = supplierService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(createSupplier);
    }

    @PutMapping("/{id}")
    public SupplierResponse update(@PathVariable Integer id, @RequestBody SupplierRequest entity) {
        return supplierService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public SupplierResponse delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }
    
    
    
    

}
