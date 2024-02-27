package com.vku.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "percent")
    private double percent;

    @Column(name = "status")
    private String status;
    
    @JsonIgnore
	@OneToMany(mappedBy = "discount_id")
    private List<Product> products;

    // Constructors, getters, and setters
}