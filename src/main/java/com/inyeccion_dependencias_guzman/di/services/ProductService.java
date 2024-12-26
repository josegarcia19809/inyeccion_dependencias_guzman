package com.inyeccion_dependencias_guzman.di.services;

import com.inyeccion_dependencias_guzman.di.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
}
