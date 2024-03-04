package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.models.Supplier;
import com.vku.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllCategories() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(int supplierId) {
        return supplierRepository.findById(supplierId);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(int supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}