package com.inyeccion_dependencias_guzman.di.services;

import com.inyeccion_dependencias_guzman.di.models.Product;
import com.inyeccion_dependencias_guzman.di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Environment environment;
    private ProductRepository repository;

    @Value("${config.price.tax}")
    private Double tax;


    public ProductServiceImpl( ProductRepository repository) {
        /*
        Cuando se pone en el contructor ya no es necesario poner @Autowired
        Si se utiliza @Qualifier( se debe escribir la clase en minúscula al inicio
         public ProductServiceImpl(@Qualifier("productList") ProductRepository repository)
         */
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(p -> {
                    Double priceTax = p.getPrice() * tax;
                    //Product newProduct = new Product(p.getId(), p.getName(),
                    //      priceImp.longValue());
                    Product newProduct = p.clone();
                    newProduct.setPrice(priceTax.longValue());
                    return newProduct;
                }).collect(Collectors.toList());
    }

//    @Override
//    public List<Product> findAll() {
//        return repository.findAll().stream()
//                .map(p -> {
//                    Double priceTax = p.getPrice() * 1.25d;
//                    p.setPrice(priceTax.longValue());
//                    return p;
//                }).collect(Collectors.toList());
//    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}


/*
La necesidad de clonar el objeto en el código depende del contexto en el que se está trabajando. Aquí te 
explico las razones clave para clonar el objeto Product en este caso:

1. Evitar modificar el objeto original
Si el métod0 findAll() del repositorio devuelve referencias a los objetos originales (no copias), cualquier 
         cambio en esos objetos afectará directamente a los datos originales almacenados en memoria o en la 
         base de datos.

En este caso:

java
Copiar código
newProduct.setPrice(priceTax.longValue());
Se modifica el precio del producto. Si no se clonara, el precio del producto original también se modificaría,
lo cual puede no ser deseado.

2. Crear un nuevo objeto con cambios específicos
El propósito de este métod0 parece ser crear una nueva lista de productos con el precio actualizado sin alterar
los productos originales. Clonar permite tomar una "plantilla" del producto original y modificar solo las
propiedades necesarias (en este caso, el precio).

3. Evitar efectos secundarios
Modificar directamente los objetos originales puede causar efectos secundarios no deseados en otras partes de
la aplicación que dependan de esos mismos objetos. Por ejemplo:

Si otros métod0s o servicios usan los productos originales mientras se ejecuta este flujo, podrían ver el precio
modificado, lo que podría causar inconsistencias.
Al clonar, se garantiza que los cambios realizados en los objetos no afectarán a los originales, preservando
la integridad de los datos.

4. Mantenimiento de principios de programación
Clonar el objeto respeta principios como:

Inmutabilidad: Se protege el estado de los objetos originales.
Separación de responsabilidades: El métod0 se encarga únicamente de calcular y devolver una nueva lista sin
alterar el estado existente.
Alternativas al clon:
Si no quieres usar clone, puedes crear un nuevo objeto explícitamente:

java
Copiar código
Product newProduct = new Product(p.getId(), p.getName(), priceTax.longValue());
Esto elimina la necesidad de un métod0 clone() y sigue evitando modificar los originales.

¿Cuándo no sería necesario clonar?
Si el métod0 está diseñado para modificar directamente los productos originales, o si repository.findAll()
ya devuelve copias de los datos, el uso de clone() sería redundante.

 */