package com.inyeccion_dependencias_guzman.di.repositories;

import com.inyeccion_dependencias_guzman.di.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    private List<Product> data;

    public ProductRepository() {
        data = Arrays.asList(
                new Product(1L, "Memoria Crsair 32", 100L),
                new Product(2L, "CPU Intel Core i9", 200L),
                new Product(3L, "Teclado Razer Mini 60", 180L),
                new Product(4L, "Motherboard Gigabyte", 500L)
        );
    }

    public List<Product> findAll() {
        return data;
    }
}
