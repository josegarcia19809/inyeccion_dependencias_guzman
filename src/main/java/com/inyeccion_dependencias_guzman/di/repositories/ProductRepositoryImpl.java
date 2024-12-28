package com.inyeccion_dependencias_guzman.di.repositories;

import com.inyeccion_dependencias_guzman.di.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

/*
Para estas pruebas hay que abrir el navegador y probar la sesión, el valor se destruye al
cerrar la ventana
 */
@SessionScope
@Repository("productList")
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> data;

    public ProductRepositoryImpl(){
        data = Arrays.asList(
                new Product(1L, "Memoria Corsair 32", 100L),
                new Product(2L, "CPU Intel Core i9", 200L),
                new Product(3L, "Teclado Razer Mini 60", 180L),
                new Product(4L, "Motherboard Gigabyte", 500L)
        );
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
    }
}
