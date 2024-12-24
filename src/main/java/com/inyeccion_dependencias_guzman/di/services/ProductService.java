package com.inyeccion_dependencias_guzman.di.services;

import com.inyeccion_dependencias_guzman.di.models.Product;
import com.inyeccion_dependencias_guzman.di.repositories.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
