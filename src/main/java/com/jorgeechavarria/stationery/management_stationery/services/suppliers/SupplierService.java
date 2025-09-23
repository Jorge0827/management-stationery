package com.jorgeechavarria.stationery.management_stationery.services.suppliers;

import java.util.List;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierResponse;

public interface SupplierService {

    List<SupplierResponse> getAll();

    SupplierResponse create(SupplierRequest request);

    SupplierResponse update(Integer id, SupplierRequest request);

    SupplierResponse delete(Integer id);

}
