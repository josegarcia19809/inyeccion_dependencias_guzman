package com.inyeccion_dependencias_guzman.di.repositories;

import com.inyeccion_dependencias_guzman.di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository {
    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus 27 Foo", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Monitor Asus 27 Foo", 600L);
    }
}
