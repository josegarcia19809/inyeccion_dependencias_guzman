package com.inyeccion_dependencias_guzman.di.repositories;

import com.inyeccion_dependencias_guzman.di.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(Long id);
}
