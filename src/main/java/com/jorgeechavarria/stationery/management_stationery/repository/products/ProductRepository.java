package com.jorgeechavarria.stationery.management_stationery.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgeechavarria.stationery.management_stationery.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
