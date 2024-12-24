package com.inyeccion_dependencias_guzman.di.services;

import com.inyeccion_dependencias_guzman.di.models.Product;
import com.inyeccion_dependencias_guzman.di.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(p -> {
                    Double priceImp = p.getPrice() * 1.25d;
                    Product newProduct = new Product(p.getId(), p.getName(),
                            priceImp.longValue());
                    return newProduct;
                }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
