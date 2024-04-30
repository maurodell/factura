package com.mauro.curso.springboot.di.factura.springbootfactura;

import com.mauro.curso.springboot.di.factura.springbootfactura.models.Item;
import com.mauro.curso.springboot.di.factura.springbootfactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean
    //@Primary
    List<Item> itemsInvoice() {
        Product product = new Product("Camara Sonny", 800);
        Product product2 = new Product("Bicicleta Bizz 28", 1200);

        return Arrays.asList(new Item(product, 3), new Item(product2, 4));
    }

    @Bean(name = "itemOffices")
    //@Primary
    List<Item> itemsInvoiceOffice() {
        Product product = new Product("Monitor Azuz", 500);
        Product product2 = new Product("Laptop", 2200);
        Product product3 = new Product("Telephone", 890);
        Product product4 = new Product("Pencil", 5);

        return Arrays.asList(new Item(product, 4),
                new Item(product2, 2),
                new Item(product3, 1),
                new Item(product4, 100));
    }
}
