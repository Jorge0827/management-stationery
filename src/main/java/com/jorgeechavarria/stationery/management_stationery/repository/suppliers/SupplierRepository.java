package com.jorgeechavarria.stationery.management_stationery.repository.suppliers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgeechavarria.stationery.management_stationery.models.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findByEmail(String email);

    Optional<Supplier> findByNit(String nit);
}
