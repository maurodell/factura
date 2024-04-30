package com.mauro.curso.springboot.di.factura.springbootfactura.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("itemOffices")
    private List<Item> items;

    public Invoice(Client client, String description, List<Item> items) {
        this.client = client;
        this.description = description;
        this.items = items;
    }

    public Invoice() {

    }

    @PostConstruct//se ejecuta cuando se crea el componente Invoice en el contexto(singleton) de la app.
    public void init(){
        System.out.println("Create the component of the invoice");
        client.setName(client.getName().concat(" Other"));
        description = description.concat(" of client: ").concat(client.getName()).concat(" ").concat(client.getLastname());
    }

    @PreDestroy//Se hace una tarea antes de destruir el componente Invoice en el contexto.
    public void destroy(){
        System.out.println("Destring component");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal(){
        return getItems().stream()
                .map(item -> item.getImporte())//convert the flow in integer
                .reduce(0, (sum, importe) -> sum + importe);//now create a accumulator
    }
}
