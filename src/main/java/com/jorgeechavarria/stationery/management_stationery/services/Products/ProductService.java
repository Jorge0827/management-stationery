package com.jorgeechavarria.stationery.management_stationery.services.Products;

import java.util.List;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoProducts.ProductRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoProducts.ProductResponse;

public interface ProductService {

    List<ProductResponse> getAll();

    ProductResponse create(ProductRequest data);

    ProductResponse update(String id, ProductRequest data);

    void delete(String id);

}
