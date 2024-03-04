package com.vku.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vku.models.Supplier;
import com.vku.services.SupplierService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/suppliers")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllCategories() {
        List<Supplier> suppliers = supplierService.getAllCategories();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) {
        Optional<Supplier> supplier = supplierService.getSupplierById(supplierId);
        return supplier.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
    	supplier.setSupplierId(0);
        Supplier savedSupplier = supplierService.saveSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int supplierId, @RequestBody Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierService.getSupplierById(supplierId);
        if (existingSupplier.isPresent()) {
            supplier.setSupplierId(supplierId);
            Supplier updatedSupplier = supplierService.saveSupplier(supplier);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        Optional<Supplier> existingSupplier = supplierService.getSupplierById(supplierId);
        if (existingSupplier.isPresent()) {
            supplierService.deleteSupplier(supplierId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}