package br.com.otero.parkinglot.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Carro {

    @Id
//    @Column(name = "plate", unique = true, nullable = false)
    private String plate;
    private String brand;
    private String model;

    public Carro(String plate, String brand, String model) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
    }
}
